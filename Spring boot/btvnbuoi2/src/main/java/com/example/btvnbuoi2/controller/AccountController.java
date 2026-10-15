package com.example.btvnbuoi2.controller;

import com.example.btvnbuoi2.dto.AccountDTO;
import com.example.btvnbuoi2.form.AccountForm;
import com.example.btvnbuoi2.service.IAccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final IAccountService accountService;

    public AccountController(IAccountService accountService) {
        this.accountService = accountService;
    }

    // Read: Lấy danh sách account
    @GetMapping
    public List<AccountDTO> getAll() {
        return accountService.getAll();
    }

    // Read: Lấy account theo ID
    @GetMapping("/{id}")
    public AccountDTO getById(@PathVariable Integer id) {
        return accountService.getById(id);
    }

    // Create: Thêm account
    @PostMapping
    public AccountDTO create(@RequestBody AccountForm form) {
        return accountService.create(form);
    }

    // Update: Cập nhật account theo ID
    @PutMapping("/{id}")
    public AccountDTO update(
            @PathVariable Integer id,
            @RequestBody AccountForm form) {
        return accountService.update(id, form);
    }

    // Delete: Xóa account theo ID
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        accountService.delete(id);
        return "Xóa account thành công";
    }
}
