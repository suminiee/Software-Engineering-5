package com.vocabulary.domain.group.repository;

import com.vocabulary.domain.group.domain.VocaWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VocaWordRepository extends JpaRepository<VocaWord, Integer> {

    @Query(value = "select g.wordId from VocaWord g where g.groupId = :groupId")
    List<Integer> getWords(@Param("groupId") Integer groupId);
}
