package com.smart.redis.service;

import java.time.Duration;
import java.util.Optional;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisKeyValueService<K, V> {

    private final RedisTemplate<K, V> redisTemplate;

    public RedisKeyValueService(RedisTemplate<K, V> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void set(K key, V value, Duration ttl) {
        redisTemplate.opsForValue().set(key, value, ttl);
    }

    public Optional<V> get(K key) {
        return Optional.ofNullable(redisTemplate.opsForValue().get(key));
    }

    public boolean delete(K key) {
        return Boolean.TRUE.equals(redisTemplate.delete(key));
    }
}
