package com.vocabulary.web.member;

import com.vocabulary.domain.member.domain.Member;
import com.vocabulary.web.member.MemberForm.*;

public class MemberBuilder {

    public static Member build(String loginId, String password, SignUpForm form, Boolean social) {
        Member member = new Member();
        member.setLoginId(loginId);
        member.setPassword(password);
        member.setNickname(form.getNickname());
        member.setDailyWord(form.getDailyWord());

        member.setLevel(-1);
        member.setRole(false);

        member.setSocial(social);

        return member;
    }
}
