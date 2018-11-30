package com.geo.source.head_first.design_mode.ducks;

import com.geo.source.head_first.design_mode.ducks.behavior.fly.FlyNoWay;
import com.geo.source.head_first.design_mode.ducks.behavior.quack.MuteQuack;

/**
 * 模型鸭
 *
 * @author YanZhen
 * 2018-09-28 19:37:45
 * ModelDuck
 */
public class ModelDuck extends Duck {

	public ModelDuck() {
		flyBehavior = new FlyNoWay();
		quackBehavior = new MuteQuack();
	}
}
