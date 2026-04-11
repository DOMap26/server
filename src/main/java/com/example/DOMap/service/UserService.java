package com.example.DOMap.service;

import com.example.DOMap.dto.LoginRequestDto; // 로그인 DTO 추가
import com.example.DOMap.dto.SignupRequestDto;
import com.example.DOMap.entity.User;
import com.example.DOMap.repository.UserRepository;
import com.example.DOMap.jwt.JwtUtil; // JWT 유틸 추가
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor // 생성자 주입
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 비밀번호 확인
    public void signup(SignupRequestDto request) {
        // 사용자가 입력한 비밀번호가 비밀번호 확인위한 비밀번호와 같지 않다면
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            // 예외를 던짐
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        // username 중복 확인
        // findBy 메서드를 활용하여서 username을 찾음
        userRepository.findByUsername(request.getUsername())
                // 만약 사용자가 입력한 값이 안에 존재한다면
                .ifPresent(user -> {
                    // 예외를 던짐
                    throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
                });
        // email 중복 체크
        userRepository.findByEmail(request.getEmail())
                .ifPresent(user -> {
                    throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
                });
        // 비밀번호 암호화
        // passwordEncoder : 스프링 시큐리티에서 제공하는 인터페이스(비밀번호를 암호화함)
        //.encode(request.getPassword()) : 사용자가 비밀번호를 입력하면 무작위값을 섞어서 암호화를 적용
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        // User 객체 생성(DTO -> Entity로 DB용으로 바꿀 때 객체)
        User user = User.builder()
                .username(request.getUsername())
                .password(encodedPassword)
                .email(request.getEmail())
                .build();

        // DB에 저장
        userRepository.save(user);
    }

    public String login(LoginRequestDto request) {

        // 사용자 조회(username을 활용하여서 DB 검색)
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        // 비밀번호 검증
        // equals대신 matches를 사용한 이유 : 비밀번호는 암호화했으므로 비교도 암호화 방식으로 해야함
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 틀렸습니다."); // 🔥 오타 수정
        }

        // JwtUtil(토큰을 만드는 법을 정의) <-> UserService(언제 토큰을 만들지, 토큰을 받는 역할)
        // 로그인 성공하면 JWT 토큰 받음(JwtUtil에서 만든 기능을 호출해서 만드는 것)
        String token = JwtUtil.createToken(user.getUsername());

        // 토큰 반환
        return token;
    }
}