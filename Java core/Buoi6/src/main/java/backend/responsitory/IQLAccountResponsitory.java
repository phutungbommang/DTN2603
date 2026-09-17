package backend.responsitory;

import entity.Account;

import java.util.List;

public interface IQLAccountResponsitory {
    List<Account> getAccounts();
    boolean suaUserTheoId(String usernameMoi,Integer accountId);
    boolean xoaAccountTheoId(Integer accountId);
    boolean themAccount(String email,String username,String fullName,Integer departmentId,Integer positionId,String createDate);
    boolean isValidEmail(String email);
    boolean isUsernameExist(String username);
    boolean isEmailExist(String email);
    boolean isAccountExist(int id);
}
