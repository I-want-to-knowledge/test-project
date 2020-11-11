package com.geo.source.testmain.publictest;

import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RandomTest {

    public static void main(String[] args) {
//		m1();
        m2();
    }

    private static void m2() {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < 10000; i++) {
            String random = RandomStringUtils.random(6, "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890");
            LocalDate now = LocalDate.now();
            String yy = now.format(DateTimeFormatter.ofPattern("yy"));
            int month = now.getMonthValue();
            switch (month) {
                case 10:
                    yy += "A";
                    break;
                case 11:
                    yy += "B";
                    break;
                case 12:
                    yy += "C";
                    break;
                default:
                    yy += month;
                    break;
            }
            System.out.println(yy + random);
            map.merge(random, 1, Integer::sum);
        }

        map.forEach((k, v) -> {
            if (v > 1) {
                System.out.println(k + ":" + v);
            }
        });
    }

    private static void m1() {
        Random r = new Random();
        double d = r.nextInt(10);
        System.out.println(d);
    }
}
