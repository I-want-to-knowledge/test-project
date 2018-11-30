package com.geo.source.head_first.design_mode.decorator.coffee;

/**
 * 底咖啡因咖啡
 *
 * @author YanZhen
 * 2018-10-29 20:37:09
 * HouseBlend
 */
public class Decaf extends Beverage {
	
	public Decaf() {
		this.description = "Espresso coffee";
	}

	@Override
	public Double cost() {
		
		return 1.05;
	}

}
