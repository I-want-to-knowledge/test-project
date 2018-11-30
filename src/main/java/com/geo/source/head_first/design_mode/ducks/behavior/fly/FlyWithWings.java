package com.geo.source.head_first.design_mode.ducks.behavior.fly;

/**
 * 飞行的行为
 *
 * @author YanZhen
 * 2018-09-27 20:42:59
 * FlyWithWings
 */
public class FlyWithWings implements FlyBehavior {

	@Override
	public void fly() {
		System.out.println("Fly away...");
	}

}
