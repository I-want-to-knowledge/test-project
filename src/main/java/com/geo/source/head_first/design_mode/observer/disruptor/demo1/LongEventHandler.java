package com.geo.source.head_first.design_mode.observer.disruptor.demo1;

import com.lmax.disruptor.EventHandler;

/**
 * 
 *
 * @author YanZhen
 * 2018-10-18 16:56:19
 * LongEventHandler
 */
public class LongEventHandler implements EventHandler<LongEvent> {

	@Override
	public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
		System.out.println("事件接收：{" + event.get() + "}，接收成功！{" + endOfBatch + "}");
	}

}
