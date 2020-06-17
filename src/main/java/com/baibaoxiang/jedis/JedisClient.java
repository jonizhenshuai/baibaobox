package com.baibaoxiang.jedis;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author chenlin
 */
public interface JedisClient {
    String set(String key, String value);
    String get(String key);
    Boolean exists(String key);
    Long del(String key);
    Long expire(String key, int second);
    Long ttl(String key);
    Long incr(String key);
    Long hset(String key, String file, String value);
    String hget(String key, String filed);
    Long hdel(String key, String... field);//删除hkey
    Boolean hexists(String key, String field);
    Long hincrBy(String key, String field, long value);
    Map<String, String> hgetAll(String key);
    Set<String> hkeys(String key);
    List<String> hmget(String key, String... fields);

}
