package com.smart.redis.service;


import java.util.Set;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisSortedSetService<K, V> {

    private final RedisTemplate<K, V> redisTemplate;

    public RedisSortedSetService(RedisTemplate<K, V> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void add(K key, V value, double score) {
        redisTemplate.opsForZSet().add(key, value, score);
    }

    public Set<V> getRange(K key, long start, long end) {
        return redisTemplate.opsForZSet().range(key, start, end);
    }

    public Double getScore(K key, V value) {
        return redisTemplate.opsForZSet().score(key, value);
    }

    public void remove(K key, V... values) {
        redisTemplate.opsForZSet().remove(key, (Object[]) values);
    }
}
