package com.hehmdalolkek.spring.kidscareback.service;

import com.hehmdalolkek.spring.kidscareback.dao.ChildRepository;
import com.hehmdalolkek.spring.kidscareback.dao.GroupRepository;
import com.hehmdalolkek.spring.kidscareback.entity.Group;
import com.hehmdalolkek.spring.kidscareback.exceptions.GroupIsNotEmptyException;
import com.hehmdalolkek.spring.kidscareback.exceptions.GroupNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final ChildRepository childRepository;

    @Autowired
    GroupServiceImpl(GroupRepository groupRepository, ChildRepository childRepository) {
        this.groupRepository = groupRepository;
        this.childRepository = childRepository;
    }

    @Override
    @Transactional
    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    @Override
    public Group getGroupById(int id) {
        return groupRepository.findById(id)
                .orElseThrow(() -> new GroupNotFoundException(id));
    }

    @Override
    public void addGroup(Group group) {
        groupRepository.save(group);
    }

    @Override
    public void editGroupById(int id, Group newGroup) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new GroupNotFoundException(id));

        if (newGroup.getTitle() != null && !Objects.equals(newGroup.getTitle(), group.getTitle())) {
            group.setTitle(newGroup.getTitle());
        }
        if (newGroup.getNumber() != 0 && !Objects.equals(newGroup.getNumber(), group.getNumber())) {
            group.setNumber(newGroup.getNumber());
        }

        groupRepository.save(group);
    }

    @Override
    public void deleteGroupById(int id) {
        groupRepository.findById(id)
                .orElseThrow(() -> new GroupNotFoundException(id));
        if (!childRepository.findAllByGroupId(id).isEmpty()) {
            throw new GroupIsNotEmptyException(id);
        }
        groupRepository.deleteById(id);
    }
}
