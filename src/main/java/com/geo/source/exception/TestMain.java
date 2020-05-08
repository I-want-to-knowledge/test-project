package com.geo.source.exception;

import com.geo.source.exception.customassert.exception.BusinessException;
import com.geo.source.exception.exceptinenum.ResponseEnum;
import com.geo.source.exception.simple_example.SimpleBusinessException;
import com.geo.source.exception.simple_example.SimpleResponseEnum;

/**
 * @author YanZhen
 * @date 2020/05/08 14:16
 **/
public class TestMain {
    public static void main(String[] args) {
//        v1();
        v2();
    }

    /**
     * 异常抛出第二版
     */
    private static void v2() {
        try {
            SimpleResponseEnum.LICENCE_NOT_FOUND.assertNotNull(123);
            SimpleResponseEnum.SYSTEM_EXCEPTION.assertNotNull(null, "123");
        } catch (Exception e) {
            SimpleBusinessException ex = (SimpleBusinessException) e;
            System.out.println(ex.getErrorCode() + " : " + ex.getErrorMessage());
            System.out.println("传入的参数为：" + ex.getErrorResult());
        }
    }

    /**
     * 异常抛出第一版
     */
    private static void v1() {
        try {
            ResponseEnum.LICENCE_NOT_FOUND.assertNotNull(123);
            ResponseEnum.SYSTEM_EXCEPTION.assertNotNull(null, "123");
        } catch (Exception e) {
            BusinessException ex = (BusinessException) e;
            System.out.println(ex.getErrorCode() + " : " + ex.getErrorMessage());
            System.out.println("传入的参数为：" + ex.getErrorResult());
        }
    }
}
