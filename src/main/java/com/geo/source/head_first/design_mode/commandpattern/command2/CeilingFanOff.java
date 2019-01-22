package com.geo.source.head_first.design_mode.commandpattern.command2;

/**
 * @Description 吊扇关闭命令
 * @Author YanZhen
 * 2019-01-19 14:32
 */
public class CeilingFanOff implements Command2 {
    private CeilingFan ceilingFan;
    private int prevSpeed;

    public CeilingFanOff(CeilingFan ceilingFan) {
        this.ceilingFan = ceilingFan;
    }

    @Override
    public void execute() {
        this.prevSpeed = this.ceilingFan.getSpeed();
        this.ceilingFan.off();
    }

    @Override
    public void undo() {
        CeilingFanUtils.undoCommand(prevSpeed, this.ceilingFan);
    }
}
