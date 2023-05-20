package com.vocabulary.domain.group.domain;

import com.vocabulary.domain.member.domain.Member;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Group {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer groupId;

    @ManyToOne
    @JoinColumn(name = "id")
    private Member id;

    private String groupName;
}
