package com.vocabulary.web.member;

import com.vocabulary.domain.member.domain.Member;
import com.vocabulary.domain.member.service.MemberService;
import com.vocabulary.web.login.argumentresolver.Login;
import com.vocabulary.web.login.session.MemberSessionDto;
import com.vocabulary.web.member.MemberForm.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/signUp/1")
    public String signUpIdForm(){
        return "signUpForm";
    }

    @PostMapping("/signUp/1")
    public String signUpId(@Validated @ModelAttribute SignUpBase form){
        return "redirect:/signUp/2";
    }

    @GetMapping("/signUp/2")
    public String socialSignUpForm() {
        return "signUpForm2";
    }

    @PostMapping("/signUp1")
    public String signUp(@Validated @ModelAttribute SignUpForm form) {
        Member member = MemberBuilder.build(form, false);
        memberService.signUp(member);
        return "redirect:/login";
    }

    @GetMapping("/edit")
    public String editForm(@Login MemberSessionDto loginMember) {
        return "editForm";
    }

    @PostMapping("/edit")
    public String edit(@Login MemberSessionDto loginMember, @ModelAttribute MemberUpdateForm form) {
        memberService.update(loginMember.getId(), form);
        return "redirect:/home";
    }








}
