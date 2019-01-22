package com.geo.source.head_first.design_mode.commandpattern.command3;

/**
 * 热水器关命令
 *
 * @author YanZhen
 * @since 2019-01-21 17:39
 */
public class HotTubOff implements Command3 {

    private HotTub hotTub;

    public HotTubOff(HotTub hotTub) {
        this.hotTub = hotTub;
    }

    @Override
    public void execute() {
        this.hotTub.off();
    }

    @Override
    public void undo() {
        this.hotTub.on();
    }
}
