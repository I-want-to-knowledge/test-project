package com.geo.source.exception.simple_example;

import com.geo.source.exception.customassert.IResponseEnum;

/**
 * 业务异常
 *
 * @author YanZhen
 * @date 2020/05/08 13:48
 **/
public class SimpleBusinessException extends SimpleBaseException {
    private static final long serialVersionUID = 1L;

    public SimpleBusinessException(SimpleResponseEnum resp) {
        super(resp, null);
    }

    public SimpleBusinessException(SimpleResponseEnum resp, Object os) {
        super(resp, os);
    }
}
