package com.vocabulary.domain.member.service;

import com.vocabulary.domain.member.domain.Member;
import com.vocabulary.domain.member.dto.MemberInfoDto;
import com.vocabulary.domain.member.repository.MemberRepository;
import com.vocabulary.web.member.MemberForm.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void signUp(Member member){
        memberRepository.save(member);
    }

    public void update(Integer id, MemberUpdateForm form) {
        Member member = memberRepository.findById(id).orElse(null);
        member.setPassword(form.getPassword());
        member.setNickname(form.getNickname());
        member.setDailyWord(form.getDailyWord());
    }

    public void update(Integer id, String nickname, Integer dailWord) {
        Member member = memberRepository.findById(id).orElse(null);
        member.setNickname(nickname);
        member.setDailyWord(dailWord);
    }

    public Member findByLoginId(String loginId) {
        return memberRepository.findByLoginId(loginId).orElse(null);
    }

    public MemberInfoDto getInfo(Integer id) {
        Member member = memberRepository.findById(id).orElse(null);
        return getMemberInfoDto(member);
    }

    private static MemberInfoDto getMemberInfoDto(Member member) {
        MemberInfoDto info = new MemberInfoDto();
        info.setNickname(member.getNickname());
        info.setLevel(member.getLevel());
        info.setDailyWord(member.getDailyWord());

        return info;
    }
}
