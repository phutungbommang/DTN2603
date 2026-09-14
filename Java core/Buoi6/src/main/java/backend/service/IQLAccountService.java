package backend.service;

import entity.Account;

import java.util.List;

public interface IQLAccountService {

    List<Account> getAccounts();
    boolean xoaAccountTheoId(Integer accountId);
    boolean suaUserTheoId(String usernameMoi, Integer accountId);
    boolean themAccount(String email, String username, String fullName, Integer departmentId, Integer positionId, String createDate);
}
