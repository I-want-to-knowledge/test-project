package com.geo.source.head_first.design_mode.decorator.coffee;

/**
 * 浓缩咖啡
 *
 * @author YanZhen
 * 2018-10-29 20:37:09
 * HouseBlend
 */
public class Espresso extends Beverage {
	
	public Espresso() {
		this.description = "Espresso coffee";
	}

	@Override
	public Double cost() {
		
		return 1.99;
	}

}
