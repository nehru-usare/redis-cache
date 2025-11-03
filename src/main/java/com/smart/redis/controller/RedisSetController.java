package com.smart.redis.controller;

import java.util.Set;
import org.springframework.web.bind.annotation.*;
import com.smart.redis.service.RedisSetService;

@RestController
@RequestMapping("/api/redis/set")
public class RedisSetController {

    private final RedisSetService<String, Object> setService;

    public RedisSetController(RedisSetService<String, Object> setService) {
        this.setService = setService;
    }

    @PostMapping("/add")
    public String add(@RequestParam String key, @RequestParam Object value) {
        setService.add(key, value);
        return "Added to set";
    }

    @GetMapping("/{key}")
    public Set<Object> get(@PathVariable String key) {
        return setService.getMembers(key);
    }

    @GetMapping("/{key}/is-member")
    public boolean isMember(@PathVariable String key, @RequestParam Object value) {
        return setService.isMember(key, value);
    }

    @DeleteMapping("/{key}/remove")
    public String remove(@PathVariable String key, @RequestParam Object value) {
        setService.remove(key, value);
        return "Removed";
    }
}
