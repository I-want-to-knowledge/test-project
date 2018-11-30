package com.geo.source.head_first.design_mode.ducks.behavior.fly;

/**
 * 火箭助推器飞行
 *
 * @author YanZhen
 * 2018-09-28 19:58:05
 * FlyRocketPowered
 */
public class FlyRocketPowered implements FlyBehavior {

	@Override
	public void fly() {
		System.out.println("I'm flying with a rocket");
	}

}
