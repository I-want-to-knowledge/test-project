package com.geo.source.head_first.design_mode.decorator.coffee;

/**
 * 焦炒烘焙咖啡
 *
 * @author YanZhen
 * 2018-10-29 20:37:09
 * HouseBlend
 */
public class DarkRoast extends Beverage {
	
	public DarkRoast() {
		this.description = "Dark roast coffee";
	}

	@Override
	public Double cost() {
		
		return .99;
	}

}
