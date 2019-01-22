package com.geo.source.head_first.design_mode.commandpattern.command1;

/**
 * 车库门打开命令
 * @author YanZhen
 * 2019-01-18 16:52
 */
public class GarageDoorOpenCommand implements Command {
    private GarageDoor garageDoor;

    public GarageDoorOpenCommand(GarageDoor garageDoor) {
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
