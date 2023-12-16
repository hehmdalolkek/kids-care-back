package com.hehmdalolkek.spring.kidscareback.service;

import com.hehmdalolkek.spring.kidscareback.entity.Group;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface GroupService {

    public List<Group> getAllGroups();

    public Group getGroupById(int id);

    public void addGroup(@Valid Group group);

    public void editGroupById(int id, @Valid Group newGroup);

    public void deleteGroupById(int id);

}
