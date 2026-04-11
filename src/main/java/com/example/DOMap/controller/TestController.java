package com.example.DOMap.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class TestController {
    @GetMapping("/api/test")
    public String test() {
        return "인증 성공!";
    }
}
