package com.geo.source.storm.test;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.kafka.spout.KafkaSpout;
import org.apache.storm.kafka.spout.KafkaSpoutConfig;
import org.apache.storm.kafka.spout.KafkaSpoutConfig.FirstPollOffsetStrategy;
import org.apache.storm.kafka.spout.KafkaSpoutConfig.ProcessingGuarantee;
import org.apache.storm.kafka.spout.KafkaTuple;
import org.apache.storm.kafka.spout.RecordTranslator;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogAnalyserStorm {

	private static final Logger LOG = LoggerFactory.getLogger(LogAnalyserStorm.class);  
	public static void main(String[] args) throws AlreadyAliveException, InvalidTopologyException, AuthorizationException {
		// mathod1();
		// mathod2();
		mathod3();

	}
	
	/**
	 * 本地访问
	 *
	 * 2018-08-07 15:41:36 void
	 */
	@SuppressWarnings("unused")
	private static void mathod1() {
		//Create Config instance for cluster configuration
    Config config = new Config();
    config.setDebug(true);
	
    //
    TopologyBuilder builder = new TopologyBuilder();
    builder.setSpout("call-log-reader-spout", new FakeCallLogReaderSpout());

    builder.setBolt("call-log-creator-bolt", new CallLogCreatorBolt())
       .shuffleGrouping("call-log-reader-spout");

    builder.setBolt("call-log-counter-bolt", new CallLogCounterBolt())
       .fieldsGrouping("call-log-creator-bolt", new Fields("call"));
		
    LocalCluster cluster = new LocalCluster();
    cluster.submitTopology("LogAnalyserStorm", config, builder.createTopology());
    try {
			Thread.sleep(100000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    
    LOG.error("over!");
    
    //Stop the topology
    cluster.shutdown();
	}

	/**
	 * 外部访问
	 *
	 * 2018-08-07 15:41:22
	 * @throws AlreadyAliveException
	 * @throws InvalidTopologyException
	 * @throws AuthorizationException void
	 */
	@SuppressWarnings("unused")
	private static void mathod2() throws AlreadyAliveException, InvalidTopologyException, AuthorizationException {
		
		// Create Config instance for cluster configuration
		Config config = new Config();
		/*config.put(Config.NIMBUS_SEEDS, Arrays.asList("192.168.10.102"));
		config.put(Config.NIMBUS_THRIFT_PORT, 6627);
		config.put(Config.STORM_ZOOKEEPER_SERVERS, Arrays.asList("192.168.10.102"));
		config.put(Config.STORM_ZOOKEEPER_PORT, 2181);*/
		config.setDebug(true);
		// 设置work的数量
		config.setNumWorkers(4);
		
		// ResourceBundle bundle = ResourceBundle.getBundle("kafkaconfig");
		/*KafkaSpout<String, String> spout = new KafkaSpout<>(KafkaSpoutConfig.builder(bundle.getString("bootstrap.servers"), Topic.ALL)
				.setProcessingGuarantee(ProcessingGuarantee.AT_MOST_ONCE)
        .setFirstPollOffsetStrategy(FirstPollOffsetStrategy.LATEST)
        .setProp(ConsumerConfig.GROUP_ID_CONFIG, bundle.getString("group.id"))
        .setRecordTranslator(new RecordTranslatorImpl())
				.build());*/
		
		TopologyBuilder builder = new TopologyBuilder();
		// builder.setSpout("call-log-reader-spout", spout, 1);
		builder.setSpout("call-log-reader-spout", new ReaderSpoutTest());
		builder.setBolt("call-log-creator-bolt", new CallLogCreatorBolt(), 1).shuffleGrouping("call-log-reader-spout");
		builder.setBolt("call-log-over-bolt", new CallLogOverBolt(), 1).shuffleGrouping("call-log-reader-spout");
		builder.setBolt("call-log-over2-bolt", new CallLogOverBolt(), 1).shuffleGrouping("call-log-reader-spout");
		builder.setBolt("call-log-count-bolt", new CallLogCounterBolt(), 1).fieldsGrouping("call-log-creator-bolt", new Fields("call"));
		
		StormSubmitter.submitTopology("LogAnalyserStorm3", config, builder.createTopology());
		
	}
	
	/**
	 * 外部访问
	 *
	 * 2018-08-07 15:41:22
	 * @throws AlreadyAliveException
	 * @throws InvalidTopologyException
	 * @throws AuthorizationException void
	 */
	private static void mathod3() throws AlreadyAliveException, InvalidTopologyException, AuthorizationException {
		
		// Create Config instance for cluster configuration
		Config config = new Config();
		/*config.put(Config.NIMBUS_SEEDS, Arrays.asList("192.168.10.102"));
		config.put(Config.NIMBUS_THRIFT_PORT, 6627);
		config.put(Config.STORM_ZOOKEEPER_SERVERS, Arrays.asList("192.168.10.102"));
		config.put(Config.STORM_ZOOKEEPER_PORT, 2181);*/
		config.setDebug(true);
		// 设置work的数量
		config.setNumWorkers(1);
		
		ResourceBundle bundle = ResourceBundle.getBundle("kafkaconfig");
		KafkaSpout<String, String> spout = new KafkaSpout<>(KafkaSpoutConfig.builder(bundle.getString("bootstrap.servers"), Topic.BOLT1)// 监听bolt1 topic
				.setProcessingGuarantee(ProcessingGuarantee.AT_MOST_ONCE)
				.setFirstPollOffsetStrategy(FirstPollOffsetStrategy.LATEST)
				.setProp(ConsumerConfig.GROUP_ID_CONFIG, bundle.getString("group.id"))
				.setRecordTranslator(new RecordTranslatorImpl())
				.build());
		
		TopologyBuilder builder = new TopologyBuilder();
		builder.setSpout("call-log-reader-spout", spout, 1);
		builder.setBolt("call-log-creator-bolt", new CallLogCreatorBolt(), 1).shuffleGrouping("call-log-reader-spout", Topic.BOLT1);
		/*builder.setBolt("call-log-over-bolt", new CallLogOverBolt(), 1).shuffleGrouping("call-log-reader-spout", Topic.BOLT2);
		builder.setBolt("call-log-over2-bolt", new CallLogOverBolt(), 1).shuffleGrouping("call-log-reader-spout", Topic.BOLT3);*/
		builder.setBolt("call-log-count-bolt", new CallLogCounterBolt(), 1).fieldsGrouping("call-log-creator-bolt", new Fields("call"));
		/*builder.setSpout("call-log-reader-spout", new FakeCallLogReaderSpout());
		builder.setBolt("call-log-creator-bolt", new CallLogCreatorBolt()).shuffleGrouping("call-log-reader-spout");
		builder.setBolt("call-log-count-bolt", new CallLogCounterBolt()).fieldsGrouping("call-log-creator-bolt",
				new Fields("call"));*/
		
		// System.setProperty("storm.jar","/usr/storm/storm-remote-submit-1.0-SNAPSHOT-jar-with-dependencies.jar");
		StormSubmitter.submitTopology("LogAnalyserStorm", config, builder.createTopology());
		
	}

	/**
	 * 主题
	 *
	 * @author YanZhen
	 * 2018-08-15 17:00:43
	 * Topic
	 */
	private interface Topic {
		// final String TOPIC1 = "topic1";
		
		// 读取数据
		final String BOLT1 = "bolt1";
		// 创建
		final String BOLT2 = "bolt2";
		// 计数
		final String BOLT3 = "bolt3";
		
		final String[] ALL = new String[] {BOLT1, BOLT2, BOLT3};
	}
	
	/**
	 * 自定义消息队列的数据记录转换器
	 *
	 * @author YanZhen
	 * 2018-08-20 11:11:21
	 * RecordTranslatorImpl
	 */
	private static class RecordTranslatorImpl implements RecordTranslator<String, String> {

    private static final long serialVersionUID = 1L;

    private static final Fields FIELDS = new Fields("topic", "content");
    
    @Override
    public List<Object> apply(ConsumerRecord<String, String> record) {
      String topic = record.topic();
      return new KafkaTuple(topic, record.value()).routedTo(topic);
    }

    @Override
    public Fields getFieldsFor(String stream) {
      return FIELDS;
    }

    @Override
    public List<String> streams() {
      return Arrays.asList(Topic.ALL);
    }
    
  }
}
