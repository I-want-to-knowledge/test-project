package com.geo.source.head_first.design_mode.observer.disruptor.demo2;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

public class C22EventHandler implements EventHandler<LongEvent>, WorkHandler<LongEvent> {

	@Override
	public void onEvent(LongEvent event) throws Exception {
		Long value = event.getValue();
		if (value == null) {
			return;
		}
		value += 40;
		System.out.println(System.currentTimeMillis()+": c2-2 consumer finished.number=" + value);
	}

	@Override
	public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
		Long value = event.getValue();
		if (value == null) {
			return;
		}
		value -= 40;
		System.out.println(System.currentTimeMillis()+": c2-2 consumer finished.number=" + value);
	}

}
