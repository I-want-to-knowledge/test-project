package com.geo.source.redis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Administrator
 * @date 2019/07/24 13:39
 **/
public class RedisClient {

    public static void main(String[] args) {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(60000);//设置最大连接数
        config.setMaxIdle(1000); //设置最大空闲数
        config.setMaxWaitMillis(3000);//设置超时时间
        config.setTestOnBorrow(true);


// 集群结点
        Set<HostAndPort> jedisClusterNode = new HashSet<>();
        jedisClusterNode.add(new HostAndPort("36.155.127.23", Integer.parseInt("7001")));
        jedisClusterNode.add(new HostAndPort("36.155.127.23", Integer.parseInt("7002")));
        jedisClusterNode.add(new HostAndPort("36.155.127.23", Integer.parseInt("7003")));
        jedisClusterNode.add(new HostAndPort("36.155.127.23", Integer.parseInt("7004")));
        jedisClusterNode.add(new HostAndPort("36.155.127.23", Integer.parseInt("7005")));
        jedisClusterNode.add(new HostAndPort("36.155.127.23", Integer.parseInt("7006")));
        String key = "APP:APP_ACCESS_TOKEN:123";
        JedisCluster jc = new JedisCluster(jedisClusterNode, config);
        jc.set(key, "123456");
        String value = jc.get(key);
        System.out.println(value);
        final Long del = jc.del(key);
        System.out.println("删除："+del);
    }
}
