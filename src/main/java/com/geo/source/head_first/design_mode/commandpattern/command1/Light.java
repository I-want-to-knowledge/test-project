package com.geo.source.head_first.design_mode.commandpattern.command1;

/**
 * 灯
 * @author YanZhen
 * 2019-01-18 16:26
 */
public class Light {
    void on() {
        System.out.println("灯已打开");
    }

    void off() {
        System.out.println("灯已关闭");
    }
}
