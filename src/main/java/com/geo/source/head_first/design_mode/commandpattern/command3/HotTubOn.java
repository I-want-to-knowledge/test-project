package com.geo.source.head_first.design_mode.commandpattern.command3;

/**
 * 热水器开命令
 *
 * @author YanZhen
 * @since 2019-01-21 17:39
 */
public class HotTubOn implements Command3 {
    private HotTub hotTub;

    public HotTubOn(HotTub hotTub) {
        this.hotTub = hotTub;
    }

    @Override
    public void execute() {
        this.hotTub.on();
    }

    @Override
    public void undo() {
        this.hotTub.off();
    }
}
