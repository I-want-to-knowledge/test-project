package com.geo.source.head_first.design_mode.observer.disruptor.demo2;

import com.lmax.disruptor.EventTranslatorOneArg;

/**
 * 转换类
 *
 * @author YanZhen
 * 2018-10-18 20:02:34
 * LongEventTranslator
 */
public class LongEventTranslator implements EventTranslatorOneArg<LongEvent, Long> {

	@Override
	public void translateTo(LongEvent event, long sequence, Long l) {
		event.setValue(l);
	}

}
