package com.geo.source.head_first.design_mode.observer.weather;

public class WeatherData {

	// 气温
	public double getTemperature() {
		return 25;
	}
	
	// 湿度
	public double getHumidity() {
		return 60;
	}
	
	// 气压（-1下降	0不变	1上升）
	public double getPressure() {
		return -1;
	}
	
	// 更新显示屏
	public void measurementsChanged() {
		
	}
}
