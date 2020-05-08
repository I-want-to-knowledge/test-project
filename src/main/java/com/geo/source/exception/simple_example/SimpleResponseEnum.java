package com.geo.source.exception.simple_example;

/**
 * @author YanZhen
 * @date 2020/05/08 14:13
 **/
public enum SimpleResponseEnum implements SimpleCustomAssert {
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

    SimpleResponseEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }


    @Override
    public SimpleBaseException newException() {
        return newException(null);
    }

    @Override
    public SimpleBaseException newException(Object args) {
        return newException(null, args);
    }

    @Override
    public SimpleBaseException newException(Throwable t, Object args) {
        return new SimpleBusinessException(this, args);
    }
}
