package com.geo.source.head_first.design_mode.observer.observer_api;

import java.util.Observable;
import java.util.Observer;

public class CurrentConditionsDisplay implements Observer, DisplayElement {
	private float temperature;
	private float humidity;
	// private Observable ob;
	
	public CurrentConditionsDisplay(Observable ob) {
		// this.ob = ob;
		ob.addObserver(this);// 注册观察者
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof WeatherData) {
			WeatherData w = (WeatherData) o;
			this.humidity = w.getHumidity();
			this.temperature = w.getTemperature();
			display();
		}
	}

	@Override
	public void display() {
		System.out.println("Current conditions: " + temperature + "F degrees and " + humidity + "% humidity");
	}

}
