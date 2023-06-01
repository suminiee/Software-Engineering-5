package com.vocabulary.web.test;

import com.vocabulary.domain.group.domain.Voca;
import com.vocabulary.domain.group.repository.VocaRepository;
import com.vocabulary.domain.group.repository.VocaWordRepository;
import com.vocabulary.domain.group.service.GroupService;
import com.vocabulary.domain.member.dto.MemberInfoDto;
import com.vocabulary.domain.member.service.MemberService;
import com.vocabulary.domain.study.service.StudyService;
import com.vocabulary.domain.test.service.TestService;
import com.vocabulary.domain.word.domain.Word;
import com.vocabulary.web.login.argumentresolver.Login;
import com.vocabulary.web.login.session.MemberSessionDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

import static com.vocabulary.domain.test.service.TestService.LEVEL_SIZE;

@Slf4j
@Controller
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;
    private final MemberService memberService;
    private final StudyService studyService;
    private final GroupService groupService;

    /**
     * 레벨 테스트
     */
    @GetMapping("/levelTest")
    public String levelTestForm(@Login MemberSessionDto loginMember, Model model) {
        model.addAttribute("testName", "레벨 테스트");
        model.addAttribute("words", testService.getLevelWords());
        return "test/test";
    }

    @GetMapping("/levelTest/result")
    public String levelTest(@Login MemberSessionDto loginMember, Model model,
                            @RequestParam(value = "answer", required = false) Integer[] wordIds, @RequestParam("time") String time) {
        List<Integer> error = testService.checkAnswer(wordIds);
        int result = (wordIds.length/2) - error.size();
        memberService.update(loginMember.getId(), result / 3);
        resultAttribute("레벨 테스트", result, wordIds.length / 2, time, model);
        return "test/testResult";
    }

    @GetMapping("/levelTest/timeout")
    public String levelTimeout(@Login MemberSessionDto loginMember, Model model){
        memberService.update(loginMember.getId(), 0);
        resultAttribute("레벨 테스트", 0, LEVEL_SIZE, "시간초과", model);
        return "test/testResult";
    }
    
    /**
     * 데일리 테스트
     */
    @GetMapping("/dailyTest")
    public String dailyTestForm(@Login MemberSessionDto loginMember, Model model) {
        LocalDate localDate = LocalDate.now();
        // 데일리 테스트 조건 확인
        if (loginMember == null || memberService.getInfo(loginMember.getId()).getDailyWord() > studyService.getStudiedCount(Date.valueOf(localDate), loginMember.getId())) {
            return "redirect:/main";
        }
        List<AnswerForm> dailyWords = testService.getDailyWords(Date.valueOf(localDate), loginMember.getId());
        model.addAttribute("testName", "데일리 테스트");
        model.addAttribute("words", dailyWords);
        return "test/test";
    }

    @GetMapping("/dailyTest/result")
    public String dailyTest(@Login MemberSessionDto loginMember, Model model,
                            @RequestParam(value = "answer", required = false) Integer[] wordIds, @RequestParam("time") String time){
        LocalDate localDate = LocalDate.now();
        List<Integer> error = testService.checkAnswer(wordIds);
        int result = (wordIds.length/2) - error.size();
        groupService.addError(loginMember.getId(), error, localDate.getMonthValue());
        testService.attendance(loginMember.getId(), Date.valueOf(localDate));
        resultAttribute("데일리 테스트", result, wordIds.length / 2, time, model);
        return "test/testResult";
    }

    @GetMapping("/dailyTest/timeout")
    public String dailyTimeout(@Login MemberSessionDto loginMember, Model model) {
        LocalDate localDate = LocalDate.now();
        List<Word> dailyWord = studyService.getDailyWord(Date.valueOf(localDate), loginMember.getId());
        List<Integer> error = new ArrayList<>();
        for (Word word : dailyWord) {
            error.add(word.getWordId());
        }
        groupService.addError(loginMember.getId(), error, localDate.getMonthValue());
        testService.attendance(loginMember.getId(), Date.valueOf(localDate));
        resultAttribute("데일리 테스트", 0, dailyWord.size(), "시간초과", model);
        return "test/testResult";
    }

    /**
     * 월말 테스트
     */
    @GetMapping("/monthlyTest")
    public String monthlyTestForm(@Login MemberSessionDto loginMember, Model model){
        LocalDate localDate = LocalDate.now();
        List<AnswerForm> monthlyWords = testService.getMonthlyWords(loginMember.getId(), localDate.getMonthValue());
        if (monthlyWords == null) {
            return "redirect:/main";
        }
        model.addAttribute("testName", "월말 테스트");
        model.addAttribute("words", monthlyWords);
        return "test/test";
    }

    @GetMapping("/monthlyTest/result")
    public String monthlyTest(@Login MemberSessionDto loginMember, Model model,
                              @RequestParam(value = "answer", required = false) Integer[] wordIds, @RequestParam("time") String time) {
        LocalDate localDate = LocalDate.now();
        List<Integer> error = testService.checkAnswer(wordIds);
        int result = (wordIds.length/2) - error.size();
        resultAttribute("월말 테스트", result, wordIds.length / 2, time, model);
        return "redirect:/result";
    }

    @GetMapping("/monthlyTest/timeout")
    public String monthlyTimeout(@Login MemberSessionDto loginMember, Model model) {
        LocalDate localDate = LocalDate.now();
        List<AnswerForm> monthlyWords = testService.getMonthlyWords(loginMember.getId(), localDate.getMonthValue());
        resultAttribute("월말 테스트", 0, monthlyWords.size(), "시간초과", model);
        return "test/testResult";
    }


    @GetMapping("/userCalendarCheck")
    public ModelAndView calendar(@Login MemberSessionDto loginMember, Model model) {
        MemberInfoDto info = memberService.getInfo(loginMember.getId());
        LocalDate localDate = LocalDate.now();
        Integer count = studyService.getStudiedCount(Date.valueOf(localDate), loginMember.getId());
        List<Date> attendance = testService.getAttendance(loginMember.getId());
        ModelAndView mv = new ModelAndView("member/userCalendarCheck");
        mv.addObject("attendance", attendance);
        mv.addObject("memberInfoDto", info);
        mv.addObject("count", count);
        return mv;
    }

    private void resultAttribute(String testName, Integer right, Integer max, String time, Model model) {
        model.addAttribute("testName", testName);
        model.addAttribute("right", right);
        model.addAttribute("max", max);
        model.addAttribute("time", time);
    }
}
