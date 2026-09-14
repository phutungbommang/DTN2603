package backend.controller;

import backend.service.IQLAccountService;
import backend.service.impl.QLAccountServiceImpl;
import entity.Account;

import java.util.List;

public class QLAccountController {
    private IQLAccountService service;

    public QLAccountController() {
        service = new QLAccountServiceImpl();
    }

    public List<Account> getAccounts() {
        List<Account> accounts = service.getAccounts();
        return accounts;
    }

    public boolean xoaAccountTheoId(Integer accountId) {
        return service.xoaAccountTheoId(accountId);
    }

    public boolean suaUserTheoId(String usernameMoi,Integer accountId) {
        return service.suaUserTheoId(usernameMoi,accountId);
    }

    public boolean themAccount(String email,String username,String fullName,Integer departmentId,Integer positionId,String createDate) {
        return service.themAccount(email,username,fullName,departmentId,positionId,createDate);
    }

}
