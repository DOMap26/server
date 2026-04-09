package com.example.DOMap.repository;

import com.example.DOMap.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

// <관리하려는 Entity, Entity의 @Id의 PK 타입>
public interface UserRepository extends JpaRepository<User,Integer> {
}
