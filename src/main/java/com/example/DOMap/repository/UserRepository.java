package com.example.DOMap.repository;

import com.example.DOMap.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

// <관리하려는 Entity, Entity의 @Id의 PK 타입>
public interface UserRepository extends JpaRepository<User, Long> {

    // Optional<User> : User 데이터가 있을 수도 없을 수도 있다는 뜻(만약에 값이 없으면 예외를 던676767666ㅋ)
    // 쿼리 메서드 SELECT * FROM user WHERE username = ? 라는 SQL이 자동으로 생성
    Optional<User> findByUsername(String username);

    // 이메일도 조회가능
    Optional<User> findByEmail(String email);

}
