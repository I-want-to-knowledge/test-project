package com.yz.demo.service.impl;

import com.yz.demo.dto.TmgIdRequest;
import com.yz.demo.service.DemoService;
import org.springframework.stereotype.Service;

/**
 * @author YanZhen
 * @date 2020/07/27 12:41
 */
@Service
public class DemoServiceImpl implements DemoService {
    @Override
    public String getString(TmgIdRequest request) {
        return "服务器效验客户端的值为：" + request.getId();
    }
}
