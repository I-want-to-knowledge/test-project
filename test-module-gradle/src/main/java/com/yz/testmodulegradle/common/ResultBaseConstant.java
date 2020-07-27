package com.yz.testmodulegradle.common;

/**
 * 响应结果枚举
 * @author YanZhen
 * @date 2020/07/27 12:50
 */
public enum ResultBaseConstant {
    RESULT_SUCCESS(200, "成功");

    ResultBaseConstant(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
