package com.vocabulary.domain.study.service;

import com.vocabulary.domain.member.repository.MemberRepository;
import com.vocabulary.domain.study.domain.Study;
import com.vocabulary.domain.study.repository.StudyRepository;
import com.vocabulary.domain.word.domain.Word;
import com.vocabulary.domain.word.repository.WordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class StudyService {

    private final StudyRepository studyRepository;
    private final MemberRepository memberRepository;
    private final WordRepository wordRepository;

    public Integer getStudiedCount(Date day, Integer id) {
        return studyRepository.getStudiedCount(day, id);
    }

    public void update(Integer id, Date day, Integer wordId) {
        Study study = studyRepository.findByIdAndDateAndWordId(id, day, wordId).orElse(null);
        study.setStudied(true);
    }

    public List<Word> getDailyWord(Date day, Integer id) {
        Integer count = studyRepository.getStudyCount(day, id);
        Integer dailyWord = memberRepository.findById(id).orElse(null).getDailyWord();

        if (count < dailyWord) {
            setDailyWord(day, id, dailyWord-count);
        }

        List<Study> result = studyRepository.findByDayAndId(day, id);
        List<Word> wordList = new ArrayList<>();
        for (Study study : result) {
            wordList.add(wordRepository.findById(study.getWordId()).orElse(null));
        }
        return wordList;
    }

    private void setDailyWord(Date day, Integer id, Integer needSave) {
        List<Word> randomWords = wordRepository.getRandomWords(needSave);

        for (Word randomWord : randomWords) {
            Study study = new Study();
            study.setId(id);
            study.setDay(day);
            study.setWordId(randomWord.getWordId());
            study.setStudied(false);
            studyRepository.save(study);
        }
    }
}
