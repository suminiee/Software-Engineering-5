package com.vocabulary.domain.group.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Voca {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer groupId;

    private Integer id;

    private String groupName;
}
