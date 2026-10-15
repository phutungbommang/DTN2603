package com.example.btvnbuoi2.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupAccountForm {
    private Integer groupId;
    private Integer accountId;
    private LocalDateTime joinDate;
}
