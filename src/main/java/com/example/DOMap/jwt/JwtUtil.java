package com.example.DOMap.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {

    // mysecretkey라는 암호를 사용하여서 SECRET_KEY라는 변수의 토큰을 만듬
    private static final String SECRET_KEY = "DOMapJWTSecretKeyMustBeLongEnough123456";

    // 토큰 만료 시간(1시간)
    private static final long EXPIRATION_TIME = 1000 * 60 * 60;

    // username을 주면 토큰 생성
    public static String createToken(String username) {

        // 토큰 생성 시작
        return Jwts.builder()
                // 토큰 안에 사용자 아이디 적기
                .setSubject(username)
                // 토큰 발급 시간 기록
                .setIssuedAt(new Date())
                // EXPIRATION_TIME(1시간)과 System.currentTimeMillis()(현재 시간)을 더해서 토큰 유효 기간 설정
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                // HS256이라는 JWT 암호화 알고리즘을 통하여 SECRET_KEY를 확정
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                // 최종 문자열 생성
                .compact();
    }

    // 토큰에서 username을 꺼내옴
    public static String getUsername(String token) {

        // 토큰 해석 시작
        Claims claims = Jwts.parser()
                // 확정 지은 토큰이 진짜인지 확인
                .setSigningKey(SECRET_KEY)
                // 토큰의 정보가 일치하는지 검사
                .parseClaimsJws(token)
                // 토큰 내용 추출
                .getBody();

        // 검증이 완료된 토큰을 돌려줌
        return claims.getSubject();
    }
}