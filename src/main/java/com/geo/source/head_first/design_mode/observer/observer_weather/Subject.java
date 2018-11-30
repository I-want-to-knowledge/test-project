package com.geo.source.head_first.design_mode.observer.observer_weather;

/**
 * 主题
 *
 * @author YanZhen
 * 2018-10-09 19:38:01
 * Subject
 */
public interface Subject {

	/**
	 * 注册
	 *
	 * 2018-10-09 20:00:33
	 * @param o 观察者
	 */
	public void registerObserver(Observer o);
	
	/**
	 * 删除
	 *
	 * 2018-10-09 20:01:03
	 * @param o 观察者
	 */
	public void removeObserver(Observer o);
	
	/**
	 * 通知
	 *
	 * 2018-10-09 20:01:40 void
	 */
	public void notifyObserver();
}
