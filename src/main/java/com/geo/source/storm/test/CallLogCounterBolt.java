package com.geo.source.storm.test;

import java.util.HashMap;
import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CallLogCounterBolt extends BaseRichBolt {

	private final Logger LOG =  LoggerFactory.getLogger(CallLogCounterBolt.class);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Map<String, Integer> counterMap;
  private OutputCollector collector;

	@Override
	public void prepare(@SuppressWarnings("rawtypes") Map stormConf, TopologyContext context, OutputCollector collector) {
		this.counterMap = new HashMap<>();
		this.collector = collector;
	}

	@Override
	public void execute(Tuple input) {
		String call = input.getString(0);
		// String duration = input.getString(1);
		
		if (!counterMap.containsKey(call)) {
			counterMap.put(call, 1);
		} else {
			Integer c = counterMap.get(call) + 1;
			counterMap.put(call, c);
		}
		
		LOG.info("\n------->第二波处理完成获取到的数据:" + call + "----" + input.getString(1));
		
		collector.ack(input);
	}

	@Override
	public void cleanup() {
		for (String key : counterMap.keySet()) {
			System.out.println(key + ":" + counterMap.get(key));
		}
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("call"));
	}

	@Override
	public Map<String, Object> getComponentConfiguration() {
		return null;
	}

}
