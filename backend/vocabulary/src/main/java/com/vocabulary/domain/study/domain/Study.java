package com.vocabulary.domain.study.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Data
@Entity
public class Study {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studyId;

    private Integer id;

    @Temporal(TemporalType.DATE)
    private Date day;

    private Integer wordId;

    private Boolean studied;
}
