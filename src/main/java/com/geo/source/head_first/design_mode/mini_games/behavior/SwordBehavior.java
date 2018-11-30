package com.geo.source.head_first.design_mode.mini_games.behavior;

/**
 * 剑
 *
 * @author YanZhen
 * 2018-09-29 19:40:49
 * SwordBehavior
 */
public class SwordBehavior implements WeaponBehavior {

	@Override
	public void useWeapon() {
		System.out.print("Sword ");
	}

}
