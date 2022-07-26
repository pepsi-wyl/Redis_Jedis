import redis.clients.jedis.*;

/**
 * @author by pepsi-wyl
 * @date 2022-04-01 18:56
 */

public class T {
    public static void main(String[] args) {
        // 连接
        Jedis jedis = new Jedis("192.168.131.130", 6379);
        // 认证
        jedis.auth("root");
        // 选择库 默认为0号库
        jedis.select(0);
        System.out.println(jedis.ping());
        // 关闭连接
        jedis.close();
    }
}
