package com.leemuzi.train.member.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 李Muzi
 * @Date 2025/2/15 17:21
 * @Description
 */
@RestController
public class TestController {
    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }
}
