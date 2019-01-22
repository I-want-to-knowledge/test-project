package com.geo.source.head_first.design_mode.commandpattern.command2;

/**
 * @Description 打开音响命令
 * @Author YanZhen
 * 2019-01-19 14:39
 */
public class StereoOnForCD implements Command2 {
    private Stereo stereo;

    public StereoOnForCD(Stereo stereo) {
        this.stereo = stereo;
    }

    @Override
    public void execute() {
        this.stereo.on();
        this.stereo.setCD();
        this.stereo.setVolume(11);
    }

    @Override
    public void undo() {

    }
}
