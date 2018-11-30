package com.geo.source.head_first.design_mode.mini_games.behavior;

/**
 * 弓箭
 *
 * @author YanZhen
 * 2018-09-29 19:39:53
 * BowAndArrowBehavior
 */
public class BowAndArrowBehavior implements WeaponBehavior {

	@Override
	public void useWeapon() {
		System.out.print("Bow and arrow ");
	}

}
