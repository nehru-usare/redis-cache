package com.smart.redis.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smart.redis.service.RedisListService;

@RestController
@RequestMapping("/api/redis/list")
public class RedisListController {

    private final RedisListService<String, Object> listService;

    public RedisListController(RedisListService<String, Object> listService) {
        this.listService = listService;
    }

    @PostMapping("/left")
    public String pushLeft(@RequestParam String key, @RequestParam Object value) {
        listService.pushLeft(key, value);
        return "Left pushed: " + value;
    }

    @PostMapping("/right")
    public String pushRight(@RequestParam String key, @RequestParam Object value) {
        listService.pushRight(key, value);
        return "Right pushed: " + value;
    }

    @GetMapping("/range/{key}")
    public List<Object> getRange(@PathVariable String key,
                                 @RequestParam(defaultValue = "0") long start,
                                 @RequestParam(defaultValue = "-1") long end) {
        return listService.getRange(key, start, end);
    }

    @DeleteMapping("/pop-left/{key}")
    public Object popLeft(@PathVariable String key) {
        return listService.popLeft(key);
    }

    @DeleteMapping("/pop-right/{key}")
    public Object popRight(@PathVariable String key) {
        return listService.popRight(key);
    }
}
