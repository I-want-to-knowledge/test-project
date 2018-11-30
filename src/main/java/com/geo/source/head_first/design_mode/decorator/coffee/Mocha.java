package com.geo.source.head_first.design_mode.decorator.coffee;

/**
 * 摩卡调料
 *
 * @author YanZhen
 * 2018-10-29 20:54:56
 * Milk
 */
public class Mocha extends CondimentDecorator {
	
	private Beverage b;
	
	public Mocha(Beverage b) {
		this.b = b;
	}

	@Override
	public String getDescription() {
		return b.getDescription() + ", Mocha";
	}

	@Override
	public Double cost() {
		return .20 + b.cost();
	}

}
