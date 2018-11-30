package com.geo.source.head_first.design_mode.observer.disruptor.demo1;

import com.lmax.disruptor.EventTranslatorOneArg;

public class LongEventTranslator implements EventTranslatorOneArg<LongEvent, Long> {

	@Override
	public void translateTo(LongEvent event, long sequence, Long l) {
		event.set(l);
	}

}
