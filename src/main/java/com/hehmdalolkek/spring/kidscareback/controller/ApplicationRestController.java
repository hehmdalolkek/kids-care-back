package com.hehmdalolkek.spring.kidscareback.controller;

import com.hehmdalolkek.spring.kidscareback.entity.Child;
import com.hehmdalolkek.spring.kidscareback.entity.Group;
import com.hehmdalolkek.spring.kidscareback.service.ChildService;
import com.hehmdalolkek.spring.kidscareback.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api")
public class ApplicationRestController {

    private final ChildService childService;
    private final GroupService groupService;

    @Autowired
    ApplicationRestController(ChildService childService, GroupService groupService) {
        this.childService = childService;
        this.groupService = groupService;
    }

    @GetMapping("/groups")
    public List<Group> getAllGroups() {
        return groupService.getAllGroups();
    }

    @GetMapping("/groups/{id}")
    public Group getGroupById(@PathVariable("id") int id) {
        return groupService.getGroupById(id);
    }

    @PostMapping("/groups")
    public void addGroup(@RequestBody Group group) {
        groupService.addGroup(group);
    }

    @PutMapping("/groups/{id}")
    public void editGroup(@PathVariable("id") int id, @RequestBody Group group) {
        groupService.editGroupById(id, group);
    }

    @DeleteMapping("/groups/{id}")
    public void deleteGroupById(@PathVariable("id") int id) {
        groupService.deleteGroupById(id);
    }

    @GetMapping("/children/{id}")
    public Child getChildById(@PathVariable("id") int id) {
        return childService.getChildById(id);
    }

    @GetMapping("/group/{id}/children")
    public List<Child> getAllChildrenByGroupId(@PathVariable("id") int id) {
        return childService.getAllChildrenByGroupId(id);
    }

    @PostMapping(value = "/children")
    public void addChild(@RequestBody Child child) {
        childService.addChild(child);
    }

    @PutMapping("/children/{id}")
    public void editChildById(@PathVariable("id") int id, @RequestBody Child child) {
        childService.editChildById(id, child);
    }

    @DeleteMapping("/children/{id}")
    public void deleteChildById(@PathVariable("id") int id) {
        childService.deleteChildById(id);
    }
}
