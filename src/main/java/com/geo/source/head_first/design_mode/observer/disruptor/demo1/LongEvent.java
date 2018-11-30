package com.geo.source.head_first.design_mode.observer.disruptor.demo1;

/**
 * 事件
 *
 * @author YanZhen
 * 2018-10-18 16:51:46
 * LongEvent
 */
public class LongEvent {

	private Long value;
	
	public void set(long value) {
		this.value = value;
	}
	
	public Long get() {
		return value;
	}
}
