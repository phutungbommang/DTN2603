package com.example.btvnbuoi2.service.impl;

import com.example.btvnbuoi2.dto.GroupAccountDTO;
import com.example.btvnbuoi2.entity.Group;
import com.example.btvnbuoi2.entity.Account;
import com.example.btvnbuoi2.entity.GroupAccount;
import com.example.btvnbuoi2.form.GroupAccountForm;
import com.example.btvnbuoi2.repository.IAccountRepository;
import com.example.btvnbuoi2.repository.IGroupAccountRepository;
import com.example.btvnbuoi2.repository.IGroupRepository;
import com.example.btvnbuoi2.service.IGroupAccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupAccountServiceImpl implements IGroupAccountService {

    private final IGroupAccountRepository groupAccountRepository;
    private final IGroupRepository groupRepository;
    private final IAccountRepository accountRepository;

    public GroupAccountServiceImpl(
            IGroupAccountRepository groupAccountRepository,
            IGroupRepository groupRepository,
            IAccountRepository accountRepository) {
        this.groupAccountRepository = groupAccountRepository;
        this.groupRepository = groupRepository;
        this.accountRepository = accountRepository;
    }

    // Thêm account vào group
    @Override
    @Transactional
    public GroupAccountDTO addAccountToGroup(GroupAccountForm form) {

        if (form.getGroupId() == null || form.getGroupId() <= 0) {
            throw new RuntimeException("Group ID phải lớn hơn 0");
        }

        if (form.getAccountId() == null || form.getAccountId() <= 0) {
            throw new RuntimeException("Account ID phải lớn hơn 0");
        }

        Integer groupId = form.getGroupId();
        Integer accountId = form.getAccountId();

        Group group = groupRepository.findById(groupId)
                .orElseThrow(() ->
                        new RuntimeException("Không tìm thấy group: " + groupId));

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() ->
                        new RuntimeException("Không tìm thấy account: " + accountId));

        boolean exists = groupAccountRepository
                .existsByGroupIdAndAccountId(groupId, accountId);

        if (exists) {
            throw new RuntimeException("Account đã tham gia group này");
        }

        GroupAccount groupAccount = new GroupAccount();
        groupAccount.setGroup(group);
        groupAccount.setAccount(account);
        groupAccount.setJoinDate(LocalDateTime.now());


        if (form.getJoinDate() != null) {
            groupAccount.setJoinDate(form.getJoinDate());
        } else {
            groupAccount.setJoinDate(LocalDateTime.now());
        }

        GroupAccount saved = groupAccountRepository.save(groupAccount);

        return new GroupAccountDTO(saved);
    }

    @Override
    public List<GroupAccountDTO> getGroupsByAccount(Integer accountId) {
        if (accountId == null || accountId <= 0) {
            throw new RuntimeException("Account ID phải lớn hơn 0");
        }

        if (!accountRepository.existsById(accountId)) {
            throw new RuntimeException("Không tìm thấy account: " + accountId);
        }
        return groupAccountRepository.findByAccountId(accountId)
                .stream()
                .map(GroupAccountDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<GroupAccountDTO> getAccountsByGroup(Integer groupId) {

        if (groupId == null || groupId <= 0) {
            throw new RuntimeException("Group ID phải lớn hơn 0");
        }

        if (!groupRepository.existsById(groupId)) {
            throw new RuntimeException("Không tìm thấy group: " + groupId);
        }
        return groupAccountRepository.findByGroupId(groupId)
                .stream()
                .map(GroupAccountDTO::new)
                .collect(Collectors.toList());
    }
}
