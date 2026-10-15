package com.example.btvnbuoi2.controller;

import com.example.btvnbuoi2.dto.GroupAccountDTO;
import com.example.btvnbuoi2.form.GroupAccountForm;
import com.example.btvnbuoi2.service.IGroupAccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GroupAccountController {
    private final IGroupAccountService groupAccountService;

    public GroupAccountController(
            IGroupAccountService groupAccountService) {
        this.groupAccountService = groupAccountService;
    }
    @PostMapping("/group-accounts")
    public GroupAccountDTO addAccountToGroup(
            @RequestBody GroupAccountForm form) {
        return groupAccountService.addAccountToGroup(form);
    }
    @GetMapping("/accounts/{accountId}/groups")
    public List<GroupAccountDTO> getGroupsByAccount(
            @PathVariable Integer accountId) {
        return groupAccountService.getGroupsByAccount(accountId);
    }
    @GetMapping("/groups/{groupId}/accounts")
    public List<GroupAccountDTO> getAccountsByGroup(
            @PathVariable Integer groupId) {
        return groupAccountService.getAccountsByGroup(groupId);
    }
}