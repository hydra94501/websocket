package com.gallery.websoket.utils;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    private final StringRedisTemplate redisTemplate;

    // 使用构造方法注入 StringRedisTemplate
    public RedisUtil(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    private static RedisUtil redisUtil;

    // 初始化静态变量
    @PostConstruct
    public void init() {
        redisUtil = this;
    }

    // 字符串操作
    public static void set(String key, String value, long time) {
        redisUtil.redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
    }

    public static void set(String key, String value) {
        redisUtil.redisTemplate.opsForValue().set(key, value);
    }

    public static String get(String key) {
        return redisUtil.redisTemplate.opsForValue().get(key);
    }

    public static void delete(String key) {
        redisUtil.redisTemplate.delete(key);
    }

    public static boolean exists(String key) {
        return redisUtil.redisTemplate.hasKey(key);
    }

    public static void expire(String key, long time) {
        redisUtil.redisTemplate.expire(key, time, TimeUnit.SECONDS);
    }

    public static long getExpire(String key) {
        return redisUtil.redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    public static Long increment(String key, long delta) {
        return redisUtil.redisTemplate.opsForValue().increment(key, delta);
    }

    // 哈希操作
    public static void hSet(String key, String hashKey, String value) {
        redisUtil.redisTemplate.opsForHash().put(key, hashKey, value);
    }

    public static String hGet(String key, String hashKey) {
        return (String) redisUtil.redisTemplate.opsForHash().get(key, hashKey);
    }

    public static void hDelete(String key, String hashKey) {
        redisUtil.redisTemplate.opsForHash().delete(key, hashKey);
    }

    public static boolean hExists(String key, String hashKey) {
        return redisUtil.redisTemplate.opsForHash().hasKey(key, hashKey);
    }

    // 列表操作
    public static void lPush(String key, String value) {
        redisUtil.redisTemplate.opsForList().leftPush(key, value);
    }

    public static void rPush(String key, String value) {
        redisUtil.redisTemplate.opsForList().rightPush(key, value);
    }

    public static String lPop(String key) {
        return redisUtil.redisTemplate.opsForList().leftPop(key);
    }

    public static String rPop(String key) {
        return redisUtil.redisTemplate.opsForList().rightPop(key);
    }

    public static List<String> lRange(String key, long start, long end) {
        return redisUtil.redisTemplate.opsForList().range(key, start, end);
    }

    // 集合操作
    public static void sAdd(String key, String value) {
        redisUtil.redisTemplate.opsForSet().add(key, value);
    }

    public static void sRemove(String key, String value) {
        redisUtil.redisTemplate.opsForSet().remove(key, value);
    }

    public static Set<String> sMembers(String key) {
        return redisUtil.redisTemplate.opsForSet().members(key);
    }

    public static boolean sIsMember(String key, String value) {
        return redisUtil.redisTemplate.opsForSet().isMember(key, value);
    }

    // 有序集合操作
    public static void zAdd(String key, String value, double score) {
        redisUtil.redisTemplate.opsForZSet().add(key, value, score);
    }

    public static Set<String> zRange(String key, long start, long end) {
        return redisUtil.redisTemplate.opsForZSet().range(key, start, end);
    }

    public static Double zScore(String key, String value) {
        return redisUtil.redisTemplate.opsForZSet().score(key, value);
    }

    public static void zRemove(String key, String value) {
        redisUtil.redisTemplate.opsForZSet().remove(key, value);
    }
}
