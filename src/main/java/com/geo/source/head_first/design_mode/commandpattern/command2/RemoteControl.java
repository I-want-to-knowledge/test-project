package com.geo.source.head_first.design_mode.commandpattern.command2;

/**
 * @Description 远程控制
 * @Author YanZhen
 * 2019-01-19 15:13
 */
public class RemoteControl {

    private Command2[] onCommands;
    private Command2[] offCommands;
    private Command2 undoCommand;

    public RemoteControl() {
        onCommands = new Command2[7];
        offCommands = new Command2[7];

        Command2 command = new NoCommand();
        for (int i = 0; i < 7; i++) {
            onCommands[i] = command;
            offCommands[i] = command;
        }

        undoCommand = command;
    }

    public void setCommand(int slot, Command2 onCommand, Command2 offCommand) {
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
        stringBuffer.append("\n--------Remote2 Control---------\n");
        for (int i = 0; i < onCommands.length; i++) {
            stringBuffer.append("[slot2 ").append(i).append("] ").append(onCommands[i].getClass().getName()).append("    ").append(offCommands[i].getClass().getName()).append("\n");
        }
        return stringBuffer.toString();
    }

    private class NoCommand implements Command2 {
        @Override
        public void execute() {

        }

        @Override
        public void undo() {

        }
    }
}
