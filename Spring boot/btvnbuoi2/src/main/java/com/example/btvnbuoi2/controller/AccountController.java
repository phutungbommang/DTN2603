
package com.example.btvnbuoi2.controller;

import com.example.btvnbuoi2.entity.Account;
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
    // Read: lấy danh sách account
    @GetMapping
    public List<Account> getAll() {
        return accountService.getAll();
    }

    // Read: lấy account theo ID
    @GetMapping("/{id}")
    public Account getById(@PathVariable Integer id) {
        return accountService.getById(id);
    }

    // Create: thêm account
    @PostMapping
    public Account create(@RequestBody Account account) {
        return accountService.create(account);
    }
    // Update: sửa account
    @PutMapping("/{id}")
    public Account update(@PathVariable Integer id, @RequestBody Account account) {
        return accountService.update(id, account);
    }

    // Delete: xóa account
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        accountService.delete(id);
        return "Xóa account thành công";
    }
}
