package com.geo.source.exception.exceptinenum;

import com.geo.source.exception.customassert.BusinessExceptionAssert;

/**
 * @author YanZhen
 * @date 2020/05/08 14:13
 **/
public enum ResponseEnum implements BusinessExceptionAssert {
    /**
     * Bad licence type
     */
    BAD_LICENCE_TYPE(7001, "Bad licence type."),

    /**
     * Licence not found
     */
    LICENCE_NOT_FOUND(7002, "Licence not found."),

    /**
     * Licence not found
     */
    SYSTEM_EXCEPTION(500, "系统测试异常！"),
    ;
    private int code;
    private String message;

    ResponseEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
