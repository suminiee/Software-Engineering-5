package com.vocabulary.web.login.session;

import com.vocabulary.domain.member.domain.Role;
import lombok.Data;

@Data
public class MemberSessionDto {

    private Integer id;

    private Role role;
}
