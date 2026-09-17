package backend.service.impl;

import backend.responsitory.IQLAccountResponsitory;
import backend.responsitory.impl.QLAccountResponsitory;
import backend.service.IQLAccountService;
import entity.Account;

import java.util.List;

public class QLAccountServiceImpl implements IQLAccountService {
    private IQLAccountResponsitory repository;
    public QLAccountServiceImpl() {
        repository = new QLAccountResponsitory();// khoi tao gia tri
    }
    @Override
    public List<Account> getAccounts() {
        List<Account> accounts = repository.getAccounts();
        return accounts;
    }

    @Override
    public boolean xoaAccountTheoId(Integer accountId) {
        return repository.xoaAccountTheoId(accountId);
    }

    @Override
    public boolean suaUserTheoId(String usernameMoi, Integer accountId) {
        return repository.suaUserTheoId(usernameMoi, accountId);
    }

    @Override
    public boolean themAccount(String email, String username, String fullName, Integer departmentId, Integer positionId, String createDate) {
        return repository.themAccount(email,username,fullName,departmentId,positionId,createDate);
    }

    @Override
    public boolean isValidEmail(String email) {
        return repository.isValidEmail(email);
    }

    @Override
    public boolean isUsernameExist(String username) {
        return repository.isUsernameExist(username);
    }

    @Override
    public boolean isEmailExist(String email) {
        return repository.isEmailExist(email);
    }

    @Override
    public boolean isAccountExist(int id) {
        return repository.isAccountExist(id);
    }


}
