package com.geo.source.head_first.design_mode.commandpattern.command3;

/**
 * @Description 音响关闭命令
 * @Author YanZhen
 * 2019-01-19 14:41
 */
public class StereoOff3 implements Command3 {
    private Stereo3 stereo;

    public StereoOff3(Stereo3 stereo) {
        this.stereo = stereo;
    }

    @Override
    public void execute() {
        this.stereo.off();
    }

    @Override
    public void undo() {
        this.stereo.on();
        this.stereo.setCD();
        this.stereo.setVolume(StereoOn3.volume);
    }
}
