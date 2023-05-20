package com.vocabulary.domain.group.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class GroupWord {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer groupWordId;

    @ManyToOne
    @JoinColumn(name = "groupId")
    private Group groupId;

    private Integer wordId;

    public GroupWord() {
    }
}
