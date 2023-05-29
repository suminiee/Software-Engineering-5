package com.vocabulary.domain.study.repository;

import com.vocabulary.domain.study.domain.Study;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface StudyRepository extends JpaRepository<Study, Integer> {

    @Query("select s from Study s where s.day = :day and s.id = :id")
    List<Study> findByDayAndId(@Param("day") Date day, @Param("id") Integer id);

    @Query("select count(*) from Study s where s.day = :day and s.id = :id and s.studied = true")
    Integer getStudiedCount(@Param("day") Date day, @Param("id") Integer id);

    @Query("select count(*) from Study s where s.day = :day and s.id = :id")
    Integer getStudyCount(@Param("day") Date day, @Param("id") Integer id);

    @Query("select s from Study s where s.id = :id and s.day = :day and s.wordId = :wordId")
    Optional<Study> findByIdAndDateAndWordId(@Param("id") Integer id, @Param("day") Date day, @Param("wordId") Integer wordId);

}