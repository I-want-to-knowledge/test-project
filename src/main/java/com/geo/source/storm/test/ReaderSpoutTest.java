package com.geo.source.storm.test;

import java.util.Map;
import java.util.Random;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichSpout;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

public class ReaderSpoutTest implements IRichSpout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Create instance for SpoutOutputCollector which passes tuples to bolt.
	private SpoutOutputCollector collector;
	// private boolean completed = false;

	// Create instance for TopologyContext which contains topology data.
	// private TopologyContext context;

	// Create instance for Random class.
	private Random randomGenerator = new Random();
	private Integer idx = 0;

	@Override
	public void open(@SuppressWarnings("rawtypes") Map conf, TopologyContext context, SpoutOutputCollector collector) {
		// this.context = context;
		this.collector = collector;
	}
	
	@Override
	public void nextTuple() {
		if (idx <= 1000) {
			/*List<String> mobileNumbers = new ArrayList<String>();
      mobileNumbers.add("1234123401");
      mobileNumbers.add("1234123402");
      mobileNumbers.add("1234123403");
      mobileNumbers.add("1234123404");
			int size = mobileNumbers.size();*/
      
      Integer localIdx = 0;
      while (localIdx++ < 100 && this.idx <= 1000) {
      	
        this.collector.emit(new Values("topic_name" + randomGenerator.nextInt(), "测试的内容，看看能不能获取到！" + randomGenerator.nextInt()));
			}
		}
	}

	@Override
	public void close() {
		System.out.println("No processing!");
	}

	@Override
	public void activate() {
		System.out.println("No processing!");
	}

	@Override
	public void deactivate() {
		
	}

	@Override
	public void ack(Object msgId) {
		
	}

	@Override
	public void fail(Object msgId) {
		
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("topic", "content"));
	}

	@Override
	public Map<String, Object> getComponentConfiguration() {
		return null;
	}

}
