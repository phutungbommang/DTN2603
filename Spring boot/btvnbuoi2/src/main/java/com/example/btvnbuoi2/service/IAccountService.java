package com.example.btvnbuoi2.service;

import com.example.btvnbuoi2.entity.Account;

import java.util.List;

public interface IAccountService {
    List<Account> getAll();
    Account getById(Integer id);
    Account create(Account account);
    Account update(Integer id, Account account);
    void delete(Integer id);
}
