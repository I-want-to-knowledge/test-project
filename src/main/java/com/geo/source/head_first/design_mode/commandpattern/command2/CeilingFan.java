package com.geo.source.head_first.design_mode.commandpattern.command2;

/**
 * 吊扇
 * @Author YanZhen
 * 2019-01-19 14:44
 */
public class CeilingFan {
    static final int HIGH = 3;
    static final int MEDIUM = 2;
    static final int LOW = 1;
    static final int OFF = 0;
    private int speed;

    public CeilingFan() {
        this.speed = OFF;
    }

    void high() {
        speed = HIGH;
        System.out.println("吊扇" + HIGH + "挡转动！");
    }

    void medium() {
        speed = MEDIUM;
        System.out.println("吊扇" + MEDIUM + "挡转动！");
    }

    void low() {
        speed = LOW;
        System.out.println("吊扇" + LOW + "挡转动！");
    }

    void off() {
        speed = OFF;
        System.out.println("吊扇停止！");
    }

    int getSpeed() {
        return speed;
    }
}
