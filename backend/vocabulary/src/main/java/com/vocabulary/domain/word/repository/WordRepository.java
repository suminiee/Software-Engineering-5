package com.vocabulary.domain.word.repository;

import com.vocabulary.domain.word.domain.Word;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordRepository extends JpaRepository<Word, Integer>, WordCustomRepository {
}
