package com.geo.source.head_first.design_mode.observer.disruptor.demo2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lmax.disruptor.BatchEventProcessor;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventProcessor;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.Sequence;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

public class DisruptorMain {

	public static void main(String[] args) {
		method1();
	}

	private static void method1() {
		int bufferSize = 1024 * 1024;
		EventFactory<LongEvent> eventFactory = new LongEventFactory();
		ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
		
		// 生成
		Disruptor<LongEvent> disruptor = new Disruptor<>(eventFactory, bufferSize,
				newSingleThreadExecutor, ProducerType.SINGLE, new YieldingWaitStrategy());
		
		EventProcessor pro1 = new BatchEventProcessor<>(disruptor.getRingBuffer(),
				disruptor.getRingBuffer().newBarrier(new Sequence[0]), new C11EventHandler());
		EventProcessor pro2 = new BatchEventProcessor<>(disruptor.getRingBuffer(),
				disruptor.getRingBuffer().newBarrier(new Sequence[0]), new C12EventHandler());
		EventProcessor pro3 = new BatchEventProcessor<>(disruptor.getRingBuffer(),
				disruptor.getRingBuffer().newBarrier(new Sequence[0]), new C21EventHandler());
		EventProcessor pro4 = new BatchEventProcessor<>(disruptor.getRingBuffer(),
				disruptor.getRingBuffer().newBarrier(new Sequence[0]), new C22EventHandler());
		
		
		disruptor.handleEventsWith(pro1, pro2, pro3, pro4);
		disruptor.start();
		
		RingBuffer<LongEvent> ringbuffer = disruptor.getRingBuffer();
		try {
			ringbuffer.publishEvent(new LongEventTranslator(), 20l);
			ringbuffer.publishEvent(new LongEventTranslator(), 30l);
			ringbuffer.publishEvent(new LongEventTranslator(), 40l);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ringbuffer.publish(ringbuffer.next());
		}
		
		newSingleThreadExecutor.shutdown();
		disruptor.shutdown();
	}
}
