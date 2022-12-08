package com.wko.register;

import com.alibaba.fastjson.JSONObject;
import com.wko.common.URL;
import org.apache.commons.collections4.CollectionUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: MapRemoteRegister
 * Package: com.wko.register
 * Description:注册中心
 *
 * @Author wko
 * @Create 2022/12/7 22:23
 * @Version 1.0
 */
public class MapRemoteRegister {

    private static final JedisPool POOL;

    public static final String IP = "114.132.225.20";
    public static final Integer PORT = 8844;
    public static final String PASSWORD = "wkofu123";
    public static final Integer DATABASE_USE = 0;

    private static final int POOL_MAX_IDLE = 16;
    private static final int POOL_MAX_TOTAL = 36;
    private static final long POOL_MAX_WAIT = 50000;

    static {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setTestOnBorrow(true);
        jedisPoolConfig.setTestOnReturn(true);
        jedisPoolConfig.setMaxIdle(POOL_MAX_IDLE);
        jedisPoolConfig.setMaxTotal(POOL_MAX_TOTAL);
        jedisPoolConfig.setMaxWaitMillis(POOL_MAX_WAIT);
        POOL = new JedisPool(jedisPoolConfig, IP, PORT, 3000, PASSWORD, DATABASE_USE);
    }

    public static Jedis getConnection(JedisPool jedisPool) {
        return jedisPool.getResource();
    }

    public static void register(String interfaceName, URL url) {
        Jedis jedis = null;
        try {
            jedis = getConnection(POOL);
            List<URL> list = get(interfaceName);
            list.add(url);
            String json = JSONObject.toJSONString(list);
            jedis.set(interfaceName, json);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            assert jedis != null;
            jedis.close();
        }
    }

    public static List<URL> get(String interfaceName) {
        Jedis jedis = null;
        String json = "";
        try {
            jedis = getConnection(POOL);
            json = jedis.get(interfaceName);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            assert jedis != null;
            jedis.close();
        }
        List<URL> urls = JSONObject.parseArray(json, URL.class);
        return CollectionUtils.isEmpty(urls) ? new ArrayList<>() : urls;
    }

}
