package com.geo.source.head_first.design_mode.commandpattern.command2;

/**
 * @Description 音响关闭命令
 * @Author YanZhen
 * 2019-01-19 14:41
 */
public class StereoOff implements Command2 {
    private Stereo stereo;

    public StereoOff(Stereo stereo) {
        this.stereo = stereo;
    }

    @Override
    public void execute() {
        this.stereo.off();
    }

    @Override
    public void undo() {

    }
}
