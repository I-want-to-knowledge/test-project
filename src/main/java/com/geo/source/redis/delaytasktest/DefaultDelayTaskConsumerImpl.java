package com.geo.source.redis.delaytasktest;

import com.geo.source.redis.delaytask.DelayTaskConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author YanZhen
 * @date 2020/04/30 13:45
 **/
public class DefaultDelayTaskConsumerImpl implements DelayTaskConsumer {
    private final Logger logger = LoggerFactory.getLogger(DefaultDelayTaskConsumerImpl.class);

    @Override
    public void consumer(Map<String, Object> params) {
        logger.info("就打印一句话，参数是：{}，执行时间是{}！", params, LocalDateTime.now());
    }
}
