package com.geo.source.redis.delaytask.timer;

import com.alibaba.fastjson.JSONObject;
import com.geo.source.redis.RedisClient;
import com.geo.source.redis.delaytask.constant.Constant;
import com.geo.source.redis.delaytask.DelayTaskConsumer;
import com.geo.source.redis.delaytask.dto.DelayTaskDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import redis.clients.jedis.JedisCluster;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Set;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @author YanZhen
 * @date 2020/04/24 13:34
 **/
public class DelayTaskTimer implements InitializingBean {
    private final Logger logger = LoggerFactory.getLogger(DelayTaskTimer.class);
    private final ScheduledThreadPoolExecutor poolExecutor = new ScheduledThreadPoolExecutor(2, r -> {
        Thread t = new Thread(r, "my-thread-" + r.hashCode());
        logger.info("{} has been created", t.getName());
        return t;
    });

    private JedisCluster redis;

    private DelayTaskTimer() {
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
            final Set<String> set = redis.zrangeByScore(Constant.REDIS_KEY, 0, LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8")));
            if (set != null && set.size() > 0) {
                for (String v : set) {
                    poolExecutor.execute(() -> {
                        final Long zrem = redis.zrem(Constant.REDIS_KEY, v);
                        if (zrem != null && zrem > 0) {
                            final DelayTaskDto dto = JSONObject.parseObject(v, DelayTaskDto.class);
                            try {
                                final DelayTaskConsumer consumer = dto.getClazz().newInstance();
                                consumer.consumer(dto.getParams());
                            } catch (Exception e) {
                                logger.error("参数为（{}），初始化消费者时出现异常，延迟任务执行失败！", v, e);
                            }
                        } else {
                            logger.error("参数为（{}），Redis中该值已经不存在，重复删除！", v);
                        }
                    });
                }
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
