package com.example.btvnbuoi2.service;

import com.example.btvnbuoi2.entity.Group;

import java.util.List;

public interface IGroupService {
    List<Group> getAll();
    Group getById(Integer id);
    Group create(Group group);
    Group update(Integer id, Group group);
    void delete(Integer id);
}
