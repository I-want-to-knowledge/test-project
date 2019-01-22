package com.geo.source.head_first.design_mode.commandpattern.command1;

/**
 * @Description 简单的远程控制端
 * @Author YanZhen
 * 2019-01-18 16:33
 */
public class SimpleRemoteControl {
    private Command command;

    public SimpleRemoteControl() {
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public void buttonWasPressed() {
        this.command.execute();
    }
}
