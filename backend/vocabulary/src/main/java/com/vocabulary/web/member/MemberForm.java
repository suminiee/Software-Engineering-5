package com.vocabulary.web.member;

import lombok.Data;

public class MemberForm {

    @Data
    public class SignUpBase {
        private String loginId;
        private String password;
    }

    @Data
    public class SignUpForm extends SignUpBase {
        private String nickname;
        private Integer dailyWord;
    }

    @Data
    public class MemberUpdateForm {
        private String password;
        private String nickname;
        private Integer dailyWord;
    }

    @Data
    public class SocialMemberUpdateForm {
        private String nickname;
        private Integer dailyWord;
    }
}
