package com.example.DOMap.config;

import com.example.DOMap.jwt.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // 스프링의 설정 정보 파일인 것을 알림
public class SecurityConfig {

    @Bean // BCryptPasswordEncoder객체를 스프링 컨테이너가 관리하는 객체로 등록
    public PasswordEncoder passwordEncoder() {
        // 비밀번호를 안전하게 암호화하는 함수를 사용
        return new BCryptPasswordEncoder();
    }

    @Bean
    // SecurityFilterChain : 보안 설정 모음
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // CSRF끔
                .authorizeRequests(auth -> auth // 요청 권한 설정 시작
                        .requestMatchers("/api/signup").permitAll() // 회원가입 허용
                        .anyRequest().authenticated() // 회원가입 제외 전부 로그인 필요
                );
        http.addFilterBefore(new JwtFilter(),
                org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class);

        return http.build(); // 보안 시스템 완성 적용
    }
}
