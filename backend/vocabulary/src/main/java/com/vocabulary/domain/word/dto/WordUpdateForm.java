package com.vocabulary.domain.word.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class WordUpdateForm {
    @NotNull
    private Integer wordId;
    @NotNull
    private String spelling;
    @NotNull
    private String mean;
}
