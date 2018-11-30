package com.geo.source.head_first.design_mode.ducks.behavior.quack;

public class MuteQuack implements QuackBehavior {

	@Override
	public void quack() {
		System.out.println("Do nothing, don't quack!");
	}

}
