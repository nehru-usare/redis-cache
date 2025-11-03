package com.smart.redis.service;


import java.util.Set;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisSetService<K, V> {

    private final RedisTemplate<K, V> redisTemplate;

    public RedisSetService(RedisTemplate<K, V> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void add(K key, V... values) {
        redisTemplate.opsForSet().add(key, values);
    }

    public Set<V> getMembers(K key) {
        return redisTemplate.opsForSet().members(key);
    }

    public boolean isMember(K key, V value) {
        return Boolean.TRUE.equals(redisTemplate.opsForSet().isMember(key, value));
    }

    public void remove(K key, V... values) {
        redisTemplate.opsForSet().remove(key, (Object[]) values);
    }
}
