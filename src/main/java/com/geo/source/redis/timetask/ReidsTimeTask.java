package com.geo.source.redis.timetask;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisPubSub;

import java.util.HashSet;
import java.util.Set;

/**
 * @author YanZhen
 * @date 2019/09/05 17:11
 **/
public class ReidsTimeTask {
    // 192.168.1.49:6381
//    private static final String ADDR = "192.168.1.49";
//    private static final String  nodes = "192.168.1.49:6381,192.168.1.49:6382,192.168.1.49:6383,192.168.1.49:6384,192.168.1.49:6385,192.168.1.49:6386";
//    private static final int PORT = 6383;
//    private static JedisPool jedis = new JedisPool(nodes);
    private static RedisSub sub = new RedisSub();

    private static JedisCluster init0() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(100);
        config.setMaxIdle(50);
        config.setMinIdle(20);
        config.setMaxWaitMillis(6 * 1000);
        config.setTestOnBorrow(true);
        // Redis集群的节点集合
        Set<HostAndPort> jedisClusterNodes = new HashSet<>();
        jedisClusterNodes.add(new HostAndPort("192.168.1.49", 6381));
        jedisClusterNodes.add(new HostAndPort("192.168.1.49", 6382));
        jedisClusterNodes.add(new HostAndPort("192.168.1.49", 6383));
        jedisClusterNodes.add(new HostAndPort("192.168.1.49", 6384));
        jedisClusterNodes.add(new HostAndPort("192.168.1.49", 6385));
        jedisClusterNodes.add(new HostAndPort("192.168.1.49", 6386));
        // 根据节点集创集群链接对象
        //JedisCluster jedisCluster = newJedisCluster(jedisClusterNodes);
        // 节点，超时时间，最多重定向次数，链接池
        return new JedisCluster(jedisClusterNodes, 2000, 100,config);
    }

    public static void init(JedisCluster jedis) {
        new Thread(() -> jedis.subscribe(sub, "__keyevent@0__:expired")).start();
    }

    public static void main(String[] args) {
        JedisCluster jedis = init0();
        init(jedis);
        for (int i = 0; i < 10; i++) {
            String orderId = "OID000000" + i;
            jedis.setex(orderId, 30, orderId);
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
