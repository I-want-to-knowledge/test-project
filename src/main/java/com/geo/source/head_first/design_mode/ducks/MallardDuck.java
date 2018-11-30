package com.geo.source.head_first.design_mode.ducks;

import com.geo.source.head_first.design_mode.ducks.behavior.fly.FlyWithWings;
import com.geo.source.head_first.design_mode.ducks.behavior.quack.Quack;

public class MallardDuck extends Duck {
	
	public MallardDuck() {
		quackBehavior = new Quack();
		flyBehavior = new FlyWithWings();
	}

	@Override
	public void display() {
		System.out.println("I'm a real Mallard duck!");
	}

}
