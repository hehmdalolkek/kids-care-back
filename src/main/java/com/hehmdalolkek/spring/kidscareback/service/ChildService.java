package com.hehmdalolkek.spring.kidscareback.service;

import com.hehmdalolkek.spring.kidscareback.entity.Child;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface ChildService {

    public List<Child> getAllChildrenByGroupId(int id);

    public Child getChildById(int id);

    public void addChild(@Valid Child child);

    public void editChildById(int id, @Valid Child newChild);

    public void deleteChildById(int id);

}
