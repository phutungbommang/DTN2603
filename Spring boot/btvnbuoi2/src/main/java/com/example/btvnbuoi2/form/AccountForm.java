package com.example.btvnbuoi2.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountForm {
    private Integer id;
    private String email;
    private String username;
    private String fullName;
    private Integer departmentId;
    private Integer positionId;
    private LocalDateTime createDate;
}
