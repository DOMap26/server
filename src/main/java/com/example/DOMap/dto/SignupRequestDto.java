package com.example.DOMap.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class SignupRequestDto {

    // 필드값이 null이 아니고, 공백을 제외한 최소 한 글자 이상의 문자가 포함되어야한다는 어노테이션
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String confirmPassword;

    // 입력된 문자열이 유효한 이메일 형식인지 검증할 때 사용
    @Email
    @NotBlank
    private String email;
}
