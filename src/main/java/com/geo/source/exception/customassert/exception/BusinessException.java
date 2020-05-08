package com.geo.source.exception.customassert.exception;

import com.geo.source.exception.customassert.IResponseEnum;

/**
 * 业务异常
 *
 * @author YanZhen
 * @date 2020/05/08 13:48
 **/
public class BusinessException extends BaseException {
    private static final long serialVersionUID = 1L;

    public BusinessException(IResponseEnum resp) {
        super(resp, null);
    }

    public BusinessException(IResponseEnum resp, Object os) {
        super(resp, os);
    }
}
