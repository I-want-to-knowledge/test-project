package com.geo.source.net.jcip;

import org.apache.storm.shade.org.apache.http.annotation.GuardedBy;
import org.apache.storm.shade.org.apache.http.annotation.Immutable;
import org.apache.storm.shade.org.apache.http.annotation.NotThreadSafe;
import org.apache.storm.shade.org.apache.http.annotation.ThreadSafe;

import java.util.concurrent.atomic.AtomicLong;

@NotThreadSafe
@ThreadSafe
@Immutable
public class ThreadTest {
	@GuardedBy("this") private int value;
	
	private final AtomicLong l = new AtomicLong(0);

	public void test() {
		l.get();
		l.incrementAndGet();
		l.get();
	}
}
