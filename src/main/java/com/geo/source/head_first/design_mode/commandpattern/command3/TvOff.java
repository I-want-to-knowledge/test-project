package com.geo.source.head_first.design_mode.commandpattern.command3;

/**
 * 电视关闭命令
 *
 * @author YanZhen
 * @since 2019-01-21 17:37
 */
public class TvOff implements Command3 {
    private Tv tv;

    public TvOff(Tv tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        this.tv.off();
    }

    @Override
    public void undo() {
        this.tv.on();
    }
}
