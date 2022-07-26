package Test;

import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @author by pepsi-wyl
 * @date 2022-04-01 19:42
 */

public class List_Redis {
    public static void main(String[] args) {
        Jedis jedis = getConnection();
        jedis.lpush("mylist", "java", "php", "c++");
        List<String> list = jedis.lrange("mylist", 0, -1);
        list.forEach(System.out::println);
    }

    public static Jedis getConnection() {
        // 连接
        Jedis jedis = new Jedis("192.168.131.130", 6379);
        // 认证
        jedis.auth("root");
        return jedis;
    }
}
