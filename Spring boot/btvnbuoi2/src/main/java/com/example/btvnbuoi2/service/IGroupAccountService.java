package com.example.btvnbuoi2.service;

import com.example.btvnbuoi2.entity.GroupAccount;

import java.util.List;

public interface IGroupAccountService {
    GroupAccount addAccountToGroup(Integer groupId, Integer accountId);
    List<GroupAccount> getGroupsByAccount(Integer accountId);
    List<GroupAccount> getAccountsByGroup(Integer groupId);
}
