package com.geo.source.head_first.design_mode.observer.event_listener.demo1;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 
 *
 * @author YanZhen
 * 2018-10-17 14:12:38
 * DemoListener
 */
@Component
public class DemoListener/* implements ApplicationListener<DemoEvent>*/ {

	// @Override
	@EventListener
	public void onApplicationEvent(DemoEvent event) {
		String msg = event.getMsg();
		System.out.println("demoListener接收到了demoPublisher发布的消息：" + msg);
	}

}
