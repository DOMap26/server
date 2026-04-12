package com.example.DOMap.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class PlayerResponseDto {

    private String nickname;
    private int level;
    private int currentHp;
    private int maxHp;
    private int currentExp;
    private int maxExp;
    private int gold;
    private int diamond;
    private String title;
}
