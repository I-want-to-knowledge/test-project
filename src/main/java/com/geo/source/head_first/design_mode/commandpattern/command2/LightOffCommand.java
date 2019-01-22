package com.geo.source.head_first.design_mode.commandpattern.command2;

/**
 * @Description 灯关闭命令
 * @Author YanZhen
 * 2019-01-19 14:28
 */
public class LightOffCommand implements Command2 {
    private Light2 light;

    public LightOffCommand(Light2 light) {
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
