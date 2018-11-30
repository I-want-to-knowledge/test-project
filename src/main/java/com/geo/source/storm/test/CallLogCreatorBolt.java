package com.geo.source.storm.test;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CallLogCreatorBolt extends BaseRichBolt {

	private final Logger LOG = LoggerFactory.getLogger(CallLogCreatorBolt.class);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Create instance for OutputCollector which collects and emits tuples to produce output
	private OutputCollector collector;

	@Override
	public void prepare(@SuppressWarnings("rawtypes") Map stormConf, TopologyContext context, OutputCollector collector) {
		this.collector = collector;
	}

	@Override
	public void execute(Tuple tuple) {
		/*String from = tuple.getString(0);
    String to = tuple.getString(1);
    Integer duration = tuple.getInteger(2);*/
		String content = tuple.getStringByField("content");
		try {
			// 写入一个文件
			DataOutputStream out_file = new DataOutputStream(new FileOutputStream("kafkastorm.out"));
			out_file.writeUTF(content);
			out_file.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    collector.emit(new Values("call-->这是你写入的内容：" + content, "duration-->" + content));
    
    LOG.info("\n-------->第一轮接收到消息：topic-->" + tuple.getStringByField("topic") + "content-->" + content);
    
    collector.ack(tuple);
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("call", "duration"));
	}

	/*@Override
	public Map<String, Object> getComponentConfiguration() {
		return null;
	}*/

}
