package Test;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author by pepsi-wyl
 * @date 2022-04-01 19:45
 */

public class Hash_Redis {
    public static void main(String[] args) {
        Jedis jedis = getConnection();

        jedis.hset("hash1", "userName", "lisi");
        String hget = jedis.hget("hash1", "userName");
        System.out.println(hget);

        Map<String, String> map = new HashMap<String, String>();
        map.put("telphone", "13673330837");
        map.put("address", "河南省安阳市");
        map.put("email", "2322535585@qq.com");
        jedis.hmset("hash2", map);
        List<String> result = jedis.hmget("hash2", "telphone", "email","address");
        result.forEach(System.out::println);
    }

    public static Jedis getConnection() {
        // 连接
        Jedis jedis = new Jedis("192.168.131.130", 6379);
        // 认证
        jedis.auth("root");
        return jedis;
    }
}
