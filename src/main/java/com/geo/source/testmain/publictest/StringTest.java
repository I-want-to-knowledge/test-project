package com.geo.source.testmain.publictest;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@SuppressWarnings("unused")
public class StringTest {

    public static void main(String[] args) {
        // method1();
        // method2();
        // method4();
        // method5();
//		method6();
        // method7("0001");
        /*String name = "123";
        String a = "'Pizza3':{'name':'" + name + "'}";
        String substring = a.substring(1, 4);
        System.out.println("结果1：" + substring);
        String substring1 = substring.substring(2, 2);
        System.out.println("结果2：" + substring1);*/
        // System.out.println(new Integer(1).equals(1));
        /*System.out.println("Windows 7".startsWith("Windows"));*/
        // m8();
        // m9();
//        m10();
        // m11();
        // m12();
        // m13();
//        m14();
//        m15();
//        m16();
//        m17();
//        m18();
//        m19();
        m20();
    }

    private static void m20() {
        String a = "ABCDEFG";
        System.out.println(a.substring(0, a.length()-1));
        System.out.println(a.replace("G", ""));
    }

    private static void m19() {
        Boolean a = null;
        if (a) {
            System.out.println(a);
        } else {
            System.out.println(a);
        }
    }

    private static void m18() {
        final DecimalFormat format = new DecimalFormat("#0.##%");
        float a = 999.111111f;
        float b = 999.996f;
        System.out.println(format.format(a));
        System.out.println(format.format(b));
//        Long a = null;
//        System.out.println(Objects.toString(a, ""));
    }

    private static void m17() {
        System.out.println("UTF_8 : " + ("你好123".getBytes(StandardCharsets.UTF_8).length));
        System.out.println("UTF_16 : " + ("你好123".getBytes(StandardCharsets.UTF_16).length));
        System.out.println("ISO_8859_1 : " + ("你好123".getBytes(StandardCharsets.ISO_8859_1).length));
        System.out.println("US_ASCII : " + ("你好123".getBytes(StandardCharsets.US_ASCII).length));
        System.out.println("UTF_16BE : " + ("你好123".getBytes(StandardCharsets.UTF_16BE).length));
        System.out.println("UTF_16LE : " + ("你好123".getBytes(StandardCharsets.UTF_16LE).length));
        try {
            System.out.println("GBK : " + ("你好123".getBytes("GBK").length));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        System.out.println("app_order_insurance_schedule".length());
    }

    private static void m16() {
        Long orgPartnerStoreId = null;
        System.out.println(String.valueOf(orgPartnerStoreId));
    }

    private static void m15() {
        System.out.println(new BigDecimal("23.0000003").longValue());
    }

    private static void m14() {
        String a = null;
        System.out.println("null".equals(a));

        String b = "aa";
        System.out.println(b.equals("aa"));
        System.out.println(b == "aa");
    }

    private static void m13() {
        final byte[] bytes = "first commit!(啊)".getBytes(StandardCharsets.UTF_8);
        System.out.println("byte = " + Arrays.toString(bytes));
        System.out.println("string = " + new String(bytes, StandardCharsets.UTF_8));
    }

    private static void m12() {
        System.out.println(" ".hashCode());
        System.out.println("1".hashCode());
        System.out.println("1.0".hashCode());
    }

    private static void m11() {
        final Optional<BigDecimal> optionalBigDecimal = Optional.of(new BigDecimal(0));
        optionalBigDecimal.filter(bigDecimal1 -> bigDecimal1.equals(BigDecimal.ZERO))
                .map(bigDecimal -> true).ifPresent(System.out::println);
        optionalBigDecimal.ifPresent(System.out::println);
        // System.out.println(bigDecimal);
    }

    /**
     * 处理器数量获取
     */
    private static void m10() {
        System.out.println(Runtime.getRuntime().availableProcessors());
    }

    /**
     * 全部转大写
     */
    private static void m9() {
        Stream<String> stringStream = Stream.of("Java 8", "C#", "JavaScript");
        stringStream.map(String::toUpperCase).forEach(System.out::println);
    }

    private static void m8() {
        String a = "A";
        char b = 65;
        System.out.println(a.getBytes().length);
        System.out.println(a.chars().max().orElse(0));
        System.out.println(b);
    }


    private static void method7(String count) {
        System.out.println(Integer.parseInt(count));
        while (count.length() > 1 && "0".equals(count.substring(0, 1))) {
            count = count.substring(1);
            System.out.println("长度：" + count.length());
        }

        System.out.println(count);
    }

    private static void method6() {
        String body = "{var globalRepeatSubmitToken = 'x000000000000000000000000000000x',"
                + "'key_check_isChange':'X111111111111111111111111111111111111111111111111111111X2'}";

        Pattern pattern = Pattern
                .compile("var globalRepeatSubmitToken = '[0-9|a-z]{32}");
        Pattern pattern2 = Pattern
                .compile("'key_check_isChange':'[0-9|A-Z]{56,59}");
        Matcher matcher = pattern.matcher(body);
        Matcher matcher2 = pattern2.matcher(body);
        while (matcher.find()) {
            System.out.println(matcher.group().replaceFirst("var globalRepeatSubmitToken = '", ""));
        }
        while (matcher2.find()) {
            System.out.println(matcher2.group().replaceFirst("'key_check_isChange':'", ""));
        }
    }

    private static void method5() {
        String a = "--";
        Object o = "--";
        System.out.println(a.equals(o));
    }

    private static void method4() {
        // System.out.println(DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH));
    }

    private static void method2() {
        Letter a = new Letter();
        a.setA("11");
        method3(a);
        System.out.println(a.toString());
    }

    private static void method3(Letter a) {
        a.setA("22");
    }

    /**
     * 2018-10-22 14:20:49 void
     */
    private static void method1() {
        Letter l = new Letter();
        try {
            System.out.println(l.getClass().getMethod("getA", String.class));
        } catch (NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        }
    }

}
