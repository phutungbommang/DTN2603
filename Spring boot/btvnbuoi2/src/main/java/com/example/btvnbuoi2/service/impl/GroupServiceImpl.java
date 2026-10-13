package com.example.btvnbuoi2.service.impl;

import com.example.btvnbuoi2.entity.Account;
import com.example.btvnbuoi2.entity.Group;
import com.example.btvnbuoi2.repository.IAccountRepository;
import com.example.btvnbuoi2.repository.IGroupRepository;
import com.example.btvnbuoi2.service.IGroupService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
@Service
public class GroupServiceImpl implements IGroupService {
    private final IGroupRepository groupRepository;
    private final IAccountRepository accountRepository;

    public GroupServiceImpl(
            IGroupRepository groupRepository,
            IAccountRepository accountRepository) {
        this.groupRepository = groupRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Group> getAll() {
        return groupRepository.findAll();
    }

    @Override
    public Group getById(Integer id) {
        return groupRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Không tìm thấy group: " + id));
    }

    private void setCreator(Group group) {
        if (group.getCreator() != null
                && group.getCreator().getId() != null) {
            Account creator = accountRepository
                    .findById(group.getCreator().getId())
                    .orElseThrow(() ->
                            new RuntimeException("Không tìm thấy account tạo group"));
            group.setCreator(creator);
        } else {
            group.setCreator(null);
        }
    }

    @Override
    @Transactional
    public Group create(Group group) {
        group.setId(null);
        setCreator(group);
        return groupRepository.save(group);
    }

    @Override
    @Transactional
    public Group update(Integer id, Group group) {
        Group oldGroup = getById(id);
        setCreator(group);
        oldGroup.setName(group.getName());
        oldGroup.setCreator(group.getCreator());
        oldGroup.setCreateDate(group.getCreateDate());
        return groupRepository.save(oldGroup);
    }

    @Override
    public void delete(Integer id) {
        Group group = getById(id);
        groupRepository.delete(group);
    }
}
