import java.util.Date;

public class Exercise2 {
    public static void Question1(){
        System.out.println("Question 2.1");
        Account[] accounts = new Account[5];
        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = new Account();
            accounts[i].email    = "Email "     + (i + 1);
            accounts[i].userName = "User name " + (i + 1);
            accounts[i].fullName = "Full name " + (i + 1);
            accounts[i].createDate = new Date();
        }
        for (Account account : accounts) {
            System.out.println(account.email);
            System.out.println(account.userName);
            System.out.println(account.fullName);
            System.out.println(account.createDate);
        }
    }
}
