package com.vocabulary.domain.word.service;

import com.vocabulary.domain.word.domain.Word;
import com.vocabulary.domain.word.dto.WordForm.*;
import com.vocabulary.domain.word.dto.WordSearchCond;
import com.vocabulary.domain.word.repository.WordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class WordService {

    private final WordRepository wordRepository;

    public void save(Word word) {
        wordRepository.save(word);
    }

    public void update(WordUpdateForm form) {
        Word findWord = wordRepository.findById(form.getWordId()).orElse(null);
        findWord.setSpelling(form.getSpelling());
        findWord.setMean(form.getMean());
    }

    public void delete(Integer wordId) {
        wordRepository.delete(wordId);
    }

    public List<Word> findAll() {
        return wordRepository.findAll();
    }
    public List<Word> findAll(WordSearchCond cond) {
        return wordRepository.findAll(cond);
    }

    public List<Word> getRandomWords(Integer maxResultSize) {
        return wordRepository.getRandomWords(maxResultSize);
    }



}
