package com.yz.demo.service;

import com.yz.demo.dto.TmgIdRequest;

/**
 * @author YanZhen
 * @date 2020/07/27 12:41
 */
public interface DemoService {
    /**
     * 获取一个字符串
     * @param request 请求的id
     * @return 字符串
     */
    String getString(TmgIdRequest request);
}