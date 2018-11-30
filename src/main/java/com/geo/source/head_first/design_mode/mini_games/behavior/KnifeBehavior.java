package com.geo.source.head_first.design_mode.mini_games.behavior;

/**
 * 匕首
 *
 * @author YanZhen
 * 2018-09-29 19:40:17
 * KnifeBehavior
 */
public class KnifeBehavior implements WeaponBehavior {

	@Override
	public void useWeapon() {
		System.out.print("Knife ");
	}

}
