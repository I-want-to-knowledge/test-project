package com.geo.source.head_first.design_mode.commandpattern;

import com.geo.source.head_first.design_mode.commandpattern.command1.*;
import com.geo.source.head_first.design_mode.commandpattern.command2.*;
import com.geo.source.head_first.design_mode.commandpattern.command3.*;

/**
 * 命令模式测试类
 * @Author YanZhen
 * 2019-01-18 16:39
 */
public class CommandMain {

    public static void main(String[] args) {
        // command1();
        // command2();

        // command2_1();
        command3();
    }

    /**
     * 批量执行命令
     */
    private static void command3() {
        // 遥控器
        RemoteControl3 remoteControl3 = new RemoteControl3();

        // 实体对象
        HotTub hotTub = new HotTub();
        Light3 light3 = new Light3();
        Stereo3 stereo3 = new Stereo3();
        Tv tv = new Tv();

        // 创建命令
        HotTubOff hotTubOff = new HotTubOff(hotTub);
        HotTubOn hotTubOn = new HotTubOn(hotTub);
        LightOff3 lightOff3 = new LightOff3(light3);
        LightOn3 lightOn3 = new LightOn3(light3);
        StereoOff3 stereoOff3 = new StereoOff3(stereo3);
        StereoOn3 stereoOn3 = new StereoOn3(stereo3);
        TvOff tvOff = new TvOff(tv);
        TvOn tvOn = new TvOn(tv);

        // 创建命令宏
        MacroCommand onMacroCommand = new MacroCommand(new Command3[]{hotTubOn, lightOn3, stereoOn3, tvOn});
        MacroCommand offMacroCommand = new MacroCommand(new Command3[]{hotTubOff, lightOff3, stereoOff3, tvOff});

        // 给按钮赋予功能
        remoteControl3.setCommand(0, onMacroCommand, offMacroCommand);

        remoteControl3.onButtonWasPushed(0);
        System.out.println();
        remoteControl3.offButtonWasPushed(0);

    }


    /**
     * 撤销命令按钮
     */
    private static void command2_1() {
        // 遥控器
        RemoteControl remoteControl = new RemoteControl();

        // 对象
        Light2 light2 = new Light2();
        CeilingFan ceilingFan = new CeilingFan();

        // 创建命令
        LightOnCommand2 lightOnCommand2 = new LightOnCommand2(light2);
        LightOffCommand lightOffCommand = new LightOffCommand(light2);
        CeilingFanHigh ceilingFanHigh = new CeilingFanHigh(ceilingFan);
        CeilingFanMedium ceilingFanMedium = new CeilingFanMedium(ceilingFan);
        CeilingFanLow ceilingFanLow = new CeilingFanLow(ceilingFan);
        CeilingFanOff ceilingFanOff = new CeilingFanOff(ceilingFan);

        // 写进遥控器
        remoteControl.setCommand(0, lightOnCommand2, lightOffCommand);
        remoteControl.setCommand(1, ceilingFanLow, ceilingFanOff);
        remoteControl.setCommand(2, ceilingFanMedium, ceilingFanOff);
        remoteControl.setCommand(3, ceilingFanHigh, ceilingFanOff);

        // 操作遥控器
        remoteControl.onButtonWasPushed(0);// 开灯
        remoteControl.offButtonWasPushed(0);// 关灯
        remoteControl.undoCommand();// 撤销关灯
        System.out.println();// 换行
        remoteControl.onButtonWasPushed(2);// 风扇中速运行
        remoteControl.onButtonWasPushed(3);// 风扇高速运行
        remoteControl.onButtonWasPushed(1);// 风扇低速运行
        remoteControl.undoCommand();// 撤销低速运转
        remoteControl.onButtonWasPushed(1);// 风扇低速运行
        remoteControl.offButtonWasPushed(1);// 风扇停止
        remoteControl.undoCommand();// 撤销停止运转
        remoteControl.undoCommand();// 撤销停止运转
    }

    /**
     * 远程控制系统
     */
    private static void command2() {
        RemoteControl remoteControl = new RemoteControl();

        // 操控对象
        Light2 light2 = new Light2();
        CeilingFan ceilingFan = new CeilingFan();
        GarageDoor2 garageDoor2 = new GarageDoor2();
        Stereo stereo = new Stereo();

        // 创建所有命令对象
        LightOnCommand2 lightOnCommand2 = new LightOnCommand2(light2);
        LightOffCommand lightOffCommand = new LightOffCommand(light2);

        CeilingFanHigh ceilingFanHigh = new CeilingFanHigh(ceilingFan);
        CeilingFanOff ceilingFanOff = new CeilingFanOff(ceilingFan);

        GarageDoorOpenCommand2 garageDoorOpenCommand2 = new GarageDoorOpenCommand2(garageDoor2);
        GarageDoorClose garageDoorClose = new GarageDoorClose(garageDoor2);

        StereoOnForCD stereoOnForCD = new StereoOnForCD(stereo);
        StereoOff stereoOff = new StereoOff(stereo);

        // 给按钮添加权限
        remoteControl.setCommand(0, lightOnCommand2, lightOffCommand);
        remoteControl.setCommand(1, ceilingFanHigh, ceilingFanOff);
        remoteControl.setCommand(2, garageDoorOpenCommand2, garageDoorClose);
        remoteControl.setCommand(3, stereoOnForCD, stereoOff);

        System.out.println(remoteControl);

        // 按下按钮
        remoteControl.onButtonWasPushed(0);
        remoteControl.offButtonWasPushed(0);
        remoteControl.onButtonWasPushed(1);
        remoteControl.offButtonWasPushed(1);
        remoteControl.onButtonWasPushed(2);
        remoteControl.offButtonWasPushed(2);
        remoteControl.onButtonWasPushed(3);
        remoteControl.offButtonWasPushed(3);

    }

    /**
     * 简单的远程控制
     */
    private static void command1() {
        // 简单的远程控制
        SimpleRemoteControl simpleRemoteControl = new SimpleRemoteControl();

        // 灯泡
        Light light = new Light();
        // 控制灯泡的命令
        LightOnCommand lightOnCommand = new LightOnCommand(light);
        simpleRemoteControl.setCommand(lightOnCommand);
        simpleRemoteControl.buttonWasPressed();

        // 车库门控制
        GarageDoor garageDoor = new GarageDoor();
        GarageDoorOpenCommand garageDoorOpenCommand = new GarageDoorOpenCommand(garageDoor);
        simpleRemoteControl.setCommand(garageDoorOpenCommand);
        simpleRemoteControl.buttonWasPressed();
    }

}
