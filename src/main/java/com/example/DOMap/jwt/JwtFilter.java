package com.example.DOMap.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// 요청 1번당 필터도 1번 실행
public class JwtFilter extends OncePerRequestFilter {

    @Override // 부모 클래스의 메서드 오버라이딩을 하는 어노테이션
    // 요청이 들어올 때 실행되는 메서드
    // 한 번의 요청당 딱 하넌만 실행되도록 보장
    protected void doFilterInternal(HttpServletRequest request, // 사용자가 보낸 모든 정보(URL, 헤더, body)
                                    HttpServletResponse response, // 서버가 사용자에게 보내는 응답
                                    FilterChain filterChain) // 다음 단계로 넘기는 역할
        throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            String username = JwtUtil.getUsername(token);
        }

    }
}
