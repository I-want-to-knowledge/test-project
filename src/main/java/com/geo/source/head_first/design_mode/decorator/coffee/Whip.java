package com.geo.source.head_first.design_mode.decorator.coffee;

/**
 * 豆浆调料
 *
 * @author YanZhen
 * 2018-10-29 20:54:56
 * Milk
 */
public class Whip extends CondimentDecorator {
	
	private Beverage b;
	
	public Whip(Beverage b) {
		this.b = b;
	}

	@Override
	public String getDescription() {
		return b.getDescription() + ", Whip";
	}

	@Override
	public Double cost() {
		return .10 + b.cost();
	}

}
