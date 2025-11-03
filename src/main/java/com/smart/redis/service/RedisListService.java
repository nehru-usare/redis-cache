package com.smart.redis.service;


import java.util.List;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisListService<K, V> {

    private final RedisTemplate<K, V> redisTemplate;

    public RedisListService(RedisTemplate<K, V> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void pushLeft(K key, V value) {
        redisTemplate.opsForList().leftPush(key, value);
    }

    public void pushRight(K key, V value) {
        redisTemplate.opsForList().rightPush(key, value);
    }

    public V popLeft(K key) {
        return redisTemplate.opsForList().leftPop(key);
    }

    public V popRight(K key) {
        return redisTemplate.opsForList().rightPop(key);
    }

    public List<V> getRange(K key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }
}
