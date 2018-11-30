package com.geo.source.head_first.design_mode.observer.observer_weather;

/**
 * 观察者
 *
 * @author YanZhen
 * 2018-10-08 20:28:27
 * Observer
 */
public interface Observer {

	/**
	 * 2018-10-09 19:39:50
	 * @param temp 温度
	 * @param humidity 湿度
	 * @param pressure 气压
	 */
	public void update(float temp, float humidity, float pressure);
}
