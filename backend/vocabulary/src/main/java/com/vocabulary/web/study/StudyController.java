package com.vocabulary.web.study;

import com.vocabulary.domain.study.service.StudyService;
import com.vocabulary.domain.word.domain.Word;
import com.vocabulary.web.login.argumentresolver.Login;
import com.vocabulary.web.login.session.MemberSessionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class StudyController {

    private final StudyService studyService;

    @GetMapping("/study")
    public ModelAndView studyForm(@Login MemberSessionDto loginMember) {
        LocalDate localDate = LocalDate.now();
        List<Word> dailyWord = studyService.getDailyWord(Date.valueOf(localDate), loginMember.getId());
        Integer curIdx = studyService.getStudiedCount(Date.valueOf(localDate), loginMember.getId());
        ModelAndView mv = new ModelAndView("study/learningPage");
        mv.addObject("word", dailyWord);
        mv.addObject("curIdx", (curIdx != 0) ? curIdx - 1 : 0);
        return mv;
    }

    @GetMapping("/studyEnd")
    public String save(@Login MemberSessionDto loginMember, @RequestParam(value = "wordId", required = false) Integer[] wordId) {
        if(wordId != null) {
            LocalDate localDate = LocalDate.now();
            for (Integer word : wordId) {
                studyService.update(loginMember.getId(), Date.valueOf(localDate), word);
            }
        }
        return "redirect:/main";
    }
}
