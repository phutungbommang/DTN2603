package com.example.btvnbuoi2.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupAccountId implements Serializable {
    private Integer group;
    private Integer account;
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GroupAccountId)) {
            return false;
        }
        GroupAccountId other = (GroupAccountId) obj;
        return Objects.equals(group, other.group)
                && Objects.equals(account, other.account);
    }
    @Override
    public int hashCode() {
        return Objects.hash(group, account);
    }
}
