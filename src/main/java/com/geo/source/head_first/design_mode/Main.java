package com.geo.source.head_first.design_mode;

import java.util.Observable;

import com.geo.source.head_first.design_mode.decorator.coffee.Espresso;
import com.geo.source.head_first.design_mode.decorator.coffee.Mocha;
import com.geo.source.head_first.design_mode.decorator.coffee.Whip;
import com.geo.source.head_first.design_mode.ducks.Duck;
import com.geo.source.head_first.design_mode.ducks.MallardDuck;
import com.geo.source.head_first.design_mode.ducks.ModelDuck;
import com.geo.source.head_first.design_mode.ducks.RedheadDuck;
import com.geo.source.head_first.design_mode.ducks.behavior.fly.FlyRocketPowered;
import com.geo.source.head_first.design_mode.mini_games.Character;
import com.geo.source.head_first.design_mode.mini_games.King;
import com.geo.source.head_first.design_mode.mini_games.Knight;
import com.geo.source.head_first.design_mode.mini_games.Queen;
import com.geo.source.head_first.design_mode.mini_games.Troll;
import com.geo.source.head_first.design_mode.mini_games.behavior.AxeBehavior;
import com.geo.source.head_first.design_mode.mini_games.behavior.BowAndArrowBehavior;
import com.geo.source.head_first.design_mode.mini_games.behavior.KnifeBehavior;
import com.geo.source.head_first.design_mode.mini_games.behavior.SwordBehavior;
import com.geo.source.head_first.design_mode.observer.observer_weather.CurrentConditionsDisplay;
import com.geo.source.head_first.design_mode.observer.observer_weather.ForecastDisplay;
import com.geo.source.head_first.design_mode.observer.observer_weather.WeatherData;

@SuppressWarnings("unused")
public class Main {

	public static void main(String[] args) {
		// duck();
		// modelDuck();
		
		// 小游戏
		// miniGame();
		
		// ObServer
		// observer();
		
		// observerApi();
		
		decorator1();
	}

	// 装饰者模式
	private static void decorator1() {
		Espresso espresso = new Espresso();
		
		Whip whip = new Whip(new Mocha(espresso));
		System.out.println("description："+ whip.getDescription() + "\ncost：$"+whip.cost());
	}

	// 观察者模式结合api
	private static void observerApi() {
		Observable observable = new com.geo.source.head_first.design_mode.observer.observer_api.WeatherData();
		new com.geo.source.head_first.design_mode.observer.observer_api.CurrentConditionsDisplay(observable);
		observable.notifyObservers(null);
	}

	private static void observer() {
		WeatherData data = new WeatherData();// 主题
		new CurrentConditionsDisplay(data);// 订阅者
		new ForecastDisplay(data);// 订阅者
		// display.update(10, 11, 12);
		data.setMeasurements(10, 20, 30);
	}

	// 小游戏测试
	private static void miniGame() {
		System.out.println("----------国王-----------");
		King c = new King();
		c.setWeaponBehavior(new SwordBehavior());
		c.changeWeapon();
		c.fight();
		System.out.println("----------战士-----------");
		Knight k = new Knight();
		k.setWeaponBehavior(new AxeBehavior());
		k.changeWeapon();
		k.fight();
		System.out.println("----------皇后-----------");
		Queen q = new Queen();
		q.setWeaponBehavior(new BowAndArrowBehavior());
		q.changeWeapon();
		q.fight();
		System.out.println("----------刺客-----------");
		Troll t = new Troll();
		t.setWeaponBehavior(new KnifeBehavior());
		t.changeWeapon();
		t.fight();
		System.out.println("-------------------------");
	}

	// 模型鸭
	private static void modelDuck() {
		Duck duck = new MallardDuck();
		duck.performQuack();
		duck.performFly();
		
		Duck model = new ModelDuck();
		model.performQuack();
		model.performFly();
		// 加上火箭
		model.setFlyBehavior(new FlyRocketPowered());
		model.performFly();
	}

	// 鸭子
	private static void duck() {
		Duck duck = new MallardDuck();
		duck.performQuack();
		duck.performFly();
		duck = new RedheadDuck();
		duck.performQuack();
		duck.performFly();
	}

}
