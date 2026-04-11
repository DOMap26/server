package com.example.DOMap.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
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
        // 헤더를 가져옴(요청에서 Authorization : Bearer eyjh...를 꺼냄)
        String authHeader = request.getHeader("Authorization");
        // 토큰이 없는 요청을 막고 JWT 토큰을 검사하기 위한 코드 작성
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            // 토큰 앞에 "Bearer "를 제거하고 토큰만 꺼내야함 따라서 substring을 활용해 7글자를 지움
            String token = authHeader.substring(7);
            // JwtUtil에서 username활용으로 토큰을 가져옴
            String username = JwtUtil.getUsername(token);

            var authentication =
                    new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(
                            username, null, null);

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }
}
