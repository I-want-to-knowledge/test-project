package com.geo.source.exception.customassert.exception;

import com.geo.source.exception.customassert.IResponseEnum;

/**
 * @author YanZhen
 * @date 2020/05/08 14:25
 **/
public class BaseException extends RuntimeException {
    /**
     * 错误码
     */
    private int errorCode;

    /**
     * 错误信息
     */
    private String errorMessage;

    /**
     * 传参
     */
    private Object errorResult;

    public BaseException(IResponseEnum resp, Object os) {
        super(resp.getMessage());
        this.errorCode = resp.getCode();
        this.errorMessage = resp.getMessage();
        this.errorResult = os;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public Object getErrorResult() {
        return errorResult;
    }
}