package com.example.DOMap.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfing {
    @Bean
    // WebMvcConfigurer라는 인터페이스를 반환 타입으로 가짐
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {

        }
    }
}
