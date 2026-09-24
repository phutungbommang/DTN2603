package backend.service.impl;

import Common.StringCommon;
import backend.responsitory.IQLAccountResponsitory;
import backend.responsitory.impl.QLAccountResponsitory;
import backend.service.IQLAccountService;
import backend.service.IQLDepartmentService;
import backend.service.IQLPositionService;
import entity.Account;
import entity.Department;
import entity.Position;
import java.time.LocalDate;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class QLAccountServiceImpl implements IQLAccountService {
    private IQLAccountResponsitory repository;
    private IQLDepartmentService departmentRepository;
    private IQLPositionService positionRepository;
    public QLAccountServiceImpl() {
        repository = new QLAccountResponsitory();// khoi tao gia tri
        departmentRepository = new QLDepartmentServiceImpl();
        positionRepository = new QLPositionServiceImpl();
    }
    @Override
    public List<Account> getAccounts() {
        List<Account> accounts = repository.getAccounts();
        return accounts;
    }

    @Override
    public boolean xoaAccountTheoId(Integer accountId) {
        return repository.xoaAccountTheoId(accountId);
    }

    @Override
    public boolean suaUserTheoId(String usernameMoi, Integer accountId) {
        return repository.suaUserTheoId(usernameMoi, accountId);
    }

    @Override
    public boolean themAccount(String email, String username, String fullName, Integer departmentId, Integer positionId, String createDate) {
        return repository.themAccount(email,username,fullName,departmentId,positionId,createDate);
    }

    @Override
    public boolean isValidEmail(String email) {
        return repository.isValidEmail(email);
    }

    @Override
    public boolean isUsernameExist(String username) {
        return repository.isUsernameExist(username);
    }

    @Override
    public boolean isEmailExist(String email) {
        return repository.isEmailExist(email);
    }

    @Override
    public boolean isAccountExist(int id) {
        return repository.isAccountExist(id);
    }

    @Override
    public String importCSV(String url) {
        if (!url.endsWith(".csv")) {
            return "File không đúng định dạng";
        }
        File file = new File(url);
        if (!file.exists()) {
            return "File không tồn tại";
        }
        List<Account> accounts = new ArrayList<>();
        List<String> listErrors = new ArrayList<>();
        String header = "";
        try (BufferedReader br = new BufferedReader(new FileReader(url))) {
            header = br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String error = validationAccount(line, accounts);
                if (error != null) {
                    System.out.println(error);
                    listErrors.add(error);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Account account : accounts) {
            repository.themAccount(
                    account.getEmail(),
                    account.getUsername(),
                    account.getFullName(),
                    account.getDepartment().getDepartmentId(),
                    account.getPosition().getPositionId(),
                    account.getCreateDate()
            );
        }
        if (!listErrors.isEmpty()) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\media\\OneDrive\\Máy tính\\DTN2603\\Java core\\csv\\account_errors.csv"))) {
                bw.write(header + ",error_message");
                bw.newLine();
                for (String error : listErrors) {
                    bw.write(error);
                    bw.newLine();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return listErrors.isEmpty() ? "Import thành công" : "Đã xuất file account_errors.csv";
    }

    public String validationAccount(String line, List<Account> accounts) {
        List<String> errors = new ArrayList<>();
        String[] values = line.split(",");
        if (values.length != 5) {
            return line + ",Sai số lượng cột";
        }
        String email = values[0].trim();
        if (!email.matches(StringCommon.EMAIL_REGEX)) {
            errors.add("Email sai định dạng");
        } else if (isEmailExist(email)) {
            errors.add("Email đã tồn tại");
        }
        String username = values[1].trim();
        if (username.length() < 6 || username.length() > 100) {
            errors.add("Username từ 6-100 ký tự");
        } else if (isUsernameExist(username)) {
            errors.add("Username đã tồn tại");
        }
        String fullName = values[2].trim();
        if (fullName.length() < 6 || fullName.length() > 100) {
            errors.add("FullName từ 6-100 ký tự");
        }
        int departmentId = 0;
        if (!values[3].matches(StringCommon.NUMBER_REGEX)) {
            errors.add("DepartmentId phải là số");
        } else {
            departmentId = Integer.parseInt(values[3]);
            if (!departmentRepository.isDepartmentExist(departmentId)) {
                errors.add("Department không tồn tại");
            }
        }
        int positionId = 0;
        if (!values[4].matches(StringCommon.NUMBER_REGEX)) {
            errors.add("PositionId phải là số");
        } else {
            positionId = Integer.parseInt(values[4]);
            if (!positionRepository.isPositionExist(positionId)) {
                errors.add("Position không tồn tại");
            }
        }
        if (errors.isEmpty()) {
            Department department = new Department(departmentId, null);
            Position position = new Position(positionId, null);
            Account account = new Account();
            account.setEmail(email);
            account.setUsername(username);
            account.setFullName(fullName);
            account.setDepartment(department);
            account.setPosition(position);
            account.setCreateDate(LocalDate.now().toString());

            accounts.add(account);

            return null;
        }
        return line + "," + String.join(" | ", errors);
    }


}
