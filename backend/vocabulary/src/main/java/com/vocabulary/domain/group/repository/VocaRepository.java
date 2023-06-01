package com.vocabulary.domain.group.repository;

import com.vocabulary.domain.group.domain.Voca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface VocaRepository extends JpaRepository<Voca, Integer> {

    @Query("select v from Voca v where v.id = :id")
    List<Voca> getGroup(Integer id);

    @Query("select v from Voca v where v.id = :id and v.groupName = :name")
    Optional<Voca> getGroup(Integer id, String name);

    Optional<Voca> findByGroupId(Integer groupId);
}
