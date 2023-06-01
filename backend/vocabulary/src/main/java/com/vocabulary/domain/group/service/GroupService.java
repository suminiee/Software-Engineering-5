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

import java.time.Month;
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
        VocaWord vocaWord = vocaWordRepository.findByGroupIdAndWordId(word.getGroupId(), word.getWordId()).orElse(null);
        if (vocaWord == null) {
            vocaWordRepository.save(word);
        }
    }

    public void deleteWord(VocaWord word) {
        VocaWord vocaWord = vocaWordRepository.findByGroupIdAndWordId(word.getGroupId(), word.getWordId()).orElse(null);
        vocaWordRepository.delete(vocaWord);
    }

    public String findByGroupId(Integer groupId) {
        return vocaRepository.findByGroupId(groupId).orElse(null).getGroupName();
    }

    public void addError(Integer id, List<Integer> error, Integer month) {
        Voca voca = vocaRepository.getGroup(id, month + "월 오답모음").orElse(null);
        if (voca == null) {
            Voca newVoca = new Voca();
            newVoca.setId(id);
            newVoca.setGroupName(month + "월 오답모음");
            vocaRepository.save(newVoca);
            voca = newVoca;
        }
        Integer groupId = voca.getGroupId();
        for (Integer wordId : error) {
            VocaWord find = vocaWordRepository.findByGroupIdAndWordId(groupId, wordId).orElse(null);
            if (find == null) {
                VocaWord vocaWord = new VocaWord();
                vocaWord.setGroupId(groupId);
                vocaWord.setWordId(wordId);
                vocaWordRepository.save(vocaWord);
            }
        }
    }
}
