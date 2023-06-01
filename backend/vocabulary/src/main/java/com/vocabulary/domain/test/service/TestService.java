package com.vocabulary.domain.test.service;

import com.vocabulary.domain.group.domain.Voca;
import com.vocabulary.domain.group.repository.VocaRepository;
import com.vocabulary.domain.group.repository.VocaWordRepository;
import com.vocabulary.domain.member.repository.MemberRepository;
import com.vocabulary.domain.study.domain.Study;
import com.vocabulary.domain.study.repository.StudyRepository;
import com.vocabulary.domain.test.domain.Test;
import com.vocabulary.domain.test.repository.TestRepository;
import com.vocabulary.domain.word.domain.Word;
import com.vocabulary.domain.word.repository.WordRepository;
import com.vocabulary.web.test.AnswerForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.*;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TestService {

    private final TestRepository testRepository;
    private final MemberRepository memberRepository;
    private final WordRepository wordRepository;
    private final VocaWordRepository vocaWordRepository;
    private final VocaRepository vocaRepository;
    private final StudyRepository studyRepository;

    public static final Integer LEVEL_SIZE = 30;

    public List<AnswerForm> getLevelWords() {
        List<Word> randomWords = wordRepository.getRandomWords(LEVEL_SIZE);
        return makeErrorList(randomWords);
    }

    public List<AnswerForm> getDailyWords(Date date, Integer id) {
        List<Study> studyList = studyRepository.findByDayAndId(date, id);
        List<Word> randomWords = new ArrayList<>();
        for (Study study : studyList) {
            Word word = wordRepository.findById(study.getWordId()).orElse(null);
            randomWords.add(word);
        }
        return makeErrorList(randomWords);
    }

    public List<AnswerForm> getMonthlyWords(Integer id, Integer month) {
        Voca voca = vocaRepository.getGroup(id, month.toString() + "월 오답모음").orElse(null);
        if (voca == null) {
            Voca voca1 = new Voca();
            voca1.setGroupName(month.toString() + "월 오답모음");
            voca1.setId(id);
            vocaRepository.save(voca1);
            voca = voca1;
        }
        List<Integer> words = vocaWordRepository.getWords(voca.getGroupId());
        List<Word> randomWords = new ArrayList<>();
        for (Integer wordId : words) {
            Word word = wordRepository.findById(wordId).orElse(null);
            randomWords.add(word);
        }
        return makeErrorList(randomWords);
    }

    private List<AnswerForm> makeErrorList(List<Word> origin) {
        Integer[] nums = {1, 2, 3, 4};
        Integer size = origin.size();
        List<AnswerForm> testForm = new ArrayList<>();
        int numIdx = 0;
        int errorIdx = 0;
        while (true) {
            testForm.clear();
            numIdx = 0;
            errorIdx = 0;
            try {
                List<Word> errorWords = wordRepository.getRandomWords(size * 3);
                for (int i = 0; i < size; i++) {
                    Collections.shuffle(Arrays.asList(nums));
                    AnswerForm form = new AnswerForm();
                    form.setQuestion(origin.get(numIdx));
                    form.setAnswer1((nums[0] == 1) ? origin.get(numIdx++) : errorWords.get(errorIdx++));
                    form.setAnswer2((nums[1] == 1) ? origin.get(numIdx++) : errorWords.get(errorIdx++));
                    form.setAnswer3((nums[2] == 1) ? origin.get(numIdx++) : errorWords.get(errorIdx++));
                    form.setAnswer4((nums[3] == 1) ? origin.get(numIdx++) : errorWords.get(errorIdx++));
                    testForm.add(form);
                }
                break;
            } catch (IndexOutOfBoundsException e) {
                log.info("index error! retry getRandomWords!!");
            }
        }
        return testForm;
    }

    public void attendance(Integer id, Date day) {
        Test find = testRepository.findByDayAndId(day, id).orElse(null);
        if (find == null){
            Test test = new Test();
            test.setDay(day);
            test.setId(id);
            testRepository.save(test);
        }
    }

    public List<Date> getAttendance(Integer id) {
        return testRepository.findAllById(id);
    }

    public List<Integer> checkAnswer(Integer[] wordIds) {
        List<Integer> errors = new ArrayList<>();
        for (int i = 0; i < wordIds.length;) {
            if (!Objects.equals(wordIds[i], wordIds[i+1])) {
                errors.add(wordIds[i]);
            }
            i += 2;
        }
        return errors;
    }
}
