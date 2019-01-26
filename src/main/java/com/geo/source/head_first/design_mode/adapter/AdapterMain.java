package com.geo.source.head_first.design_mode.adapter;

import com.geo.source.head_first.design_mode.adapter.simple_adapter.Duck;
import com.geo.source.head_first.design_mode.adapter.simple_adapter.MallardDuck;
import com.geo.source.head_first.design_mode.adapter.simple_adapter.TurkeyAdapter;
import com.geo.source.head_first.design_mode.adapter.simple_adapter.WildTurkey;

/**
 * 适配器测试
 *
 * @author YanZhen
 * @since 2019-01-23 10:55
 */
public class AdapterMain {

    public static void main(String[] args) {
        adapter1();
    }

    private static void adapter1() {
        Duck mallardDuck = new MallardDuck();
        mallardDuck.quack();
        mallardDuck.fly();
        System.out.println();
        Duck turkeyAdapter = new TurkeyAdapter(new WildTurkey());
        turkeyAdapter.quack();
        turkeyAdapter.fly();
    }
}
