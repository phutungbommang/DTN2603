import java.util.Date;

public class GroupAccount {
    Group group;
    Account account;
    Date joinDate;

    public GroupAccount(Group group, Account account, Date joinDate) {
        this.group = group;
        this.account = account;
        this.joinDate = joinDate;
    }
}
