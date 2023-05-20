package com.vocabulary.domain.member.dto;

import lombok.Data;

@Data
public class MemberInfoDto {
    private String nickname;
    private Integer level;
    private Integer dailyWord;
}
