package com.smart.redis.controller;

import java.util.Set;
import org.springframework.web.bind.annotation.*;
import com.smart.redis.service.RedisSortedSetService;

@RestController
@RequestMapping("/api/redis/zset")
public class RedisSortedSetController {

    private final RedisSortedSetService<String, Object> zSetService;

    public RedisSortedSetController(RedisSortedSetService<String, Object> zSetService) {
        this.zSetService = zSetService;
    }

    @PostMapping("/add")
    public String add(@RequestParam String key, @RequestParam Object value, @RequestParam double score) {
        zSetService.add(key, value, score);
        return "Added to sorted set";
    }

    @GetMapping("/{key}/range")
    public Set<Object> getRange(@PathVariable String key,
                                @RequestParam(defaultValue = "0") long start,
                                @RequestParam(defaultValue = "-1") long end) {
        return zSetService.getRange(key, start, end);
    }

    @GetMapping("/{key}/score")
    public Double getScore(@PathVariable String key, @RequestParam Object value) {
        return zSetService.getScore(key, value);
    }

    @DeleteMapping("/{key}/remove")
    public String remove(@PathVariable String key, @RequestParam Object value) {
        zSetService.remove(key, value);
        return "Removed";
    }
}
