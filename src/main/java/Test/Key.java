package Test;

import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * @author by pepsi-wyl
 * @date 2022-04-01 19:14
 */

public class Key {
    public static void main(String[] args) {

        // 认证
        Jedis jedis = getConnection();

        jedis.flushDB();         // 清空当前库

        // set
        jedis.set("k1", "v1");
        jedis.set("k2", "v2");
        jedis.set("k3", "v3");

        // set 过期时间
        jedis.expire("k3",10);

        // get
        System.out.println(jedis.get("k1"));
        System.out.println(jedis.get("k2"));
        System.out.println(jedis.get("k3"));

        // 当前数据库的 key 的数量
        System.out.println(jedis.dbSize());

        // keys *
        Set<String> keys = jedis.keys("*");
        keys.forEach(System.out::println);

        System.out.println(jedis.exists("k1"));
        System.out.println(jedis.del("k1"));
        System.out.println(jedis.unlink("k2"));
        System.out.println(jedis.type("k3"));
        System.out.println(jedis.ttl("k3"));
    }

    public static Jedis getConnection() {
        // 连接
        Jedis jedis = new Jedis("192.168.131.130", 6379);
        // 认证
        jedis.auth("root");
        return jedis;
    }

}
