package com.geo.source.head_first.design_mode.commandpattern.command3;

/**
 * @Description 音响
 * @Author YanZhen
 * 2019-01-19 14:45
 */
public class Stereo3 {

    void on() {
        System.out.println("音响打开！");
    }

    void off() {
        System.out.println("音响关闭！");
    }

    void setCD() {
        System.out.println("CD已经放入！");
    }

    void setDVD() {
        System.out.println("DVD已经放入！");
    }

    void setRadio() {
        System.out.println("打开收音机！");
    }

    void setVolume(int num) {
        System.out.println("音量设置：" + num);
    }
}
