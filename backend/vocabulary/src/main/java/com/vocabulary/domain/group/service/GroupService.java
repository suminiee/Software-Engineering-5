package com.vocabulary.domain.group.service;

import com.vocabulary.domain.group.domain.Voca;
import com.vocabulary.domain.group.domain.VocaWord;
import com.vocabulary.domain.group.repository.VocaRepository;
import com.vocabulary.domain.group.repository.VocaWordRepository;
import com.vocabulary.domain.word.domain.Word;
import com.vocabulary.domain.word.repository.WordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class GroupService {

    private final VocaRepository vocaRepository;
    private final VocaWordRepository vocaWordRepository;
    private final WordRepository wordRepository;

    public List<Voca> getGroup(Integer id) {
        return vocaRepository.getGroup(id);
    }

    public void saveGroup(Voca group) {
        vocaRepository.save(group);
    }

    public void deleteGroup(Voca group) {
        vocaRepository.delete(group);
    }

    public List<Word> getGroupWords(Integer groupId) {
        List<Integer> wordIds = vocaWordRepository.getWords(groupId);
        List<Word> words = new ArrayList<>();
        for (Integer wordId : wordIds) {
            words.add(wordRepository.findById(wordId).orElse(null));
        }
        return words;
    }

    public void saveWord(VocaWord word) {
        vocaWordRepository.save(word);
    }

    public void deleteWord(VocaWord word) {
        vocaWordRepository.delete(word);
    }
}
