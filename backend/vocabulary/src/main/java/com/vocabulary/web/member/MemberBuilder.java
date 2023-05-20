package com.vocabulary.web.member;

import com.vocabulary.domain.member.domain.Member;
import com.vocabulary.web.member.MemberForm.*;

public class MemberBuilder {

    public static Member build(SignUpForm form, Boolean social) {
        Member member = new Member();
        member.setLoginId(form.getLoginId());
        member.setPassword(form.getPassword());
        member.setNickname(form.getNickname());
        member.setDailyWord(form.getDailyWord());

        member.setLevel(-1);
        member.setRole(false);

        member.setSocial(social);

        return member;
    }
}
