package com.vocabulary.domain.member.repository;

import com.vocabulary.domain.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {

    @Query("select m from Member m where m.loginId = :loginId")
    Optional<Member> findByLoginId(@Param("loginId") String loginId);

    @Query("update Member m set m.level = :level where m.id = :id")
    void updateLevel(@Param("id") Integer id, @Param("level") Integer level);
}
