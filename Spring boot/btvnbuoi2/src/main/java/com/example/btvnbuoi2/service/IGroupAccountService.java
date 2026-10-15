package com.example.btvnbuoi2.service;

import com.example.btvnbuoi2.dto.GroupAccountDTO;
import com.example.btvnbuoi2.entity.GroupAccount;
import com.example.btvnbuoi2.form.GroupAccountForm;

import java.util.List;

public interface IGroupAccountService {
    GroupAccountDTO addAccountToGroup(GroupAccountForm form);
    List<GroupAccountDTO> getGroupsByAccount(Integer accountId);
    List<GroupAccountDTO> getAccountsByGroup(Integer groupId);
}
