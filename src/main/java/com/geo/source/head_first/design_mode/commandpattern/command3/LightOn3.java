package com.geo.source.head_first.design_mode.commandpattern.command3;

/**
 * 灯光打开命令
 * @author YanZhen
 * 2019-01-18 16:30
 */
public class LightOn3 implements Command3 {

    private Light3 light;

    public LightOn3(Light3 light) {
        this.light = light;
    }

    @Override
    public void execute() {
        this.light.on();
    }

    @Override
    public void undo() {
        this.light.off();
    }
}
