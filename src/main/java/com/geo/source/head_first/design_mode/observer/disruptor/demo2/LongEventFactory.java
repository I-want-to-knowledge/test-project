package com.geo.source.head_first.design_mode.observer.disruptor.demo2;

import com.lmax.disruptor.EventFactory;

/**
 * 事件工厂
 *
 * @author YanZhen
 * 2018-10-18 16:53:58
 * LongEventFactory
 */
public class LongEventFactory implements EventFactory<LongEvent> {

	@Override
	public LongEvent newInstance() {
		
		return new LongEvent();
	}

}
