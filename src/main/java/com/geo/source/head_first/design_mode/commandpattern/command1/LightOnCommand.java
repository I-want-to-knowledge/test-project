package com.geo.source.head_first.design_mode.commandpattern.command1;

/**
 * @Description 灯光打开命令
 * @Author YanZhen
 * 2019-01-18 16:30
 */
public class LightOnCommand implements Command {

    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }

    @Override
    public void undo() {

    }
}
