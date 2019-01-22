package com.geo.source.head_first.design_mode.commandpattern.command2;

/**
 * 吊扇低档运行
 * @author YanZhen
 * 2019-01-21 16:20
 */
public class CeilingFanLow implements Command2 {
    private CeilingFan ceilingFan;
    private int prevSpeed;

    public CeilingFanLow(CeilingFan ceilingFan) {
        this.ceilingFan = ceilingFan;
    }

    @Override
    public void execute() {
        this.prevSpeed = this.ceilingFan.getSpeed();
        this.ceilingFan.low();
    }

    @Override
    public void undo() {
        CeilingFanUtils.undoCommand(prevSpeed, ceilingFan);
    }
}
