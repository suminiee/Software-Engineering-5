package com.vocabulary.web.member;

import jakarta.validation.constraints.*;
import lombok.Data;

public class MemberForm {

    @Data
    public class SignUpBase {
        @NotBlank(message = "공백이 있으면 안 됩니다.")
        @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z]).{5,15}", message = "알파벳 소문자와 숫자를 포함하여야 합니다.")
        @Pattern(regexp = ".{5,15}", message = "5-15자 이내여야 합니다.")
        private String loginId;
        @NotBlank(message = "공백이 있으면 안 됩니다.")
        @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z]).{5,15}", message = "알파벳 소문자와 숫자를 포함하여야 합니다.")
        @Pattern(regexp = ".{5,15}", message = "5-15자 이내여야 합니다.")
        private String password;

        @NotNull(message = "비밀번호가 일치하지 않습니다.")
        private String passCheck;
    }

    @Data
    public class SignUpForm {
        @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z]).{5,15}", message = "알파벳 소문자와 숫자를 포함하여야 합니다.")
        @Pattern(regexp = ".{5,15}", message = "5-15자 이내여야 합니다.")
        private String nickname;
        @Min(value = 10) @Max(value = 100)
        private Integer dailyWord;
    }

    @Data
    public class MemberUpdateForm {
        @NotBlank(message = "공백이 있으면 안 됩니다.")
        @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z]).{5,15}", message = "알파벳 소문자와 숫자를 포함하여야 합니다.")
        @Pattern(regexp = ".{5,15}", message = "5-15자 이내여야 합니다.")
        private String password;

        @NotNull(message = "비밀번호가 일치하지 않습니다.")
        private String passCheck;

        @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z]).{5,15}", message = "알파벳 소문자와 숫자를 포함하여야 합니다.")
        @Pattern(regexp = ".{5,15}", message = "5-15자 이내여야 합니다.")
        private String nickname;

        @Min(value = 10) @Max(value = 100)
        private Integer dailyWord;
    }

    @Data
    public class SocialMemberUpdateForm {
        @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z]).{5,15}", message = "알파벳 소문자와 숫자를 포함하여야 합니다.")
        @Pattern(regexp = ".{5,15}", message = "5-15자 이내여야 합니다.")
        private String nickname;

        @Min(value = 10) @Max(value = 100)
        private Integer dailyWord;
    }
}
