package com.geo.source.head_first.design_mode.observer.event_listener.demo1;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.geo.source.head_first.design_mode.observer.EventListener.demo1"})
public class EventConfig {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(EventConfig.class);
		DemoPublisher publisher = context.getBean(DemoPublisher.class);
		publisher.published();
		context.close();
	}

}
