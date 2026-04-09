package com.example.DOMap.controller;

import com.example.DOMap.dto.SignupRequestDto;
import com.example.DOMap.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // 데이터를 JSON 형태로 반환할때 활용
@RequiredArgsConstructor // final이 붙은 필드를 모아서 자동으로 생성사 생성(DI)
@RequestMapping("/api") // 모은 주소는 "/api"로 시작
public class UserController {

    private final UserService userService;

    public String signup(@RequestBody SignupRequestDto request) {
        userService.signup(request);

        return "회원가입 성공!";
    }
}
