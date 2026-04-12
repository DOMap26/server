package com.example.DOMap.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // 이 클래스가 DB 테이블이 된다는 뜻
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 id 자동 증가
    private Long id;

    @OneToOne // User 하나당 Player 하나
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(nullable = false)
    private String nickname; // 게임 닉네임

    @Column(nullable = false)
    private String level; // 레벨

    @Column(nullable = false)
    private int currentHp; // 현재 체력

    @Column(nullable = false)
    private int maxHp; // 최대 체력

    @Column(nullable = false)
    private int currentExp; // 현재 경험치

    @Column(nullable = false)
    private int maxExp; // 다음 레벨까지 필요한 최대 경험치

    @Column(nullable = false)
    private int gold; // 골드
}