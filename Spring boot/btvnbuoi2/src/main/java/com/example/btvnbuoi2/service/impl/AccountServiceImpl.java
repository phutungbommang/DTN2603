package com.example.btvnbuoi2.service.impl;

import com.example.btvnbuoi2.dto.AccountDTO;
import com.example.btvnbuoi2.entity.Account;
import com.example.btvnbuoi2.entity.Department;
import com.example.btvnbuoi2.entity.Position;
import com.example.btvnbuoi2.form.AccountForm;
import com.example.btvnbuoi2.repository.IAccountRepository;
import com.example.btvnbuoi2.repository.IDepartmentRepository;
import com.example.btvnbuoi2.repository.IPositionRepository;
import com.example.btvnbuoi2.service.IAccountService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<AccountDTO> getAll() {
        return accountRepository.findAll()
                .stream()
                .map(AccountDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public AccountDTO getById(Integer id) {
        if (id == null || id <= 0) {
            throw new RuntimeException("ID phải lớn hơn 0");
        }
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Account"));
        return new AccountDTO(account);
    }

    private Account getEntityById(Integer id) {
        if (id == null || id <= 0) {
            throw new RuntimeException("ID phải lớn hơn 0");
        }
        return accountRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Không tìm thấy account: " + id));
    }

    @Override
    @Transactional
    public AccountDTO create(AccountForm form) {
        if (form.getEmail() == null || form.getEmail().trim().isEmpty()) {
            throw new RuntimeException("Email không được để trống");
        }
        if (form.getUsername() == null || form.getUsername().trim().isEmpty()) {
            throw new RuntimeException("Username không được để trống");
        }
        if (accountRepository.existsByEmail(form.getEmail())) {
            throw new RuntimeException("Email đã tồn tại");
        }
        if (accountRepository.existsByUsername(form.getUsername())) {
            throw new RuntimeException("Username đã tồn tại");
        }
        Account account = new Account();
        account.setEmail(form.getEmail());
        account.setUsername(form.getUsername());
        account.setFullName(form.getFullName());
        account.setCreateDate(form.getCreateDate());
        Department department = departmentRepository
                .findById(form.getDepartmentId())
                .orElseThrow(() ->
                        new RuntimeException("Không tìm thấy department"));
        Position position = positionRepository
                .findById(form.getPositionId())
                .orElseThrow(() ->
                        new RuntimeException("Không tìm thấy position"));
        account.setDepartment(department);
        account.setPosition(position);
        Account savedAccount = accountRepository.save(account);
        return new AccountDTO(savedAccount);
    }

    @Override
    @Transactional
    public AccountDTO update(Integer id, AccountForm form) {
        if (form.getEmail() == null || form.getEmail().trim().isEmpty()) {
            throw new RuntimeException("Email không được để trống");
        }
        if (form.getUsername() == null || form.getUsername().trim().isEmpty()) {
            throw new RuntimeException("Username không được để trống");
        }
        Account account = getEntityById(id);
        Account existingEmail = accountRepository.findByEmail(form.getEmail())
                .orElse(null);

        if (existingEmail != null && !existingEmail.getId().equals(id)) {
            throw new RuntimeException("Email đã tồn tại");
        }
        Account existingUsername = accountRepository.findByUsername(form.getUsername())
                .orElse(null);
        if (existingUsername != null && !existingUsername.getId().equals(id)) {
            throw new RuntimeException("Username đã tồn tại");
        }
        account.setEmail(form.getEmail());
        account.setUsername(form.getUsername());
        account.setFullName(form.getFullName());
        account.setCreateDate(form.getCreateDate());

        Department department = departmentRepository
                .findById(form.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy department"));

        Position position = positionRepository
                .findById(form.getPositionId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy position"));

        account.setDepartment(department);
        account.setPosition(position);

        Account savedAccount = accountRepository.save(account);

        return new AccountDTO(savedAccount);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Account account = getEntityById(id);
        accountRepository.delete(account);
    }
}
