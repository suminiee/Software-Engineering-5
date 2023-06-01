package com.vocabulary.domain.test.repository;

import com.vocabulary.domain.test.domain.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface TestRepository extends JpaRepository<Test, Integer> {

    @Query("select t.day from Test t where t.id = :id")
    List<Date> findAllById(@Param("id") Integer id);

    @Query("select t from Test t where t.day = :day and t.id = :id")
    Optional<Test> findByDayAndId(@Param("day") Date day, @Param("id") Integer id);
}
