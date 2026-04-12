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

}