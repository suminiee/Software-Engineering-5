package com.vocabulary.domain.test.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Test {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer testId;

    private Integer id;

    @Temporal(TemporalType.DATE)
    private Date day;
}
