package Test;

import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @author by pepsi-wyl
 * @date 2022-04-01 19:37
 */

public class String_Redis {
    public static void main(String[] args) {
        // 认证
        Jedis jedis = getConnection();
        jedis.mset("str1", "v1", "str2", "v2", "str3", "v3");
        List<String> mget = jedis.mget("str1", "str2", "str3");
        mget.forEach(System.out::println);
    }

    public static Jedis getConnection() {
        // 连接
        Jedis jedis = new Jedis("192.168.131.130", 6379);
        // 认证
        jedis.auth("root");
        return jedis;
    }
}
