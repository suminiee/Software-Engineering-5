package com.vocabulary.domain.word.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Word {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer wordId;

    private String spelling;
    private String mean;

    public Word() {
    }
}
