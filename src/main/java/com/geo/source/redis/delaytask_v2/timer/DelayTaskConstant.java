package com.geo.source.redis.delaytask_v2.timer;

/**
 * 延迟任务-常量
 * @author YanZhen
 * @date 2020/04/16 00:21
 **/
public interface DelayTaskConstant {
    /**
     * 延迟任务Redis key
     */
    String REDIS_KEY = "TASK:DELAY_TASK:";
    /**
     * 当意外终止，系统休息30分钟
     */
    long SYSTEM_ERROR_SLEEP = 1800_000;
    /**
     * 无任务时，系统休息10分钟
     */
    long SYSTEM_NOTHING_SLEEP = 600_000;
}
