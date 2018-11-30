package com.geo.source.head_first.design_mode.observer.disruptor.demo1;

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
		EventFactory<LongEvent> eventFactory = new LongEventFactory();
		ExecutorService executor = Executors.newSingleThreadExecutor();
		int ringBufferSize = 1024 * 1024;
		Disruptor<LongEvent> disruptor = new Disruptor<>(eventFactory, ringBufferSize, executor, ProducerType.SINGLE, new YieldingWaitStrategy());
		
		EventProcessor processor = new BatchEventProcessor<>(
        disruptor.getRingBuffer(),
        disruptor.getRingBuffer().newBarrier(new Sequence[0]),
        new LongEventHandler());
		
		disruptor.handleEventsWith(processor);
		RingBuffer<LongEvent> ringBuffer = disruptor.start();
		
		for (int i=0;i<100;i++) {
			long next = ringBuffer.next();
			try {
				LongEvent longEvent = ringBuffer.get(next);
				longEvent.set(i);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				ringBuffer.publish(next);
			}
		}
		
		executor.shutdown();
		disruptor.shutdown();
	}

}
