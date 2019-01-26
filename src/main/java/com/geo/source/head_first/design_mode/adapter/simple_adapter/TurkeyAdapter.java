package com.geo.source.head_first.design_mode.adapter.simple_adapter;

/**
 * 火鸡适配器
 *
 * @author YanZhen
 * @since 2019-01-23 10:46
 */
public class TurkeyAdapter implements Duck {
    private Turkey turkey;

    public TurkeyAdapter(Turkey turkey) {
        this.turkey = turkey;
    }

    @Override
    public void quack() {
        this.turkey.gobble();
    }

    @Override
    public void fly() {
        for (int i = 0; i < 5; i++) {
            this.turkey.fly();
        }
    }
}
