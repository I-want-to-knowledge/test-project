package com.geo.source.head_first.design_mode.commandpattern.command2;

/**
 * @Description 吊扇的工具类
 * @Author YanZhen
 * 2019-01-21 15:57
 */
class CeilingFanUtils {

    /**
     * 吊扇
     * 撤销命令
     * @param prevSpeed 上次速度
     * @param ceilingFan 吊扇对象
     */
    static void undoCommand(int prevSpeed, CeilingFan ceilingFan) {
        switch (prevSpeed) {
            case CeilingFan.OFF:
                ceilingFan.off();
                break;
            case CeilingFan.LOW:
                ceilingFan.low();
                break;
            case CeilingFan.MEDIUM:
                ceilingFan.medium();
                break;
            case CeilingFan.HIGH:
                ceilingFan.high();
                break;
            default:
                System.err.println("不存在的挡位！");
        }
    }
}
