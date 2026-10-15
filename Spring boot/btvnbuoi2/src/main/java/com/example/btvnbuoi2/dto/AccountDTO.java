package com.example.btvnbuoi2.dto;

import com.example.btvnbuoi2.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
    private Integer id;
    private String email;
    private String username;
    private String fullName;
    private String departmentName;
    private String positionName;
    private LocalDateTime createDate;
    public AccountDTO(Account account) {
        this.id = account.getId();
        this.email = account.getEmail();
        this.username = account.getUsername();
        this.fullName = account.getFullName();
        if (account.getDepartment() != null) {
            this.departmentName = account.getDepartment().getName();
        }
        if (account.getPosition() != null) {
            this.positionName = account.getPosition().getName().toString();
        }
        this.createDate = account.getCreateDate();
    }
}
