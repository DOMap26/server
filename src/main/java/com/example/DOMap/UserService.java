package com.example.DOMap;

import com.example.DOMap.dto.SignupRequestDto;
import com.example.DOMap.repository.UserRepository;
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
        String encodedPassword = passwordEncoder.encode(request.getPassword());
    }
}
