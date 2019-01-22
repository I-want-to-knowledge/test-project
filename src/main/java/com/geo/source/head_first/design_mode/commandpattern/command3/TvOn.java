package com.geo.source.head_first.design_mode.commandpattern.command3;

/**
 * 电视开命令
 *
 * @author YanZhen
 * @since 2019-01-21 17:35
 */
public class TvOn implements Command3 {
    private Tv tv;

    public TvOn(Tv tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        this.tv.on();
    }

    @Override
    public void undo() {

    }
}
