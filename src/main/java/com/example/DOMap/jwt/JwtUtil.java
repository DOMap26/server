package com.example.DOMap.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {

    // 기존 문자열 키 방식은 보안 길이 문제 발생 → Key 객체 방식으로 변경
    // HS256 알고리즘에 맞는 안전한 키를 자동 생성해줌 (256bit 이상 보장)
    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // 토큰 만료 시간(1시간)
    private static final long EXPIRATION_TIME = 1000 * 60 * 60;

    // username을 주면 토큰 생성
    public static String createToken(String username) {

        // 토큰 생성 시작
        return Jwts.builder()
                // 토큰 안에 사용자 아이디 적기 (JWT의 subject)
                .setSubject(username)
                // 토큰 발급 시간 기록
                .setIssuedAt(new Date())
                // EXPIRATION_TIME(1시간)과 System.currentTimeMillis()(현재 시간)을 더해서 토큰 유효 기간 설정
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                // HS256이라는 JWT 암호화 알고리즘을 통하여 SECRET_KEY로 서명
                // Key 객체를 사용해야 보안 기준을 만족함
                .signWith(SECRET_KEY)
                // 최종 문자열 생성
                .compact();
    }

    // 토큰에서 username을 꺼내옴
    public static String getUsername(String token) {

        // 토큰 해석 시작
        Claims claims = Jwts.parserBuilder()
                // 만든 SECRET_KEY로 서명 검증 (위조 여부 체크)
                .setSigningKey(SECRET_KEY)
                // parser 생성
                .build()
                // 토큰의 정보가 일치하는지 검사 (서명 + 만료시간 포함)
                .parseClaimsJws(token)
                // 토큰 내용 추출
                .getBody();

        // 검증이 완료된 토큰에서 username 반환
        return claims.getSubject();
    }
}