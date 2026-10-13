package com.example.btvnbuoi2.service.impl;

import com.example.btvnbuoi2.entity.Account;
import com.example.btvnbuoi2.entity.Department;
import com.example.btvnbuoi2.entity.Position;
import com.example.btvnbuoi2.repository.IAccountRepository;
import com.example.btvnbuoi2.repository.IDepartmentRepository;
import com.example.btvnbuoi2.repository.IPositionRepository;
import com.example.btvnbuoi2.service.IAccountService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class AccountServiceImpl implements IAccountService {
    private final IAccountRepository accountRepository;
    private final IDepartmentRepository departmentRepository;
    private final IPositionRepository positionRepository;

    public AccountServiceImpl(
            IAccountRepository accountRepository,
            IDepartmentRepository departmentRepository,
            IPositionRepository positionRepository) {
        this.accountRepository = accountRepository;
        this.departmentRepository = departmentRepository;
        this.positionRepository = positionRepository;
    }

    @Override
    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account getById(Integer id) {
        return accountRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Không tìm thấy account: " + id));
    }

    private void setRelations(Account account) {
        if (account.getDepartment() != null && account.getDepartment().getId() != null) {
            Department department = departmentRepository
                    .findById(account.getDepartment().getId())
                    .orElseThrow(() ->
                            new RuntimeException("Không tìm thấy department"));

            account.setDepartment(department);
        } else {
            account.setDepartment(null);
        }

        if (account.getPosition() != null
                && account.getPosition().getId() != null) {
            Position position = positionRepository
                    .findById(account.getPosition().getId())
                    .orElseThrow(() ->
                            new RuntimeException("Không tìm thấy position"));

            account.setPosition(position);
        } else {
            account.setPosition(null);
        }
    }

    @Override
    @Transactional
    public Account create(Account account) {
        if (accountRepository.existsByEmail(account.getEmail())) {
            throw new RuntimeException("Email đã tồn tại");
        }
        account.setId(null);
        setRelations(account);
        return accountRepository.save(account);
    }
    @Override
    @Transactional
    public Account update(Integer id, Account account) {
        Account oldAccount = getById(id);
        accountRepository.findByEmail(account.getEmail())
                .filter(existing -> !existing.getId().equals(id))
                .ifPresent(existing -> {
                    throw new RuntimeException("Email đã tồn tại");
                });
        setRelations(account);
        oldAccount.setEmail(account.getEmail());
        oldAccount.setUsername(account.getUsername());
        oldAccount.setFullName(account.getFullName());
        oldAccount.setDepartment(account.getDepartment());
        oldAccount.setPosition(account.getPosition());
        oldAccount.setCreateDate(account.getCreateDate());
        return accountRepository.save(oldAccount);
    }
    @Override
    public void delete(Integer id) {
        Account account = getById(id);
        accountRepository.delete(account);
    }
}
