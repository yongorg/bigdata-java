package com.class1926.copybigdata.utile;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.Properties;

/**
 * @author junhi
 * @date 2019/6/19 11:36
 */
public final class JedisUtil {

    private static JedisPool jedisPool;

    static{
        //加载配置文件
        InputStream is = JedisUtil.class.getClassLoader().getResourceAsStream("application.properties");
        Properties properties = new Properties();
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获取数据，配置到jedisPoolConfig
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(Integer.parseInt(properties.getProperty("maxTotal")));
        jedisPoolConfig.setMaxIdle(Integer.parseInt(properties.getProperty("maxIdle")));

        //初始化jedisPool
        jedisPool = new JedisPool(jedisPoolConfig, properties.getProperty("host"), Integer.parseInt(properties.getProperty("port")));

        try {
            //在工具类加载的时候，直接启动redis-server服务器,对中文进行解码
            String str = JedisUtil.class.getClassLoader().getResource("redis_server.bat").getPath();
            String redisPath = URLDecoder.decode(str, "utf-8");
            Runtime.getRuntime().exec(redisPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接的方法
     * @return
     */
    public static Jedis getJedis(){
        return jedisPool.getResource();
    }

    /**
     * 关闭连接的方法
     * @param jedis
     */
    public static void close(Jedis jedis){
        if(jedis != null){
            jedis.close();
        }
    }

}
