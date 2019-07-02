package com.class1926.copybigdata.utile;

import com.alibaba.fastjson.JSON;
import com.class1926.copybigdata.entity.MapResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author junhi
 * @date 2019/6/19 16:14
 */
public final class JackjsonToJedisUtil {

    //获取连接
    private static Jedis jedis = JedisUtil.getJedis();

    /**
     * 根据key取值
     *
     * @param key
     * @return
     */
    public static String get(String key) {
        return jedis.get(key);
    }

    /**
     * 将传入的数据序列化为json，并放到redis缓存中
     *
     * @param mapResultList
     * @param jedisKey
     */
    public static void setObject(List<MapResult> mapResultList, String jedisKey) {
        try {
            //将结果转为json数据
            ObjectMapper mapper = new ObjectMapper();
            String str = mapper.writeValueAsString(mapResultList);
            //json数据设置到redis缓存
            jedis.set(jedisKey, str);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置
     * @param Obj
     * @param jedisKey
     */
    public static void setObject2(List<Object> Obj, String jedisKey) {
        try {
            //将结果转为json数据
            ObjectMapper mapper = new ObjectMapper();
            String str = mapper.writeValueAsString(Obj);
            //json数据设置到redis缓存
            jedis.set(jedisKey, str);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public static void setObject(Object[][] obj, String jedisKey) {
        try {
            //将结果转为json数据
            ObjectMapper mapper = new ObjectMapper();
            String str = mapper.writeValueAsString(obj);
            //json数据设置到redis缓存
            jedis.set(jedisKey, str);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过key从redis中获取值
     *
     * @param jedisKey
     * @return
     */
    public static List<MapResult> getListMapResult(String jedisKey) {
        List<MapResult> mapResult = null;

        String jedisValue = jedis.get(jedisKey);
        //序列化返回时，增加泛型
        JavaType javaType = TypeFactory.defaultInstance().constructParametricType(List.class, MapResult.class);
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            mapResult = objectMapper.readValue(jedisValue, javaType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mapResult;
    }

    /**
     * 获取obj二维数组
     *  从json转为二维数组，循环赋值实现
     * @param jedisKey
     * @return
     */
    public static Object[][] getObjectArray(String jedisKey) {
        ObjectMapper objectMapper = new ObjectMapper();
        String jedisValue = jedis.get(jedisKey);
        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readTree(jedisValue);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Object[][] obj = new Object[jsonNode.size()][jsonNode.get(0).size()];
        for (int i = 0; i < jsonNode.size(); i++) {
            JsonNode jsonNodeTmp = jsonNode.get(i);
            JavaType javaType = TypeFactory.defaultInstance().constructParametricType(List.class, Object.class);
            List<Object> list = null;
            try {
                list = objectMapper.readValue(jsonNodeTmp.toString(), javaType);
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (int j = 0; j < list.size(); j++) {
                obj[i][j] = list.get(j);
            }
        }
        return obj;
    }


    /**
     *
     * @param jedisKey
     * @return
     */
    public static  List<Object> getObjectList(String jedisKey) {

        ObjectMapper mapper = new ObjectMapper();
        String str = jedis.get(jedisKey);
        JsonNode jsonNode = null;
        try {
            jsonNode = mapper.readTree(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Object> list = new ArrayList<>();

        for (int i = 0; i < jsonNode.size(); i++) {
            JsonNode jsonNodeTmp = jsonNode.get(i);
            JavaType javaType = TypeFactory.defaultInstance().constructParametricType(List.class, MapResult.class);
            List<MapResult> mapResultList = null;
            try {
                mapResultList = mapper.readValue(jsonNodeTmp.toString(), javaType);
            } catch (IOException e) {
                e.printStackTrace();
            }

            list.add(mapResultList);
        }
        return list;
    }


    /**
     *
     * @param jedisKey
     * @return
     */
    public static  List<Object> getObjectObject(String jedisKey) {

        ObjectMapper mapper = new ObjectMapper();
        String str = jedis.get(jedisKey);
        JsonNode jsonNode = null;
        try {
            jsonNode = mapper.readTree(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Object> list = new ArrayList<>();

        for (int i = 0; i < jsonNode.size(); i++) {
            JsonNode jsonNodeTmp = jsonNode.get(i);
            JavaType javaType = TypeFactory.defaultInstance().constructParametricType(List.class, Object.class);
            List<Object> objectltList = null;
            try {
               objectltList = mapper.readValue(jsonNodeTmp.toString(), javaType);
            } catch (IOException e) {
                e.printStackTrace();
            }

            list.add(objectltList);
        }
        return list;
    }


    /**
     * 获取obj二维数组
     *  从json转为二维数组，循环赋值实现
     * @param jedisKey
     * @return
     */
    public static Object[][] getObjectArrayFastJson(String jedisKey) {
        String jedisValue = jedis.get(jedisKey);
        List<Object> objectList = JSON.parseArray(jedisValue, Object.class);
        Object[][] obj = new Object[objectList.size()][JSON.parseArray(objectList.get(0).toString(), Object.class).size()];
        for (int i = 0; i < objectList.size(); i++) {
            List<Object> objectListTmp = JSON.parseArray(objectList.get(i).toString(), Object.class);
            for (int j = 0; j < objectListTmp.size(); j++) {
                obj[i][j] = objectListTmp.get(j);
            }
        }
        return obj;
    }


}
