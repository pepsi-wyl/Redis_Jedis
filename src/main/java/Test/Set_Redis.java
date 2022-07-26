package Test;

import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * @author by pepsi-wyl
 * @date 2022-04-01 19:43
 */

public class Set_Redis {
    public static void main(String[] args) {
        Jedis jedis = getConnection();
        jedis.sadd("orders", "order02", "order03","order04");
        jedis.srem("orders", "order02");
        Set<String> smembers = jedis.smembers("orders");
        smembers.forEach((v) -> System.out.println(v));
    }

    public static Jedis getConnection() {
        Jedis jedis = new Jedis("101.43.169.194", 6379);
        jedis.auth("bsy8023.00");
        return jedis;
    }
}
