package com.vocabulary.domain.word.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

public class WordForm {

    @Data
    public class WordSaveForm {
        @NotEmpty
        @Max(value = 20)
        private String spelling;
        @NotEmpty
        @Max(value = 100)
        private String mean;
    }

    @Data
    public class WordUpdateForm {
        @NotNull
        private Integer wordId;
        @NotNull
        @Max(value = 20)
        private String spelling;
        @NotNull
        @Max(value = 100)
        private String mean;
    }

}
