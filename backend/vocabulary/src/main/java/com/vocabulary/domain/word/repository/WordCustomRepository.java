package com.vocabulary.domain.word.repository;

import com.vocabulary.domain.word.domain.Word;

import java.util.List;

public interface WordCustomRepository {

    void delete(Integer wordId);
    List<Word> findAllByCond(String cond);
    List<Word> getRandomWords(Integer maxResultSize);

}
