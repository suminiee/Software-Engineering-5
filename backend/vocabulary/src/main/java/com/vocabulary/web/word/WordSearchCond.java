package com.vocabulary.web.word;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class WordSearchCond {

    @NotEmpty
    @Max(value = 100)
    private String searchCond;
}
