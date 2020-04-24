package com.geo.source.redis.delaytask.timer;

import com.geo.source.redis.RedisClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import redis.clients.jedis.JedisCluster;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author YanZhen
 * @date 2020/04/24 13:34
 **/
public class DelayTaskTimer implements InitializingBean {
    private Logger logger = LoggerFactory.getLogger(DelayTaskTimer.class);
    private ScheduledThreadPoolExecutor poolExecutor = new ScheduledThreadPoolExecutor(2, r -> {
        Thread t = new Thread(r, "my-thread-" + r.hashCode());
        logger.info("{} has been created", t.getName());
        return t;
    });

    private JedisCluster redis;

    public DelayTaskTimer() {
    }

    public DelayTaskTimer(JedisCluster redis) {
        this.redis = redis;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        poolExecutor.execute(() -> {
            final DelayTaskTimer timer = new DelayTaskTimer(RedisClient.getJedisCluster());
            timer.timer();
        });
    }

    private void timer() {
        while (true) {
            // redis.


            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
