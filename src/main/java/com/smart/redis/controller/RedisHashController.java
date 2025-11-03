package com.smart.redis.controller;


import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smart.redis.service.RedisHashService;

@RestController
@RequestMapping("/api/redis/hash")
public class RedisHashController {

    private final RedisHashService<String, String, Object> hashService;

    public RedisHashController(RedisHashService<String, String, Object> hashService) {
        this.hashService = hashService;
    }

    @PostMapping("/{key}/{field}")
    public String put(@PathVariable String key, @PathVariable String field, @RequestParam Object value) {
        hashService.putField(key, field, value);
        return "Field saved";
    }

    @GetMapping("/{key}")
    public Map<String, Object> getAll(@PathVariable String key) {
        return hashService.getAll(key);
    }

    @GetMapping("/{key}/{field}")
    public Object getField(@PathVariable String key, @PathVariable String field) {
        return hashService.getField(key, field).orElse("No field found");
    }

    @DeleteMapping("/{key}/{field}")
    public String deleteField(@PathVariable String key, @PathVariable String field) {
        hashService.deleteField(key, field);
        return "Field deleted";
    }
}
