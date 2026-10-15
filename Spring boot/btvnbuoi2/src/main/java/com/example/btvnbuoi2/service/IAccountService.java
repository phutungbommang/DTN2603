package com.example.btvnbuoi2.service;

import com.example.btvnbuoi2.dto.AccountDTO;
import com.example.btvnbuoi2.entity.Account;
import com.example.btvnbuoi2.form.AccountForm;

import java.util.List;

public interface IAccountService {
    List<AccountDTO> getAll();
    AccountDTO getById(Integer id);
    AccountDTO create(AccountForm form);
    AccountDTO update(Integer id, AccountForm form);
    void delete(Integer id);
}
