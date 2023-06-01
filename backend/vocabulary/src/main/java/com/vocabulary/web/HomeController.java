package com.vocabulary.web;

import com.vocabulary.domain.member.dto.MemberInfoDto;
import com.vocabulary.domain.member.service.MemberService;
import com.vocabulary.domain.study.service.StudyService;
import com.vocabulary.web.login.argumentresolver.Login;
import com.vocabulary.web.login.session.MemberSessionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.Date;
import java.time.LocalDate;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberService memberService;
    private final StudyService studyService;

    @GetMapping("/main")
    public String home(@Login MemberSessionDto loginMember, Model model) {
        if (loginMember == null) {
            return "main/mainVisitor";
        }

        if (loginMember.getRole() == true) {
            return "redirect:/words";
        }

        MemberInfoDto info = memberService.getInfo(loginMember.getId());
        LocalDate localDate = LocalDate.now();
        Integer count = studyService.getStudiedCount(Date.valueOf(localDate), loginMember.getId());
        model.addAttribute("memberInfoDto", info);
        model.addAttribute("count", count);
        return "main/mainMember";
    }
}
