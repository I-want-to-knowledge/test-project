package com.geo.source.exception;

import com.geo.source.exception.customassert.exception.BusinessException;
import com.geo.source.exception.exceptinenum.ResponseEnum;

/**
 * @author YanZhen
 * @date 2020/05/08 14:16
 **/
public class TestMain {
    public static void main(String[] args) {
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
