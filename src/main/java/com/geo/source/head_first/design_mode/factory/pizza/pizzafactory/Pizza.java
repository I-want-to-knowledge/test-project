package com.geo.source.head_first.design_mode.factory.pizza.pizzafactory;

/**
 * @ClassName Pizza
 * @Author YanZhen
 * 2019-01-10 20:36
 * @Description pizza
 */
public interface Pizza {
    /**
     * 准备
     */
    void prepare();

    /**
     * 烘烤
     */
    void bake();

    /**
     * 切片
     */
    void cut();

    /**
     * 装盒
     */
    void box();
}
