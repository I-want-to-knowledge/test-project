package com.geo.source.head_first.design_mode.commandpattern.command3;

/**
 * 命令宏
 *
 * @author YanZhen
 * @since 2019-01-22 10:55
 */
public class MacroCommand implements Command3 {
    private Command3[] command3s;

    public MacroCommand(Command3[] command3s) {
        this.command3s = command3s;
    }

    @Override
    public void execute() {
        for (Command3 command3 : command3s) {
            command3.execute();
        }
    }

    @Override
    public void undo() {

    }
}
