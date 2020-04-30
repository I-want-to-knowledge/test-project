package com.geo.source.redis;

import com.geo.source.redis.delaytask.DelayTaskProducer;
import com.geo.source.redis.delaytask.timer.DelayTaskTimer;
import com.geo.source.redis.delaytasktest.DefaultDelayTaskConsumerImpl;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Administrator
 * @date 2019/07/24 13:39
 **/
public class RedisClient {

    public static void main(String[] args) {
//        m1();
        m2();
    }

    /**
     * 延迟任务测试
     */
    private static void m2() {
//        timer();
        producerTask();
    }

    /**
     * 定时器，专门消费延迟任务
     */
    private static void timer() {
        // 运转定时器
        final DelayTaskTimer timer = new DelayTaskTimer();
        try {
            timer.afterPropertiesSet();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 生产一个延迟任务
     */
    private static void producerTask() {
        // 传输的参数
        Map<String, Object> map = new HashMap<>(4);
        map.put("1", "a");
        map.put("2", "b");
        map.put("3", "c");
        map.put("4", "d");

        System.out.println("生产任务的时间：" + LocalDateTime.now());
        // 生产一个任务
        final DelayTaskProducer producer = new DelayTaskProducer(getJedisCluster());
        producer.producer(DefaultDelayTaskConsumerImpl.class, map, LocalDateTime.now().plusSeconds(60));
    }

    /**
     * 链接测试
     */
    private static void m1() {
        JedisCluster jc = getJedisCluster();
        String key = "APP:APP_ACCESS_TOKEN:123";
        jc.set(key, "123456");
        String value = jc.get(key);
        System.out.println(value);
        final Long del = jc.del(key);
        System.out.println("删除："+del);
    }

    public static JedisCluster getJedisCluster() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(60000);//设置最大连接数
        config.setMaxIdle(1000); //设置最大空闲数
        config.setMaxWaitMillis(3000);//设置超时时间
        config.setTestOnBorrow(true);


        // 集群结点
        Set<HostAndPort> jedisClusterNode = new HashSet<>();
        jedisClusterNode.add(new HostAndPort("192.168.1.49", 6381));
        jedisClusterNode.add(new HostAndPort("192.168.1.49", 6382));
        jedisClusterNode.add(new HostAndPort("192.168.1.49", 6383));
        jedisClusterNode.add(new HostAndPort("192.168.1.49", 6384));
        jedisClusterNode.add(new HostAndPort("192.168.1.49", 6385));
        jedisClusterNode.add(new HostAndPort("192.168.1.49", 6386));
        return new JedisCluster(jedisClusterNode, config);
    }
}
