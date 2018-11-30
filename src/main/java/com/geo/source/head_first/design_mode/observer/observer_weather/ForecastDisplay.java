package com.geo.source.head_first.design_mode.observer.observer_weather;

/**
 * 布告板
 *
 * @author YanZhen
 * 2018-10-09 20:45:41
 * ForecastDisplay
 */
public class ForecastDisplay implements Observer, DisplayElement {
	private float temperature;
	private float humidity;
	private float pressure;
	
	public ForecastDisplay(Subject weatherData) {
		weatherData.registerObserver(this);
	}
	
	@Override
	public void update(float temp, float humidity, float pressure) {
		this.temperature = temp;
		this.humidity = humidity;
		this.pressure = pressure;
		display();
	}
	
	@Override
	public void display() {
		System.out.println("布告板信息：温度-" + temperature + "/湿度-" + humidity + "/气压-" + pressure);
	}

}
