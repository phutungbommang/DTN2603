package com.example.btvnbuoi2.service.impl;

import com.example.btvnbuoi2.entity.Account;
import com.example.btvnbuoi2.entity.Group;
import com.example.btvnbuoi2.entity.GroupAccount;
import com.example.btvnbuoi2.repository.IAccountRepository;
import com.example.btvnbuoi2.repository.IGroupAccountRepository;
import com.example.btvnbuoi2.repository.IGroupRepository;
import com.example.btvnbuoi2.service.IGroupAccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

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

    @Override
    @Transactional
    public GroupAccount addAccountToGroup(
            Integer groupId, Integer accountId) {

        Group group = groupRepository.findById(groupId)
                .orElseThrow(() ->
                        new RuntimeException("Không tìm thấy group: " + groupId));

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() ->
                        new RuntimeException("Không tìm thấy account: " + accountId));

        boolean exists = groupAccountRepository
                .existsByGroup_IdAndAccount_Id(groupId, accountId);

        if (exists) {
            throw new RuntimeException("Account đã tham gia group này");
        }
        GroupAccount groupAccount = new GroupAccount();
        groupAccount.setGroup(group);
        groupAccount.setAccount(account);
        groupAccount.setJoinDate(LocalDateTime.now());

        return groupAccountRepository.save(groupAccount);
    }

    @Override
    public List<GroupAccount> getGroupsByAccount(Integer accountId) {
        if (!accountRepository.existsById(accountId)) {
            throw new RuntimeException("Không tìm thấy account: " + accountId);}
        return groupAccountRepository.findByAccount_Id(accountId);
    }

    @Override
    public List<GroupAccount> getAccountsByGroup(Integer groupId) {
        if (!groupRepository.existsById(groupId)) {
            throw new RuntimeException("Không tìm thấy group: " + groupId);
        }
        return groupAccountRepository.findByGroup_Id(groupId);
    }
}
