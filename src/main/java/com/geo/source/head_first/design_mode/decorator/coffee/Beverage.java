package com.geo.source.head_first.design_mode.decorator.coffee;

/**
 * 饮料抽象类
 *
 * @author YanZhen
 * 2018-10-29 20:28:00
 * Beverage
 */
public abstract class Beverage {

	protected String description = "Unknown Beverage";
	
	public String getDescription() {
		return description;
	}
	
	public abstract Double cost();
}
