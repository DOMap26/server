package com.example.DOMap.repository;

import com.example.DOMap.entity.Player;
import com.example.DOMap.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    // User를 기준으로 플레이어 찾기
    Optional<Player> findByUser(User user);

}
