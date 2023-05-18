package com.vocabulary.domain.word.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

public class WordForm {

    @Data
    public class WordDeleteForm {
        @NotEmpty
        private Integer wordId;
    }

    @Data
    public class WordSaveForm {
        @NotEmpty
        private String spelling;
        @NotEmpty
        private String mean;
    }

    @Data
    public class WordUpdateForm {
        @NotNull
        private Integer wordId;
        @NotNull
        private String spelling;
        @NotNull
        private String mean;
    }

}
