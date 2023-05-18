package com.vocabulary.web.login;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * todo 검증추가
 */
@Data
public class LoginForm {
    @NotEmpty
    private String loginId;
    @NotEmpty
    private String password;
}
