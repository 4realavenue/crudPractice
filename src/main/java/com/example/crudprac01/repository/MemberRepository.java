package com.example.crudprac01.repository;

import com.example.crudprac01.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByIsDeletedFalse();
}
