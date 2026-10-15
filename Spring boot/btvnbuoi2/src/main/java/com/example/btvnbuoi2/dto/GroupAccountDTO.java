package com.example.btvnbuoi2.dto;

import com.example.btvnbuoi2.entity.GroupAccount;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupAccountDTO {

    private Integer groupId;
    private Integer accountId;
    private LocalDateTime joinDate;

    public GroupAccountDTO(GroupAccount groupAccount) {
        this.groupId = groupAccount.getGroup().getId();
        this.accountId = groupAccount.getAccount().getId();
        this.joinDate = groupAccount.getJoinDate();
    }
}
