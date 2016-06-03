package com.hyj.util.Jedis;

import redis.clients.jedis.Jedis;

/**
 * Created by Administrator on 2016/5/23 0023.
 */
public class JedisUtil {

    public static Jedis createJedis() {
        return new Jedis("localhost");
    }

    public static Jedis createJedis(String host, int port) {
        return new Jedis(host, port);
    }

    public static Jedis createJedis(String host, int port, String password) {
        Jedis jedis = new Jedis(host, port);
        if (password != null && password.length() > 0) {
            jedis.auth(password);
        }
        return jedis;
    }

}
