package com.hehmdalolkek.spring.kidscareback.service;

import com.hehmdalolkek.spring.kidscareback.entity.Group;

import java.util.List;

public interface GroupService {

    public List<Group> getAllGroups();

    public Group getGroupById(int id);

    public void addGroup(Group group);

    public void editGroupById(int id, Group newGroup);

    public void deleteGroupById(int id);

}
