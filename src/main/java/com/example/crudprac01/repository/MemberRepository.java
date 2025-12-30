package com.example.crudprac01.repository;

import com.example.crudprac01.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByIsDeletedFalse();

    Boolean existsByEmail(String email);

    // 회원을 이메일로 조회
    Optional<Member> findByEmail(String email);
}
