package com.geo.source.head_first.design_mode.ducks;

import com.geo.source.head_first.design_mode.ducks.behavior.fly.FlyBehavior;
import com.geo.source.head_first.design_mode.ducks.behavior.quack.QuackBehavior;

public class Duck {
	QuackBehavior quackBehavior;
	FlyBehavior flyBehavior;

	public void performQuack() {
		quackBehavior.quack();
	}

	public void performFly() {
		flyBehavior.fly();
	}

	// void quack() {
	// System.out.println("嘎嘎嘎...");
	// }

	void swim() {
		System.out.println("在水上飘着。");
	}

	void display() {

	}
	
	// void fly();
	
	public void setQuackBehavior(QuackBehavior quackBehavior) {
		this.quackBehavior = quackBehavior;
	}
	
	public void setFlyBehavior(FlyBehavior flyBehavior) {
		this.flyBehavior = flyBehavior;
	}
}
