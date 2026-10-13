package com.example.btvnbuoi2.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "group_account")
@IdClass(GroupAccountId.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupAccount {

    @Id
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @Id
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "join_date")
    private LocalDateTime joinDate;
}
