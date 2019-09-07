package com.geo.source.redis.timetask;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;

/**
 * @author YanZhen
 * @date 2019/09/05 17:11
 **/
public class ReidsTimeTask {
    // 192.168.1.49:6381
    private static final String ADDR = "192.168.1.49";
    private static final int PORT = 6383;
    private static JedisPool jedis = new JedisPool(ADDR, PORT);
    private static RedisSub sub = new RedisSub();

    public static void init() {
        new Thread(() -> jedis.getResource().subscribe(sub, "__keyevent@0__:expired")).start();
    }

    public static void main(String[] args) throws InterruptedException {
        init();
        for (int i = 0; i < 10; i++) {
            String orderId = "OID000000" + i;
            jedis.getResource().setex(orderId, 3, orderId);
            System.out.println(System.currentTimeMillis() + "ms:" + orderId + "订单生成");
        }
    }

    static class RedisSub extends JedisPubSub {
        @Override
        public void onMessage(String channel, String message) {
            System.out.println(System.currentTimeMillis() + "ms:" + message + "订单取消");
        }
    }
}
