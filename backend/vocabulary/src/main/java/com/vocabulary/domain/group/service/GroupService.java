package com.vocabulary.domain.group.service;

import com.vocabulary.domain.group.domain.Group;
import com.vocabulary.domain.group.domain.GroupWord;
import com.vocabulary.domain.group.dto.GroupInfoDto;
import com.vocabulary.domain.group.repository.GroupRepository;
import com.vocabulary.domain.group.repository.GroupWordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private final GroupWordRepository groupWordRepository;

    public List<GroupInfoDto> getGroup(Integer id) {
        return groupRepository.getGroups(id);
    }

    public void saveGroup(Group group) {
        groupRepository.save(group);
    }

    public void deleteGroup(Group group) {
        groupRepository.delete(group);
    }

    public void getGroupWords(Integer groupId) {
        groupWordRepository.getWords(groupId);
    }

    public void saveWord(GroupWord word) {
        groupWordRepository.save(word);
    }

    public void deleteWord(GroupWord word) {
        groupWordRepository.delete(word);
    }
}
