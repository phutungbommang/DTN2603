package backend.service.impl;

import Common.StringCommon;
import backend.responsitory.IQLAccountResponsitory;
import backend.responsitory.impl.QLAccountResponsitory;
import backend.service.IQLAccountService;
import backend.service.IQLDepartmentService;
import backend.service.IQLPositionService;
import backend.service.ImportFileCSV;
import entity.Account;
import entity.Department;
import entity.Position;
import java.time.LocalDate;
import context.AccountContext;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class QLAccountServiceImpl implements IQLAccountService, ImportFileCSV<Account, AccountContext> {
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
        List<Account> accounts = repository.getAccounts();
        List<Department> departments = departmentRepository.getDepartments();
        List<Position> positions = positionRepository.getPositions();
        Set<String> setUsername = accounts.stream().map(Account::getUsername).collect(Collectors.toSet());
        Set<String> setEmail = accounts.stream().map(Account::getEmail).collect(Collectors.toSet());
        Map<Integer, Department> mapDepartmentId = departments.stream().collect(Collectors.toMap(
                                Department::getDepartmentId,
                                department -> department
                        ));
        Map<Integer, Position> mapPositionId = positions.stream().collect(Collectors.toMap(
                                Position::getPositionId,
                                position -> position
                        ));
        AccountContext context =
                new AccountContext(
                        setUsername,
                        setEmail,
                        mapDepartmentId,
                        mapPositionId
                );

        String pathErrorsFile =
                "C:\\Users\\media\\OneDrive\\Máy tính\\DTN2603\\Java core\\csv\\account_errors.csv";

        return this.importCSV(url, context, pathErrorsFile);
    }



    @Override
    public void validation(String line, List<Account> entities, List<String> listErrors, AccountContext context) {
        String[] values = line.split(",");
        if (values.length != 5) {
            listErrors.add(line + ",Sai số lượng cột");
            return;
        }
        List<String> errors = new ArrayList<>();
        String email = values[0].trim();
        if (email.length() < 6 || email.length() > 100) {
            errors.add("Email có độ dài từ 6 đến 100 kí tự");
        } else if (!email.matches(StringCommon.EMAIL_REGEX)) {
            errors.add("Email sai định dạng");
        } else if (context.getSetEmail().contains(email)) {
            errors.add("Email đã tồn tại");
        }
        String userName = values[1].trim();
        if (userName.length() < 6 || userName.length() > 100) {
            errors.add("Username có độ dài từ 6 đến 100 kí tự");
        } else if (context.getSetUsername().contains(userName)) {
            errors.add("Username đã tồn tại");
        }
        String fullName = values[2].trim();
        if (fullName.length() < 6 || fullName.length() > 100) {
            errors.add("FullName có độ dài từ 6 đến 100 kí tự");
        }
        int departmentId = 0;
        Department department = new Department();
        if (!values[3].trim().matches(StringCommon.NUMBER_REGEX)) {
            errors.add("DepartmentId phải là số");
        } else {
            departmentId = Integer.parseInt(values[3].trim());
            if (departmentId <= 0) {
                errors.add("DepartmentId phải lớn hơn 0");
            } else if (!context.getMapByDepartmentId().containsKey(departmentId)) {
                errors.add("DepartmentId không tồn tại");
            } else {
                department = context.getMapByDepartmentId().get(departmentId);
            }
        }
        int positionId = 0;
        Position position = new Position();
        if (!values[4].trim().matches(StringCommon.NUMBER_REGEX)) {
            errors.add("PositionId phải là số");
        } else {
            positionId = Integer.parseInt(values[4].trim());

            if (positionId <= 0) {
                errors.add("PositionId phải lớn hơn 0");
            } else if (!context.getMapByPositionId().containsKey(positionId)) {
                errors.add("PositionId không tồn tại");
            } else {
                position = context.getMapByPositionId().get(positionId);
            }
        }
        if (errors.isEmpty()) {
            Account account = new Account();
            account.setEmail(email);
            account.setUsername(userName);
            account.setFullName(fullName);
            account.setDepartment(department);
            account.setPosition(position);
            account.setCreateDate(LocalDate.now().toString());
            context.getSetEmail().add(email);
            context.getSetUsername().add(userName);
            entities.add(account);
        } else {
            String error = String.join(" - ", errors);
            listErrors.add(line + "," + error);
        }
    }

    @Override
    public void saveAll(List<Account> entities) {
        repository.themListAccount(entities);
    }

    @Override
    public void exportErrors(String header, List<String> listErrors, String pathErrorFile) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(pathErrorFile));
            bw.write(header + "error_message");
            bw.newLine();
            for (String error : listErrors) {
                bw.write(error);
                bw.newLine();
            }
            bw.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

