package com.hehmdalolkek.spring.kidscareback.service;

import com.hehmdalolkek.spring.kidscareback.entity.Child;

import java.util.List;

public interface ChildService {

    public List<Child> getAllChildrenByGroupId(int id);

    public Child getChildById(int id);

    public void addChild(Child child);

    public void editChildById(int id, Child newChild);

    public void deleteChildById(int id);

}
