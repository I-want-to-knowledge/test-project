package com.geo.source.head_first.design_mode.observer.weather;

/**
 * 主题
 *
 * @author YanZhen
 * 2018-10-08 20:28:04
 * Subject
 */
public interface Subject {

	public void registerObserver();
	public void removeObserver();
	public void notifyObserver();
}
