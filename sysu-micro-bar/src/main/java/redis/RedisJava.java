package redis;

import redis.clients.jedis.Jedis;

/**
 * Created by Administrator on 2016/5/23 0023.
 */
public class RedisJava {
    public static void main(String[] args) {
        // 连接阿里云服务器的Redis服务
        Jedis jedis = new Jedis("115.28.245.65", 6379);
        jedis.set("666", "黄勇进");

        // 查看服务器是否运行
        System.out.println("Server is Running " + jedis.ping());
        String value = jedis.get("666");
        System.out.println("成功连接" + value);

    }
}
