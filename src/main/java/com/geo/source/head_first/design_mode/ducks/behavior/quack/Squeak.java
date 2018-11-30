package com.geo.source.head_first.design_mode.ducks.behavior.quack;

public class Squeak implements QuackBehavior {

	@Override
	public void quack() {
		System.out.println("Squeak squeak...");
	}

}
