package com.geo.source.head_first.design_mode.observer.event_listener.demo1;

import org.springframework.context.ApplicationEvent;

/**
 * 
 *
 * @author YanZhen
 * 2018-10-17 13:49:48
 * DemoEvent
 */
public class DemoEvent extends ApplicationEvent {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String msg;
	
	public void addLog() {
		System.out.println("日志：" + msg);
	}

	public DemoEvent(Object source, String msg) {
		super(source);
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
