package com.geo.source.testmain.publictest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author YanZhen
 * @date 2019/11/25 09:09
 **/
public class InterfaceClassTest {
    private interface a {
        Pattern P = Pattern.compile("\\d");
    }

    public static void main(String[] args) {
        final Matcher matcher = a.P.matcher("1");
        System.out.println(matcher.find());
    }
}
