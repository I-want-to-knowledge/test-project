package com.geo.source.time.task.task.impl;

import com.geo.source.time.task.task.MinuteTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 模拟任务1
 * @author YanZhen
 * @date 2019/09/07 09:06
 **/
@Slf4j
@Service
public class MinuteTaskServiceImpl implements MinuteTaskService {
    @Override
    public void run() {
        log.info("MinuteTaskServiceImpl：模拟任务1执行！");
    }
}
