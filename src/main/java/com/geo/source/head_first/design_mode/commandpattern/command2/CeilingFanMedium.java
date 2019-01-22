package com.geo.source.head_first.design_mode.commandpattern.command2;

/**
 * @Description 吊扇运行
 * @Author YanZhen
 * 2019-01-19 14:30
 */
public class CeilingFanMedium implements Command2 {
    private CeilingFan ceilingFan;
    private int prevSpeed;

    public CeilingFanMedium(CeilingFan ceilingFan) {
        this.ceilingFan = ceilingFan;
    }

    @Override
    public void execute() {
        prevSpeed = this.ceilingFan.getSpeed();
        this.ceilingFan.medium();
    }

    @Override
    public void undo() {
        CeilingFanUtils.undoCommand(prevSpeed, this.ceilingFan);
    }
}
