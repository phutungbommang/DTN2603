package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private int        accountId;
    private String     email;
    private String     username;
    private String     fullName;
    private Department department;
    private Position   position;
    private String     createDate;

}
