package com.geo.source.testmain.publictest;

/**
 * @Auther: YanZhen
 * @Date: 2018-12-29 09:46
 * @Description: 断言 测试
 **/
public class AssertTest {
    public static void main(String[] args) {
        //断言1结果为true，则继续往下执行
        assert true;
        System.out.println("断言1没有问题，Go！");

        System.out.println("\n-----------------\n");

        //断言2结果为false,程序终止
        assert false : "断言失败，此表达式的信息将会在抛出异常的时候输出！";
        System.out.println("断言2没有问题，Go！");
    }
}
