package com.geo.source.head_first.design_mode.commandpattern.command3;

/**
 * 远程控制
 * @author YanZhen
 * @since 2019-01-19 15:13
 */
public class RemoteControl3 {

    private Command3[] onCommands;
    private Command3[] offCommands;
    private Command3 undoCommand;

    public RemoteControl3() {
        onCommands = new Command3[7];
        offCommands = new Command3[7];

        Command3 command = new NoCommand();
        for (int i = 0; i < 7; i++) {
            onCommands[i] = command;
            offCommands[i] = command;
        }

        undoCommand = command;
    }

    public void setCommand(int slot, Command3 onCommand, Command3 offCommand) {
        onCommands[slot] = onCommand;
        offCommands[slot] = offCommand;
    }

    public void onButtonWasPushed(int slot) {
        onCommands[slot].execute();
        undoCommand = onCommands[slot];
    }

    public void offButtonWasPushed(int slot) {
        offCommands[slot].execute();
        undoCommand = offCommands[slot];
    }

    public void undoCommand() {
        System.out.print("已经撤销，");
        undoCommand.undo();
    }

    @Override
    public String toString() {
        StringBuilder stringBuffer = new StringBuilder();
        stringBuffer.append("\n--------Remote3 Control---------\n");
        for (int i = 0; i < onCommands.length; i++) {
            stringBuffer.append("[slot3 ").append(i).append("] ").append(onCommands[i].getClass().getName()).append("    ").append(offCommands[i].getClass().getName()).append("\n");
        }
        return stringBuffer.toString();
    }

    private class NoCommand implements Command3 {
        @Override
        public void execute() {

        }

        @Override
        public void undo() {

        }
    }
}
