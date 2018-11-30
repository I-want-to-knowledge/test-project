package com.geo.source.kafka.test.simple;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.CyclicBarrier;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import com.geo.source.kafka.test.KafkaConfig;

public class ConsumerTest {

	public static void main(String[] args) {
		CyclicBarrier cb = new CyclicBarrier(1);
		
		try {
			// new Thread(() -> consumer1(KafkaConfig.Config.IPPORT + ",192.168.10.69:9092", "topic_group")).start();
			new Thread(() -> consumer1("192.168.10.101:9092,192.168.10.102:9092", "bolt")).start();
			// new Thread(() -> consumer1("192.168.10.69:9092", "topic_group")).start();
			// new Thread(() -> consumer1("192.168.10.69:9092", "topic_group1")).start();
			// new Thread(() -> consumer1("192.168.10.101:9092", "topic_group")).start();
			cb.await();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
	}

	/**
	 * 消费者1
	 *
	 * 2018-07-17 14:45:49 void
	 * @param ipPort 
	 * @param groupName 
	 */
	private static void consumer1(String ipPort, String groupName) {
		
		// kafka consumer configuration settings
		String topicName = "bolt1";
		Properties p = new Properties();
		p.put(KafkaConfig.Config.SERVER, ipPort);
		p.put(KafkaConfig.Config.GROUPID, groupName);
		p.put("client.id", topicName);
		p.put(KafkaConfig.Config.COMMIT, "true");
		p.put(KafkaConfig.Config.INTERVAL, "1000");
		p.put(KafkaConfig.Config.TIMEOUT, "30000");
		p.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		p.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		
		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(p);
		consumer.subscribe(Arrays.asList(topicName));
		System.out.println("Subscribed to topic " + topicName);
		
		try {
			while(true) {
				ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
				records.forEach(record -> {
					System.out.printf("------------ip:%s，group name:%s！", ipPort, groupName);
					// print the offset,key and value for the consumer records.
					System.out.printf("输出结果topic = %s, value = %s	\n", record.topic(), record.value());
				});
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			consumer.close();
		}
		
	}

}
