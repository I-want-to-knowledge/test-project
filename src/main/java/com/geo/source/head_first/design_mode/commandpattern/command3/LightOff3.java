package com.geo.source.head_first.design_mode.commandpattern.command3;

/**
 * @Description 灯关闭命令
 * @Author YanZhen
 * 2019-01-19 14:28
 */
public class LightOff3 implements Command3 {
    private Light3 light;

    public LightOff3(Light3 light) {
        this.light = light;
    }

    @Override
    public void execute() {
        this.light.off();
    }

    @Override
    public void undo() {
        this.light.on();
    }
}
