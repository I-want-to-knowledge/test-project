package com.geo.source.head_first.design_mode.observer.observer_api;

import java.util.Observable;

/**
 * 天气数据
 *
 * @author YanZhen
 * 2018-10-24 19:42:46
 * WeatherData
 */
public class WeatherData extends Observable {
	private float temperature = 10;
	private float humidity = 20;
	private float pressure = 30;
	
	float getTemperature() {
		return temperature;
	}
	
	float getHumidity() {
		return humidity;
	}
	
	float getPressure() {
		return pressure;
	}
	
	@Override
	public void notifyObservers(Object arg) {
		super.setChanged();
		super.notifyObservers(arg);
	}
}
