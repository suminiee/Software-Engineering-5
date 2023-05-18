package com.vocabulary.domain.word.repository;

import com.vocabulary.domain.word.domain.Word;
import com.vocabulary.domain.word.dto.WordSearchCond;
import com.vocabulary.domain.word.dto.WordUpdateForm;

import java.util.List;
import java.util.Optional;

public interface WordRepository {

    void save(Word word);
    void update(WordUpdateForm form);
    void delete(Integer wordId);
    Optional<Word> findById(Integer wordId);
    List<Word> findAll();
    List<Word> findAll(WordSearchCond cond);
}
