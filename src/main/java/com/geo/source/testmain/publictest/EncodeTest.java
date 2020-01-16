package com.geo.source.testmain.publictest;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @author YanZhen
 * @date 2019/12/24 14:20
 **/
public class EncodeTest {
    public static void main(String[] args) {
        String s = "%2B";
        String s2;
        try {
            s2 = URLDecoder.decode(s, "UTF-8");
            System.out.println(s2);
            final String s1 = URLEncoder.encode("=", "UTF-8");
            System.out.println(s1);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
