package com.example.btvnbuoi2.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupForm {
    private String name;
    private Integer creatorId;
    private LocalDateTime createDate;
}

