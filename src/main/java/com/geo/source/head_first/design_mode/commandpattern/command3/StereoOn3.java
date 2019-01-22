package com.geo.source.head_first.design_mode.commandpattern.command3;

/**
 * 打开音响命令
 * @Author YanZhen
 * 2019-01-19 14:39
 */
public class StereoOn3 implements Command3 {
    private Stereo3 stereo;

    public StereoOn3(Stereo3 stereo) {
        this.stereo = stereo;
    }

    @Override
    public void execute() {
        this.stereo.on();
        this.stereo.setCD();
        int volume = 11;
        this.stereo.setVolume(volume);
    }

    @Override
    public void undo() {

    }
}
