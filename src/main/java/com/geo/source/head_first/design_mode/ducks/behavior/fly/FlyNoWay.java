package com.geo.source.head_first.design_mode.ducks.behavior.fly;

/**
 * 不会飞的行为
 *
 * @author YanZhen
 * 2018-09-27 20:43:21
 * FlyNoWay
 */
public class FlyNoWay implements FlyBehavior {

	@Override
	public void fly() {
		System.out.println("Do nothing, can't fly!");
	}

}
