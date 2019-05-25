package com.person.erp.common.utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * @author zhuwj
 * @description
 * @since 2018/6/4
 */
@Component
public class RedisUtils {

    @Resource
    private RedisTemplate redisTemplate;

    private static RedisUtils redisUtils;

    @PostConstruct
    public void init() {
        redisUtils = this;
        redisUtils.redisTemplate = this.redisTemplate;
    }

    /**
     * 将数据放入缓存
     * @param key 键
     * @param value 值
     * @return boolean
     * @author zhuwj
     */
    public static boolean set(final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisUtils.redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 将数据放入缓存，并指定有效时间
     * @param key 键
     * @param value 值
     * @param expireTime 数值
     * @param timeUnit 时间单位
     * @return boolean
     * @author zhuwj
     */
    public static boolean set(final String key, Object value, Long expireTime, TimeUnit timeUnit) {
        boolean result = false;

        try {
            ValueOperations<Serializable, Object> operations = redisUtils.redisTemplate.opsForValue();
            operations.set(key, value, expireTime, timeUnit);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 从缓存中取出数据
     * @param key 键
     * @return java.lang.Object
     * @author zhuwj
     */
    public static Object get(final String key) {
        ValueOperations operations = redisUtils.redisTemplate.opsForValue();
        return operations.get(key);
    }

    /**
     * 判断缓存中是否存在key的数据
     * @param key 键
     * @return boolean
     * @author zhuwj
     */
    public static boolean exist(final String key) {
        return redisUtils.redisTemplate.hasKey(key);
    }

    /**
     * 将key的数据从缓存中删除
     * @param key 键
     */
    public static void
    remove(final String key) {
        try {
            if (exist(key)) {
                redisUtils.redisTemplate.delete(key);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新key的数据的生存时间
     * @author zhuwj
     * @since 2019/5/9 11:31
     * @param key 键
     * @param timeout 超时时间数
     * @param unit 单位
     * @return boolean
     */
    public static boolean expire(final String key, long timeout, TimeUnit unit) {
        return redisUtils.redisTemplate.expire(key, timeout, unit);
    }

}
