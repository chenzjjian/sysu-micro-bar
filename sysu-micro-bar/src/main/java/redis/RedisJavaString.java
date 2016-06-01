package redis;

import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2016/5/23 0023.
 */
public class RedisJavaString {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("115.28.245.65", 6379);
        jedis.set("hello", "黄勇进");
        System.out.println("Stored string in redis " + jedis.get("hello"));
        System.out.println("==============================");
        jedis.lpush("q-list", "黄勇进");
        jedis.lpush("q-list", "Mongodb");
        jedis.lpush("q-list", "Mysql");
        // 获取存储的数据并输出
        List<String> list = jedis.lrange("q-list", 0, 5);
        for (String str : list) {
            System.out.println(str);
        }
        System.out.println("==============================");
        Set<String> alist = jedis.keys("*");
        for (String str : alist) {
            System.out.println("list of stored keys: " + str);
        }
    }
}
