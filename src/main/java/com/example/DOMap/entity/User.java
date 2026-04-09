package com.example.DOMap.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 사용자 아이디 값이 비어있을 수 없으며, 고유값이어야함
    @Column(nullable = false, unique = true)
    private String username;

    // 비밀번호 저장(값이 비어있을 수 없음)
    @Column(nullable = false)
    private String password;

}
