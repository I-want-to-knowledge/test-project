package com.geo.source.head_first.design_mode.commandpattern.command2;

/**
 * @Description 车库门关闭命令
 * @Author YanZhen
 * 2019-01-19 14:37
 */
public class GarageDoorClose implements Command2 {
    private GarageDoor2 garageDoor;

    public GarageDoorClose(GarageDoor2 garageDoor) {
        this.garageDoor = garageDoor;
    }

    @Override
    public void execute() {
        this.garageDoor.down();
    }

    @Override
    public void undo() {

    }
}
