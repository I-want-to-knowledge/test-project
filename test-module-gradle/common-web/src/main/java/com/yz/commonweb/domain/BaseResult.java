package com.yz.commonweb.domain;

import com.yz.commonweb.constant.ResultBaseConstant;

/**
 * 基本响应信息
 * @author YanZhen
 * @date 2020/07/27 12:32
 */
public class BaseResult<T> {
    private final int code;
    private final String message;
    private final T result;

    /**
     * 自定义结果响应
     * @param code 响应编号
     * @param message 响应消息
     * @param result 响应结果
     */
    public BaseResult(int code, String message, T result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }

    /**
     * 成功响应
     * @param result 响应结果
     */
    public BaseResult(T result) {
        this(ResultBaseConstant.RESULT_SUCCESS.getCode(), "", result);
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getResult() {
        return result;
    }
}
