
package com.example.btvnbuoi2.service.impl;

import com.example.btvnbuoi2.dto.GroupDTO;
import com.example.btvnbuoi2.entity.Account;
import com.example.btvnbuoi2.entity.Group;
import com.example.btvnbuoi2.form.GroupForm;
import com.example.btvnbuoi2.repository.IAccountRepository;
import com.example.btvnbuoi2.repository.IGroupRepository;
import com.example.btvnbuoi2.service.IGroupService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<GroupDTO> getAll() {
        return groupRepository.findAll()
                .stream()
                .map(GroupDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public GroupDTO getById(Integer id) {
        Group group = getEntityById(id);
        return new GroupDTO(group);
    }

    private Group getEntityById(Integer id) {
        if (id == null || id <= 0) {
            throw new RuntimeException("ID group phải lớn hơn 0");
        }
        return groupRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Không tìm thấy group: " + id));
    }

    private Account getCreator(Integer creatorId) {
        if (creatorId == null || creatorId <= 0) {
            throw new RuntimeException("Creator ID phải lớn hơn 0");
        }
        return accountRepository.findById(creatorId)
                .orElseThrow(() ->
                        new RuntimeException("Không tìm thấy account tạo group"));
    }

    @Override
    @Transactional
    public GroupDTO create(GroupForm form) {
        if (form.getName() == null || form.getName().trim().isEmpty()) {
            throw new RuntimeException("Tên group không được để trống");
        }
        Account creator = getCreator(form.getCreatorId());
        Group group = new Group();
        group.setName(form.getName().trim());
        group.setCreator(creator);
        if (form.getCreateDate() != null) {
            group.setCreateDate(form.getCreateDate());
        } else {
            group.setCreateDate(LocalDateTime.now());
        }
        Group savedGroup = groupRepository.save(group);
        return new GroupDTO(savedGroup);
    }

    @Override
    @Transactional
    public GroupDTO update(Integer id, GroupForm form) {
        Group oldGroup = getEntityById(id);
        if (form.getName() == null || form.getName().trim().isEmpty()) {
            throw new RuntimeException("Tên group không được để trống");
        }
        Account creator = getCreator(form.getCreatorId());
        oldGroup.setName(form.getName().trim());
        oldGroup.setCreator(creator);
        if (form.getCreateDate() != null) {
            oldGroup.setCreateDate(form.getCreateDate());
        }
        Group savedGroup = groupRepository.save(oldGroup);
        return new GroupDTO(savedGroup);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Group group = getEntityById(id);
        groupRepository.delete(group);
    }
}
