package com.vocabulary.web.member;

import com.vocabulary.domain.login.LoginService;
import com.vocabulary.domain.member.domain.Member;
import com.vocabulary.domain.member.service.MemberService;
import com.vocabulary.web.login.argumentresolver.Login;
import com.vocabulary.web.login.session.MemberSessionDto;
import com.vocabulary.web.member.MemberForm.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/signup/1")
    public String signUpIdForm(@ModelAttribute("signUpBase") SignUpBase form) {
        return "signup/signup1";
    }

    @PostMapping("/signup/1")
    public String signUpId(@Validated @ModelAttribute SignUpBase form, BindingResult bindingResult, HttpServletRequest request) {
        // 비밀번호 확인 및 아이디 중복 확인
        if (form.getPassword() != null && form.getPassCheck() != null) {
            if (!form.getPassword().equals(form.getPassCheck())) {
                bindingResult.addError(new FieldError("signUpBase", "passCheck", "비밀번호가 일치하지 않습니다."));
            }
        }
        if (form.getLoginId() != null) {
            if (memberService.findByLoginId(form.getLoginId()) != null) {
                bindingResult.addError(new FieldError("signUpBase", "loginId", "이미 등록된 아이디입니다."));
            }
        }

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "signup/signup1";
        }

        request.getSession().setAttribute("loginId", form.getLoginId());
        request.getSession().setAttribute("password", form.getPassword());
        request.getSession().setAttribute("social", false);
        return "redirect:/signup/2";
    }

    @GetMapping("/signup/2")
    public String socialSignUpForm(@ModelAttribute("signUpForm") SignUpForm form) {
        return "signup/signup2";
    }

    @PostMapping("/signup/2")
    public String signUp(@Validated @ModelAttribute SignUpForm form, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "signup/signup2";
        }
        Member member = MemberBuilder.build((String) session.getAttribute("loginId")
                , (String) session.getAttribute("password"),
                form, (Boolean) session.getAttribute("social"));
        memberService.signUp(member);
        return "redirect:/main";
    }

    @GetMapping("/edit")
    public String editForm(@Login MemberSessionDto loginMember) {
        return "editForm";
    }

    @PostMapping("/edit")
    public String edit(@Login MemberSessionDto loginMember, @Validated @ModelAttribute MemberUpdateForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "editForm";
        }
        memberService.update(loginMember.getId(), form);
        return "redirect:/home";
    }
}
