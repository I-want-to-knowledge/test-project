package com.geo.source.head_first.design_mode.decorator.io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @ClassName InputTest
 * @Author YanZhen
 * 2019-01-09 20:30
 * @Description io测试
 */
public class InputTest {

    public static void main(String[] args) {
        int c;
        try {
            LowerCaseInputStream in = new LowerCaseInputStream(
                    new BufferedInputStream(
                            new FileInputStream(
                                    new File(InputTest.class.getResource("/").getPath())
                                            .toString().replaceFirst("%20", " ").concat("\\test.txt"))));
            while ((c = in.read()) >= 0) {
                System.out.print((char) c);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
