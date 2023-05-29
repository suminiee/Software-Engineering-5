package com.vocabulary.domain.group.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class VocaWord {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer groupWordId;

    private Integer groupId;
    private Integer wordId;
}
