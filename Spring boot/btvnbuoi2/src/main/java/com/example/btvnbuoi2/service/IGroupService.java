package com.example.btvnbuoi2.service;

import com.example.btvnbuoi2.dto.GroupDTO;
import com.example.btvnbuoi2.entity.Group;
import com.example.btvnbuoi2.form.GroupForm;

import java.util.List;

public interface IGroupService {
    List<GroupDTO> getAll();
    GroupDTO getById(Integer id);
    GroupDTO create(GroupForm form);
    GroupDTO update(Integer id, GroupForm form);
    void delete(Integer id);
}
