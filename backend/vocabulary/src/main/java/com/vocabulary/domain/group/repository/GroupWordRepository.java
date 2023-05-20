package com.vocabulary.domain.group.repository;

import com.vocabulary.domain.group.domain.GroupWord;
import com.vocabulary.domain.group.dto.GroupInfoDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GroupWordRepository extends JpaRepository<GroupWord, Integer> {

    @Query(value = "select g.wordId from GroupWord g where g.groupId = :id")
    List<GroupInfoDto> getWords(Integer groupId);
}
