package Phone;

import redis.clients.jedis.Jedis;

import java.util.Random;

/**
 * @author by pepsi-wyl
 * @date 2022-04-01 20:05
 */

public class Phone {

    public static void main(String[] args) {
        String phone = "13673330837";
        // 发送
        System.out.println(verifyCode(phone));
        // 验证
        getRedisCode(phone, "348933");
    }

    // 每个手机每天只能发送三次 验证码放到redis设置过期时间
    public static boolean verifyCode(String phoneNumber) {
        // 连接
        Jedis jedis = getConnection();

        // 生成key
        String countKey = "verifyCode" + phoneNumber + ":count";
        // 每个手机每天只能发送三次
        String count = jedis.get(countKey);
        if (count == null) jedis.setex(countKey, 24 * 60 * 60, "1");    // 首次发送   过期时间为一天 设置为1
        else if (Integer.parseInt(count) <= 2) jedis.incr(countKey);                   // 还可以发送 值递增
        else if (Integer.parseInt(count) > 2) {                                        // 超过三次 超过次数限制
            System.out.println("发送次数超过限制");    // 超过发送次数
            jedis.close(); // 关闭redis连接
            return false;  // 返回false 发送失败
        }

        // 生成验证码
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 6; i++) stringBuilder.append(random.nextInt(10));
        String code = stringBuilder.toString(); // 验证码生成
        System.out.println(code);  // 验证码

        // 生成key
        String codeKey = "verifyCode" + phoneNumber + ":code";
        // 验证码 2分钟过期
        jedis.setex(codeKey, 120, code);
        jedis.close(); // 关闭redis连接
        return true;   // 返回true 发送成功
    }

    // 验证验证码
    public static void getRedisCode(String phoneNumber, String code) {
        // 连接
        Jedis jedis = getConnection();
        String codeKey = "verifyCode" + phoneNumber + ":code";
        if (code.equals(jedis.get(codeKey))) System.out.println("success");
        else System.out.println("error");
        System.out.println(jedis.ttl(codeKey));
        jedis.close(); // 关闭redis连接
    }

    public static Jedis getConnection() {
        Jedis jedis = new Jedis("192.168.131.136", 6379);
        jedis.auth("bsy8023.00");
        return jedis;
    }

}
