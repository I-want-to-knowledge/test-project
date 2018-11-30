package com.geo.source.head_first.design_mode.mini_games;

import com.geo.source.head_first.design_mode.mini_games.behavior.WeaponBehavior;

/**
 * 角色
 *
 * @author YanZhen
 * 2018-09-28 20:32:00
 * Character
 */
public abstract class Character {
	WeaponBehavior weaponBehavior;
	
	public void changeWeapon() {
		weaponBehavior.useWeapon();
	}

	abstract void fight();

	public void setWeaponBehavior(WeaponBehavior weaponBehavior) {
		this.weaponBehavior = weaponBehavior;
	}
}
