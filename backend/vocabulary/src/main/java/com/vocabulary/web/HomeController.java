package com.vocabulary.web;

import com.vocabulary.domain.member.dto.MemberInfoDto;
import com.vocabulary.domain.member.service.MemberService;
import com.vocabulary.domain.study.service.StudyService;
import com.vocabulary.web.login.argumentresolver.Login;
import com.vocabulary.web.login.session.MemberSessionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberService memberService;
    private final StudyService studyService;

    @GetMapping("/home")
    public String home(@Login MemberSessionDto loginMember, Model model) {
        if (loginMember == null) {
            return "home";
        }

        MemberInfoDto info = memberService.getInfo(loginMember.getId());
        Integer count = studyService.getCount(loginMember.getId());
        model.addAttribute("info", info);
        model.addAttribute("count", count);

        return "loginHome";
    }
}
