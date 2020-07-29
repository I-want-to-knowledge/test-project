package com.yz.testmodulegradle.controller;

import com.yz.testmodulegradle.common.BaseResult;
import com.yz.testmodulegradle.constant.TmgApiPath;
import com.yz.testmodulegradle.dto.TmgIdRequest;
import com.yz.testmodulegradle.service.DemoService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author YanZhen
 * @date 2020/07/27 09:44
 */
@RestController
public class DemoController {
    @Resource
    private DemoService demoServiceImpl;

    @PostMapping(value = TmgApiPath.DEMO, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResult<String> getString(@RequestBody TmgIdRequest request) {
        return new BaseResult<>(demoServiceImpl.getString(request));
    }
}
