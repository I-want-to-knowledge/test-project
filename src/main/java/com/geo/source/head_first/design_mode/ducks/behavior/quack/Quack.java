package com.geo.source.head_first.design_mode.ducks.behavior.quack;

public class Quack implements QuackBehavior {

	@Override
	public void quack() {
		System.out.println("Quack quack...");
	}

}
