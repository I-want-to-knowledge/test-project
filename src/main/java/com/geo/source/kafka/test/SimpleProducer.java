package com.geo.source.kafka.test;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class SimpleProducer {

	public static void main(String[] args) {
		
		// Assign topicName to string variable
		String topicName = "topic2";
		
		// create instance for properties to access producer configs
		Properties props = new Properties();
		// Assign localhost id			192.168.10.69:9092,192.168.10.45:9092
		props.put(KafkaConfig.Config.SERVER, KafkaConfig.Config.IPPORT + ",192.168.10.101:9092");

		// Set acknowledgements for producer requests.
		props.put(KafkaConfig.Config.ACKS, "all");

		// If the request fails, the producer can automatically retry,
		props.put(KafkaConfig.Config.RETRIES, 0);

		// Specify buffer size in config
		props.put(KafkaConfig.Config.SIZE, 16384);

		// Reduce the no of requests less than 0
		props.put(KafkaConfig.Config.MS, 1);

		// The buffer.memory controls the total amount of memory available to the
		// producer for buffering.
		props.put(KafkaConfig.Config.MEMORY, 33554432);

		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		Producer<String, String> producer = new KafkaProducer<String, String>(props);
		
		for (int i = 0; i < 10; i++)
			producer.send(new ProducerRecord<String, String>(topicName, i + "d", i + "dXXX"));
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Message sent successfully");
		producer.close();
	}

}
