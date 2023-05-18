package com.vocabulary.domain.word.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class WordDeleteForm {
    @NotEmpty
    private Integer wordId;
}
