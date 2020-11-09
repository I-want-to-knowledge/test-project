package com.geo.source.testmain.publictest;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

/**
 * @author YanZhen
 * @date 2020/10/12 15:24
 */
public class SystemTest {
    public static void main(String[] args) {
//        m1();
        m2();
    }

    private static void m2() {
        System.out.println(System.currentTimeMillis());
    }

    private static void m1() {
        Properties props=System.getProperties();
        System.out.println("Java的运行环境版本："+props.getProperty("java.version"));
        System.out.println("默认的临时文件路径："+props.getProperty("java.io.tmpdir"));
        System.out.println("操作系统的名称："+props.getProperty("os.name"));
        System.out.println("操作系统的构架："+props.getProperty("os.arch"));
        System.out.println("操作系统的版本："+props.getProperty("os.version"));
        System.out.println("文件分隔符："+props.getProperty("file.separator"));   //在 unix 系统中是＂／＂
        System.out.println("路径分隔符："+props.getProperty("path.separator"));   //在 unix 系统中是＂:＂
        System.out.println("行分隔符："+props.getProperty("line.separator"));   //在 unix 系统中是＂/n＂
        System.out.println("用户的账户名称："+props.getProperty("user.name"));
        System.out.println("用户的主目录："+props.getProperty("user.home"));
        System.out.println("用户的当前工作目录："+props.getProperty("user.dir"));
        System.out.println("用户的当前工作目录2："+System.getProperty("user.dir"));
        try {
            InetAddress ip4 = Inet4Address.getLocalHost();
            System.out.println("当前服务器的ip：" + ip4.getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String dir = System.getProperty("user.dir");
        String[] split = dir.split("\\\\");
        String s = split[split.length-1];
        System.out.println("项目文件夹名称：" + s);
    }
}
