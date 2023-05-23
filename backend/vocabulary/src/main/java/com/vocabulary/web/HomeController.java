package com.vocabulary.web;

import com.vocabulary.domain.member.dto.MemberInfoDto;
import com.vocabulary.domain.member.service.MemberService;
import com.vocabulary.domain.study.service.StudyService;
import com.vocabulary.web.login.argumentresolver.Login;
import com.vocabulary.web.login.session.MemberSessionDto;
import com.vocabulary.web.login.session.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberService memberService;
    private final StudyService studyService;

    @GetMapping("/main")
    public String home(@Login MemberSessionDto loginMember, Model model) {
        if (loginMember == null) {
            return "main";
        }

        if (loginMember.getRole() == true) {
            return "redirect:/words";
        }

        MemberInfoDto info = memberService.getInfo(loginMember.getId());
//        Integer count = stu
//        model.addAttribute("info", info);
//        model.addAttribute("count", count);

        return "main";
    }
}
