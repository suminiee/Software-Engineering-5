package com.vocabulary.domain.word.repository;

import com.vocabulary.domain.word.domain.Word;
import com.vocabulary.domain.word.dto.WordSearchCond;

import java.util.List;

public interface WordCustomRepository {

    void delete(Integer wordId);
    List<Word> findAll(WordSearchCond cond);
    List<Word> getRandomWords(Integer maxResultSize);

}
