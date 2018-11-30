package com.geo.source.storm.test;

import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CallLogOverBolt extends BaseRichBolt {

	private final Logger LOG = LoggerFactory.getLogger(CallLogOverBolt.class);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private OutputCollector collector;

	@Override
	public void prepare(@SuppressWarnings("rawtypes") Map stormConf, TopologyContext context, OutputCollector collector) {
		this.collector = collector;
	}

	@Override
	public void execute(Tuple tuple) {
		String topic = tuple.getStringByField("topic");
		String content = tuple.getStringByField("content");
		LOG.info("\n-------->结束了消息已经收到：topic-->" + topic + "content-->" + content);
    
    collector.ack(tuple);
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
	}
}
