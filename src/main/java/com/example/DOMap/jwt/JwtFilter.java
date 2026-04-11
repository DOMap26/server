package com.example.DOMap.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

// 요청 1번당 필터도 1번 실행
public class JwtFilter extends OncePerRequestFilter {

    @Override // 부모 클래스의 메서드 오버라이딩을 하는 어노테이션
    // 요청이 들어올 때 실행되는 메서드
    // 한 번의 요청당 딱 한 번만 실행되도록 보장
    protected void doFilterInternal(HttpServletRequest request, // 사용자가 보낸 모든 정보(URL, 헤더, body)
                                    HttpServletResponse response, // 서버가 사용자에게 보내는 응답
                                    FilterChain filterChain) // 다음 단계로 넘기는 역할

            throws ServletException, IOException {

        // 헤더를 가져옴(요청에서 Authorization : Bearer eyjh...를 꺼냄)
        String authHeader = request.getHeader("Authorization");

        // 디버깅용: 헤더 확인 (처음 테스트할 때 매우 중요)
        System.out.println("Authorization Header: " + authHeader);

        // 토큰이 있는 경우에만 JWT 검사 진행
        if (authHeader != null && authHeader.startsWith("Bearer ")) {

            try {
                // 토큰 앞에 "Bearer "를 제거하고 토큰만 꺼내야함 따라서 substring을 활용해 7글자를 지움
                String token = authHeader.substring(7);

                // 디버깅용: 토큰 확인
                System.out.println("JWT Token: " + token);

                // JwtUtil에서 username을 추출
                String username = JwtUtil.getUsername(token);

                // 디버깅용: username 확인
                System.out.println("JWT Username: " + username);

                // 권한을 반드시 넣어줘야 Spring Security가 인증된 사용자로 인정함
                List<SimpleGrantedAuthority> authorities =
                        List.of(new SimpleGrantedAuthority("ROLE_USER"));

                // 이미 인증된 사용자이므로 username + 권한을 넣어 Authentication 객체 생성
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                username, null, authorities);

                // ⭐ 이미 인증된 상태면 덮어쓰지 않도록 체크 (안정성 증가)
                if (SecurityContextHolder.getContext().getAuthentication() == null) {
                    // 지금 요청의 로그인 정보를 저장하는 곳
                    SecurityContextHolder.getContext().setAuthentication(authentication);

                    // 디버깅용: 인증 저장 확인
                    System.out.println("Authentication 저장 완료");
                }

            } catch (Exception e) {
                // JWT 파싱 실패 시 에러 출력 (이게 핵심 디버깅 포인트)
                System.out.println("JWT 에러 발생: " + e.getMessage());
            }
        } else {
            // 토큰 자체가 없는 경우
            System.out.println("JWT 없음 또는 형식 틀림");
        }

        // 다음 단계 요청으로 넘기는 코드
        filterChain.doFilter(request, response);
    }
}