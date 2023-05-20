package com.vocabulary.web.login.session;

import lombok.Data;

@Data
public class MemberSessionDto {
    private Integer id;
    private Boolean role;
    private Boolean social;
}
