package com.geo.source.testmain.publictest.lombok;

/**
 * @author YanZhen
 * @date 2020/09/29 11:06
 */
public class LombokTest {
    public static void main(String[] args) {
        LombokB lombokB = new LombokB();
        lombokB.setA("你好！");
        lombokB.setB("你好！");
        System.out.println(lombokB.getA());
        System.out.println(lombokB.getB());
        System.out.println(lombokB);
        LombokB o = new LombokB();
        o.setA("你好！");
        o.setB("来个不一样的！");
        System.out.println(lombokB.equals(o));
    }
}
