package com.example.btvnbuoi2.dto;

import com.example.btvnbuoi2.entity.Group;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupDTO {
    private Integer id;
    private String name;
    private Integer creatorId;
    private String creatorName;
    private LocalDateTime createDate;
    public GroupDTO(Group group) {
        this.id = group.getId();
        this.name = group.getName();
        this.createDate = group.getCreateDate();
        if (group.getCreator() != null) {
            this.creatorId = group.getCreator().getId();
            this.creatorName = group.getCreator().getFullName();
        }
    }
}
