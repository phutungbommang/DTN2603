
package com.example.btvnbuoi2.controller;

import com.example.btvnbuoi2.entity.GroupAccount;
import com.example.btvnbuoi2.service.IGroupAccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GroupAccountController {
    private final IGroupAccountService groupAccountService;
    public GroupAccountController(IGroupAccountService groupAccountService) {
        this.groupAccountService = groupAccountService;
    }

    // thêm account vào group
    @PostMapping("/groups/{groupId}/accounts/{accountId}")
    public GroupAccount addAccountToGroup(@PathVariable Integer groupId, @PathVariable Integer accountId) {
        return groupAccountService.addAccountToGroup(groupId, accountId);
    }

    // xem account tham gia những group nào
    @GetMapping("/accounts/{accountId}/groups")
    public List<GroupAccount> getGroupsByAccount(@PathVariable Integer accountId) {
        return groupAccountService.getGroupsByAccount(accountId);
    }

    // xem group có những account nào
    @GetMapping("/groups/{groupId}/accounts")
    public List<GroupAccount> getAccountsByGroup(@PathVariable Integer groupId) {
        return groupAccountService.getAccountsByGroup(groupId);
    }
}
