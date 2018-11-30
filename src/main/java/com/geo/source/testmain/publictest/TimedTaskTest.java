package com.geo.source.testmain.publictest;

import java.util.Timer;
import java.util.TimerTask;

public class TimedTaskTest {

	public static void main(String[] args) {
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				// task to run goes here
				System.out.println("Hello !!!");
			}
		};
		Timer timer = new Timer();
		long delay = 0;
		long intevalPeriod = 1 * 1000;
		// schedules the task to be run in an interval
		timer.scheduleAtFixedRate(task, delay, intevalPeriod);
	}

}
