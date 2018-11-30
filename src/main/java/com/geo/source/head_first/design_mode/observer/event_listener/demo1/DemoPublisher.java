package com.geo.source.head_first.design_mode.observer.event_listener.demo1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 
 *
 * @author YanZhen
 * 2018-10-17 14:12:00
 * DemoPublisher
 */
@Component
public class DemoPublisher {
	
	@Autowired
	ApplicationContext context;
	
	public void published() {
		DemoEvent event = new DemoEvent(this, "一个test事件\n发布成功！");
		System.out.println("发布事件：" + event);
		context.publishEvent(event);
	}
}
