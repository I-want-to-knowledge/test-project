package com.geo.source.head_first.design_mode.commandpattern.command3;

/**
 * 命令
 * @Author YanZhen
 * 2019-01-18 16:24
 */
public interface Command3 {

    /**
     * 执行命令
     */
    void execute();

    /**
     * 撤销
     */
    void undo();
}
