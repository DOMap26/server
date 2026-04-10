package com.example.DOMap.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class CorsConfig {

    // CORS가 필요한 이유 : 프론트, 백엔드와 같이 다른 서버라서 요청을 원래 받지 못하지만 이 클래스를 통해 요청 허용 설정
    @Bean
    // WebMvcConfigurer 인터페이스를 반환하는 메서
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            // CORS 설정을 추가하는 메서드
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // 모든 URL 허용
                        .allowedOrigins("*") // 모든 출처 허용
                        .allowedMethods("*"); // 모든 메서드 허용
            }
        };
    }
}