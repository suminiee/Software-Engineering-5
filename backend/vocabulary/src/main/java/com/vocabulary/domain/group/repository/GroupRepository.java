package com.vocabulary.domain.group.repository;

import com.vocabulary.domain.group.domain.Group;
import com.vocabulary.domain.group.dto.GroupInfoDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Integer> {

    @Query(value = "select g.groupId, g.groupName from Group g where g.id = :id")
    List<GroupInfoDto> getGroups(Integer id);
}
