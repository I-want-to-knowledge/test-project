package com.geo.source.head_first.design_mode.commandpattern.command2;

/**
 * @Description 车库门打开命令
 * @Author YanZhen
 * 2019-01-18 16:52
 */
public class GarageDoorOpenCommand2 implements Command2 {
    private GarageDoor2 garageDoor;

    public GarageDoorOpenCommand2(GarageDoor2 garageDoor) {
        this.garageDoor = garageDoor;
    }

    @Override
    public void execute() {
        this.garageDoor.up();
        // this.garageDoor.lightOn();
    }

    @Override
    public void undo() {

    }
}
