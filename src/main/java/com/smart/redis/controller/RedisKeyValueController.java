package com.smart.redis.controller;


import java.time.Duration;
import org.springframework.web.bind.annotation.*;
import com.smart.redis.service.RedisKeyValueService;

@RestController
@RequestMapping("/api/redis/kv")
public class RedisKeyValueController {

    private final RedisKeyValueService<String, Object> kvService;

    public RedisKeyValueController(RedisKeyValueService<String, Object> kvService) {
        this.kvService = kvService;
    }

    @PostMapping("/set")
    public String set(@RequestParam String key, @RequestParam Object value) {
        kvService.set(key, value, Duration.ofMinutes(10));
        return "Stored key: " + key;
    }

    @GetMapping("/get/{key}")
    public Object get(@PathVariable String key) {
        return kvService.get(key).orElse("Not found");
    }

    @DeleteMapping("/delete/{key}")
    public String delete(@PathVariable String key) {
        return kvService.delete(key) ? "Deleted" : "Missing key";
    }
}
