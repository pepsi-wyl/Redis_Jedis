package Test;

import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * @author by pepsi-wyl
 * @date 2022-04-01 19:50
 */

public class Zset_Redis {
    public static void main(String[] args) {
        Jedis jedis = getConnection();
        jedis.zadd("zset01", 100, "z3");
        jedis.zadd("zset01", 90, "l4");
        jedis.zadd("zset01", 80, "w5");
        jedis.zadd("zset01", 70, "z6");
        Set<String> zrange = jedis.zrange("zset01", 0, -1);
        zrange.forEach(System.out::println);
    }

    public static Jedis getConnection() {
        // 连接
        Jedis jedis = new Jedis("192.168.131.130", 6379);
        // 认证
        jedis.auth("root");
        return jedis;
    }
}
