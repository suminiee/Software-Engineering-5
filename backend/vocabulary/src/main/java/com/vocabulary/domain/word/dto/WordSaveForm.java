package com.vocabulary.domain.word.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class WordSaveForm {
    @NotEmpty
    private String spelling;
    @NotEmpty
    private String mean;
}
