package com.geo.source.head_first.design_mode.decorator.coffee;

/**
 * 牛奶调料
 *
 * @author YanZhen
 * 2018-10-29 20:54:56
 * Milk
 */
public class Milk extends CondimentDecorator {
	
	private Beverage b;
	
	public Milk(Beverage b) {
		this.b = b;
	}

	@Override
	public String getDescription() {
		return b.getDescription() + ", Milk";
	}

	@Override
	public Double cost() {
		return .10 + b.cost();
	}

}
