package com.geo.source.exception.customassert;

/**
 * 响应信息枚举值模板
 * @author YanZhen
 * @date 2020/05/08 13:44
 **/
public interface IResponseEnum {
    /**
     * 错误编码
     * @return 错误编码
     */
    int getCode();

    /**
     * 错误信息
     * @return 错误信息
     */
    String getMessage();
}
