package com.geo.source.head_first.design_mode.commandpattern.command2;

/**
 * @Description 灯光打开命令
 * @Author YanZhen
 * 2019-01-18 16:30
 */
public class LightOnCommand2 implements Command2 {

    private Light2 light;

    public LightOnCommand2(Light2 light) {
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
