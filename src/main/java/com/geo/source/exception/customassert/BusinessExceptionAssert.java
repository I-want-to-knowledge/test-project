package com.geo.source.exception.customassert;

import com.geo.source.exception.customassert.exception.BaseException;
import com.geo.source.exception.customassert.exception.BusinessException;

/**
 * @author YanZhen
 * @date 2020/05/08 14:07
 **/
public interface BusinessExceptionAssert extends IResponseEnum, CustomAssert {
    @Override
    default BaseException newException() {
        return newException(null, null);
    }

    @Override
    default BaseException newException(Object args) {
        return newException(null, args);
    }

    @Override
    default BaseException newException(Throwable t, Object args) {
        return new BusinessException(this, args);
    }
}
