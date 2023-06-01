package com.vocabulary.web.test;

import com.vocabulary.domain.word.domain.Word;
import lombok.Data;

@Data
public class AnswerForm {
    private Word question;
    private Word answer1;
    private Word answer2;
    private Word answer3;
    private Word answer4;
}
