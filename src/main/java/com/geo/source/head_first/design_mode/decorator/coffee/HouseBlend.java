package com.geo.source.head_first.design_mode.decorator.coffee;

/**
 * 首选咖啡
 *
 * @author YanZhen
 * 2018-10-29 20:37:09
 * HouseBlend
 */
public class HouseBlend extends Beverage {
	
	public HouseBlend() {
		this.description = "House blend coffee";
	}

	@Override
	public Double cost() {
		
		return .89;
	}

}
