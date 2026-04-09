package com.example.DOMap.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration // 스프링의 설정 정보 파일인 것을 알림
public class SecurityConfig {

    @Bean // BCryptPasswordEncoder객체를 스프링 컨테이너가 관리하는 객체로 등록
    public PasswordEncoder passwordEncoder() {
        // 비밀번호를 안전하게 암호화하는 함수를 사용
        return new BCryptPasswordEncoder();
    }
}
