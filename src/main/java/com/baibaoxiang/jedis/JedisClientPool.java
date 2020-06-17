package com.baibaoxiang.jedis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author chenlin
 */

@Component
public class JedisClientPool implements JedisClient {
    @Autowired
    JedisPool jedisPool;
    @Override
    public String set(String key, String value) {
        Jedis resource = jedisPool.getResource();
        String set = resource.set(key, value);
        resource.close();
        return set;
    }

    @Override
    public String get(String key) {
        Jedis resource = jedisPool.getResource();
        String s = resource.get(key);
        resource.close();
        return s;

    }

    @Override
    public Boolean exists(String key) {
        Jedis resource = jedisPool.getResource();
        Boolean exists = resource.exists(key);
        resource.close();
        return exists;

    }

    @Override
    public Long del(String key) {
        Jedis resource = jedisPool.getResource();
        Long del = resource.del(key);
        resource.close();
        return del;
    }

    @Override
    public Long expire(String key, int second) {
        Jedis resource = jedisPool.getResource();
        Long expire = resource.expire(key, second);
        resource.close();
        return expire;
    }

    @Override
    public Long ttl(String key) {
        Jedis resource = jedisPool.getResource();
        Long ttl = resource.ttl(key);
        resource.close();
        return ttl;
    }


    @Override
    public Long incr(String key) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.incr(key);
        jedis.close();
        return result;
    }

    @Override
    public Long hset(String key, String field, String value) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.hset(key, field, value);
        jedis.close();
        return result;
    }

    @Override
    public String hget(String key, String field) {
        Jedis jedis = jedisPool.getResource();
        String result = jedis.hget(key, field);
        jedis.close();
        return result;
    }

    @Override
    public Long hdel(String key, String... field) {
        Jedis jedis = jedisPool.getResource();
        Long hdel = jedis.hdel(key, field);
        jedis.close();
        return hdel;
    }

    @Override
    public Boolean hexists(String key, String field) {
        Jedis jedis = jedisPool.getResource();
        Boolean hexists = jedis.hexists(key, field);
        jedis.close();
        return hexists;
    }

    @Override
    public Long hincrBy(String key, String field, long value) {
        Jedis jedis = jedisPool.getResource();
        Long hincr = jedis.hincrBy(key, field, value);
        jedis.close();
        return hincr;
    }

    @Override
    public Map<String, String> hgetAll(String key) {
        Jedis jedis = jedisPool.getResource();
        Map<String, String> hgetAll = jedis.hgetAll(key);
        jedis.close();
        return hgetAll;
    }

    @Override
    public Set<String> hkeys(String key) {
        Jedis jedis = jedisPool.getResource();
        Set<String> hkeys = jedis.hkeys(key);
        jedis.close();
        return hkeys;
    }

    @Override
    public List<String> hmget(String key, String... fields) {
        Jedis jedis = jedisPool.getResource();
        List<String> hmget = jedis.hmget(key, fields);
        jedis.close();
        return hmget;
    }
}