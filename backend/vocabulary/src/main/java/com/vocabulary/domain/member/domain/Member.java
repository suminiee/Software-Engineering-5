package com.vocabulary.domain.member.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String loginId;
    private String password;
    private String nickname;
    private Integer level;
    private Integer dailyWord;
    private Boolean social;
    private Boolean role;
}
