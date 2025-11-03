package com.smart.redis.service;

import java.util.Map;
import java.util.Optional;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisHashService<K, F, V> {

    private final RedisTemplate<K, V> redisTemplate;

    public RedisHashService(RedisTemplate<K, V> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void putField(K key, F field, V value) {
        redisTemplate.<F, V>opsForHash().put(key, field, value);
    }

    public Optional<V> getField(K key, F field) {
        return Optional.ofNullable(redisTemplate.<F, V>opsForHash().get(key, field));
    }

    public Map<F, V> getAll(K key) {
        return redisTemplate.<F, V>opsForHash().entries(key);
    }

    public void deleteField(K key, F field) {
        redisTemplate.<F, V>opsForHash().delete(key, field);
    }
}
