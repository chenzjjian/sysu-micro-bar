package com.hyj.util.Jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by Administrator on 2016/5/23 0023.
 */
public class JedisPoolUtil {
    private static JedisPool jedisPool;
    private static String HOST = "115.28.245.65";
    private static int PORT = 6379;
    private static String PASSWORD = "666666";

    private static void createJedisPool() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxWaitMillis(1000);
        jedisPoolConfig.setMaxIdle(10);
        jedisPoolConfig.setMaxTotal(10);
        jedisPool = new JedisPool(jedisPoolConfig, HOST, PORT);
    }

    private static synchronized void initJedisPool() {
        if (jedisPool == null)
            createJedisPool();
    }

    public static Jedis getJedis() {
        if (jedisPool == null)
            initJedisPool();
        Jedis jedis = jedisPool.getResource();
        if (jedis != null) {
//            jedis.auth(PASSWORD);
        }
        return jedis;
    }



}
