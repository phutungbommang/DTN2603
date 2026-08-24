import java.util.Date;

public class Account {
    int accountId;
    String userName;
    String fullName;
    String email;
    Date createDate;
    Department department;
    Position position;

    public Account(int accountId, String fullName, String userName, String email, Date createDate, Department department, Position position) {
        this.accountId = accountId;
        this.fullName = fullName;
        this.userName = userName;
        this.email = email;
        this.createDate = createDate;
        this.department = department;
        this.position = position;
    }
}