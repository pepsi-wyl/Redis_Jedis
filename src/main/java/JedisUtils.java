import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author by pepsi-wyl
 * @date 2022-07-20 20:00
 */

public class JedisUtils {
    private static volatile JedisPool jedisPool;

    private static final String host = "192.168.131.130";
    private static final Integer port = 6379;
    private static final String password = "root";

    public JedisUtils() {
    }

    private static JedisPool getJedisPoolInstance() {
        if (null == jedisPool) {
            synchronized (JedisPool.class) {
                if (null == jedisPool) {
                    JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
                    jedisPoolConfig.setMaxIdle(200);
                    jedisPoolConfig.setMaxIdle(32);
                    jedisPoolConfig.setMaxWaitMillis(100 * 1000);
                    jedisPoolConfig.setBlockWhenExhausted(true);
                    jedisPoolConfig.setTestOnBorrow(true);
                    jedisPool = new JedisPool(jedisPoolConfig, host, port, 6000, password);
                }
            }
        }
        return jedisPool;
    }

    public static Jedis getJedis() {
        return getJedisPoolInstance().getResource();
    }

    public static void main(String[] args) {
        Jedis jedis = getJedis();
        System.out.println(jedis.ping());
    }

}
