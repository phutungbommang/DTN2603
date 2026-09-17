package frontend;

import backend.controller.QLAccountController;
import backend.controller.QLDepartmentController;
import backend.controller.QLPostitionController;
import entity.Account;
import entity.Department;
import entity.Position;
import entity.PositionName;

import java.util.List;
import java.util.Scanner;

public class Function {
    private Scanner scanner;
    private QLAccountController accountController;
    private QLDepartmentController departmentController;
    private QLPostitionController positionController;

    public Function() {
        scanner = new Scanner(System.in);
        accountController = new QLAccountController();
        departmentController = new QLDepartmentController();
        positionController = new QLPostitionController();
    }
    public String checkLength(int min, int max) {
        while (true) {
            String text = scanner.nextLine();
            if (text.trim().length() < min || text.trim().length() > max) {
                System.err.println(String.format("Vui lòng nhập từ %d đến %d kí tự!", min, max));
                continue;
            }
            return text;
        }
    }
    private boolean isValidEmail(String email) {
        return email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");
    }

    private boolean isUsernameExist(String username) {
        for (Account acc : accountController.getAccounts()) {
            if (acc.getUsername().equals(username)) return true;
        }
        return false;
    }
    private boolean isEmailExist(String email) {
        for (Account acc : accountController.getAccounts()) {
            if (acc.getEmail().equals(email)) return true;
        }
        return false;
    }
    private boolean isAccountExist(int id) {
        for (Account acc : accountController.getAccounts()) {
            if (acc.getAccountId() == id) return true;
        }
        return false;
    }
    private boolean isDepartmentExist(int id) {
        for (Department dept : departmentController.getDepartments()) {
            if (dept.getDepartmentId() == id) return true;
        }
        return false;
    }
    private boolean isPositionExist(int id) {
        for (Position pos : positionController.getPositions()) {
            if (pos.getPositionId() == id) return true;
        }
        return false;
    }
    public void hienThiAccount() {
        System.out.println("==== Hiển thị toàn bộ account ====");
        List<Account> accounts = accountController.getAccounts();
        this.showAccount(accounts);
    }
    public void themAccount() {
        String username = "";
        while (true) {
            System.out.println("Nhập Username (>6 và <100 kí tự, không trùng): ");
            username = this.checkLength(6, 100);
            if (isUsernameExist(username)) {
                System.err.println("Username đã tồn tại. Nhập lại!");
                continue;
            }
            break;
        }
        String email = "";
        while (true) {
            System.out.println("Nhập Email (>6 và <100 kí tự, đúng định dạng, không trùng): ");
            email = this.checkLength(6, 100);
            if (!isValidEmail(email)) {
                System.err.println("Email không đúng định dạng (VD: abc@gmail.com). Nhập lại!");
                continue;
            }
            if (isEmailExist(email)) {
                System.err.println("Email đã tồn tại. Nhập lại!");
                continue;
            }
            break;
        }
        System.out.println("Nhập FullName (>6 và <100 kí tự): ");
        String fullName = this.checkLength(6, 100);
        int departmentId = 0;
        while (true) {
            System.out.println("Nhập Department Id: ");
            if (!scanner.hasNextInt()) {
                System.err.println("Vui lòng nhập số!");
                scanner.nextLine();
                continue;
            }
            departmentId = scanner.nextInt();
            scanner.nextLine();
            if (!isDepartmentExist(departmentId)) {
                System.err.println("Department Id không tồn tại. Nhập lại!");
                continue;
            }
            break;
        }
        int positionId = 0;
        while (true) {
            System.out.println("Nhập Position Id: ");
            if (!scanner.hasNextInt()) {
                System.err.println("Vui lòng nhập số!");
                scanner.nextLine();
                continue;
            }
            positionId = scanner.nextInt();
            scanner.nextLine();
            if (!isPositionExist(positionId)) {
                System.err.println("Position Id không tồn tại. Nhập lại!");
                continue;
            }
            break;
        }
        String createDate = "";
        while (true) {
            System.out.println("Nhập Create Date (yyyy-MM-dd): ");
            createDate = scanner.nextLine();
            if (!createDate.trim().matches("^(?:(?:19|20)\\d\\d-(?:(?:0[13578]|1[02])-(?:0[1-9]|[12]\\d|3[01])|(?:0[469]|11)-(?:0[1-9]|[12]\\d|30)|02-(?:0[1-9]|1\\d|2[0-8]))|(?:(?:19|20)(?:[02468][048]|[13579][26])|(?:20)00)-02-29)$")) {
                System.err.println("Ngày không hợp lệ. Nhập lại (yyyy-MM-dd)!");
                continue;
            }
            break;
        }

        boolean check = accountController.themAccount(email, username, fullName, departmentId, positionId, createDate);
        if (check) {
            System.out.println("Thêm account thành công!");
        } else {
            System.out.println("Thêm account không thành công!");
        }
    }
    public void xoaAccount() {
        System.out.println("==== Chức năng xóa account ====");
        int accountId = 0;
        while (true) {
            System.out.println("Nhập ID Account muốn xóa: ");
            if (!scanner.hasNextInt()) {
                System.err.println("Vui lòng nhập số!");
                scanner.nextLine();
                continue;
            }
            accountId = scanner.nextInt();
            scanner.nextLine();
            if (!isAccountExist(accountId)) {
                System.err.println("Account Id không tồn tại. Nhập lại!");
                continue;
            }
            break;
        }
        boolean check = accountController.xoaAccountTheoId(accountId);
        if (check) {
            System.out.println("Xóa account thành công!");
        } else {
            System.out.println("Xóa account không thành công!");
        }
    }
    public void suaAccount() {
        // --- Id ---
        int accountId = 0;
        while (true) {
            System.out.println("Nhập ID Account muốn sửa: ");
            if (!scanner.hasNextInt()) {
                System.err.println("Vui lòng nhập số!");
                scanner.nextLine();
                continue;
            }
            accountId = scanner.nextInt();
            scanner.nextLine();
            if (!isAccountExist(accountId)) {
                System.err.println("Account Id không tồn tại. Nhập lại!");
                continue;
            }
            break;
        }
        String usernameMoi = "";
        while (true) {
            System.out.println("Nhập Username mới (>6 và <100 kí tự, không trùng): ");
            usernameMoi = this.checkLength(6, 100);
            if (isUsernameExist(usernameMoi)) {
                System.err.println("Username đã tồn tại. Nhập lại!");
                continue;
            }
            break;
        }

        boolean check = accountController.suaUserTheoId(usernameMoi, accountId);
        if (check) {
            System.out.println("Sửa account thành công!");
        } else {
            System.out.println("Sửa account không thành công!");
        }
    }

    public void hienThiDepartment() {
        System.out.println("Hiển thị toàn bộ department");
        List<Department> departments = departmentController.getDepartments();
        this.showDepartment(departments);
    }

    public void themDepartment() {
        System.out.println("Nhập tên phòng ban (3-50 kí tự): ");
        String departmentName = this.checkLength(3, 50);
        boolean check = departmentController.themDepartment(departmentName);
        if (check) {
            System.out.println("Thêm phòng ban thành công!");
        } else {
            System.out.println("Thêm phòng ban không thành công!");
        }
    }
    public void xoaDepartment() {
        System.out.println("Chức năng xóa department");
        int departmentId = 0;
        while (true) {
            System.out.println("Nhập ID phòng ban muốn xóa: ");
            if (!scanner.hasNextInt()) {
                System.err.println("Vui lòng nhập số!");
                scanner.nextLine();
                continue;
            }
            departmentId = scanner.nextInt();
            scanner.nextLine();
            if (!isDepartmentExist(departmentId)) {
                System.err.println("Department Id không tồn tại. Nhập lại!");
                continue;
            }
            break;
        }

        boolean check = departmentController.xoaDepartmentTheoId(departmentId);
        if (check) {
            System.out.println("Xóa phòng ban thành công!");
        } else {
            System.out.println("Xóa phòng ban không thành công!");
        }
    }
    public void suaDepartment() {
        int departmentId = 0;
        while (true) {
            System.out.println("Nhập ID phòng ban muốn sửa: ");
            if (!scanner.hasNextInt()) {
                System.err.println("Vui lòng nhập số!");
                scanner.nextLine();
                continue;
            }
            departmentId = scanner.nextInt();
            scanner.nextLine();
            if (!isDepartmentExist(departmentId)) {
                System.err.println("Department Id không tồn tại. Nhập lại!");
                continue;
            }
            break;
        }
        System.out.println("Nhập tên phòng ban mới (3-50 kí tự): ");
        String departmentName = this.checkLength(3, 50);
        boolean check = departmentController.suaDepartmentNameTheoId(departmentId, departmentName);
        if (check) {
            System.out.println("Sửa phòng ban thành công!");
        } else {
            System.out.println("Sửa phòng ban không thành công!");
        }
    }
    public void hienThiPosition() {
        System.out.println("==== Hiển thị toàn bộ position ====");
        List<Position> positions = positionController.getPositions();
        this.showPosition(positions);
    }

    public void themPosition() {
        System.out.println("Mời bạn chọn chức vụ: 1. DEV   2. TEST   3. SCRUM_MASTER   Khác. PM");
        String choice = scanner.nextLine();
        PositionName positionName = null;
        switch (choice) {
            case "1":
                positionName = PositionName.DEV;
                break;
            case "2":
                positionName = PositionName.TEST;
                break;
            case "3":
                positionName = PositionName.SCRUM_MASTER;
                break;
            default:
                positionName = PositionName.PM;
                break;
        }
        boolean check = positionController.themPosition(positionName);
        if (check) {
            System.out.println("Thêm chức vụ thành công!");
        } else {
            System.out.println("Thêm chức vụ không thành công!");
        }
    }
    public void xoaPosition() {
        System.out.println("Chức năng xóa position");
        int positionId = 0;
        while (true) {
            System.out.println("Nhập ID chức vụ muốn xóa: ");
            if (!scanner.hasNextInt()) {
                System.err.println("Vui lòng nhập số!");
                scanner.nextLine();
                continue;
            }
            positionId = scanner.nextInt();
            scanner.nextLine();
            if (!isPositionExist(positionId)) {
                System.err.println("Position Id không tồn tại. Nhập lại!");
                continue;
            }
            break;
        }
        boolean check = positionController.xoaPositionTheoId(positionId);
        if (check) {
            System.out.println("Xóa chức vụ thành công!");
        } else {
            System.out.println("Xóa chức vụ không thành công!");
        }
    }
    public void suaPosition() {
        int positionId = 0;
        while (true) {
            System.out.println("Nhập ID chức vụ muốn sửa: ");
            if (!scanner.hasNextInt()) {
                System.err.println("Vui lòng nhập số!");
                scanner.nextLine();
                continue;
            }
            positionId = scanner.nextInt();
            scanner.nextLine();
            if (!isPositionExist(positionId)) {
                System.err.println("Position Id không tồn tại. Nhập lại!");
                continue;
            }
            break;
        }
        System.out.println("Mời bạn chọn chức vụ mới: 1. DEV   2. TEST   3. SCRUM_MASTER   Khác. PM");
        String choice = scanner.nextLine();
        PositionName positionName = null;
        switch (choice) {
            case "1":
                positionName = PositionName.DEV;
                break;
            case "2":
                positionName = PositionName.TEST;
                break;
            case "3":
                positionName = PositionName.SCRUM_MASTER;
                break;
            default:
                positionName = PositionName.PM;
                break;
        }
        boolean check = positionController.suaPositionNameTheoId(positionId, positionName);
        if (check) {
            System.out.println("Sửa chức vụ thành công!");
        } else {
            System.out.println("Sửa chức vụ không thành công!");
        }
    }
    public void showAccount(List<Account> accounts) {
        System.out.println("+----+------------------------------+------------------+--------------------+----------------------+------------------+");
        System.out.printf("|%-4s|%-30s|%-18s|%-20s|%-22s|%-18s|%n",
                " ID", " Email", " Username", " Full Name", " Department", " Position");
        System.out.println("+----+------------------------------+------------------+--------------------+----------------------+------------------+");
        if (accounts.size() > 0) {
            for (Account account : accounts) {
                System.out.printf("|%-4d|%-30s|%-18s|%-20s|%-22s|%-18s|%n",
                        account.getAccountId(), account.getEmail(), account.getUsername(),
                        account.getFullName(), account.getDepartment(), account.getPosition());
            }
        } else {
            System.out.printf("|%77s|\n", "Không có thông tin");
        }
        System.out.println("+----+------------------------------+------------------+--------------------+----------------------+------------------+");
    }
    public void showDepartment(List<Department> departments) {
        System.out.println("+-------------------------+-------------------------+");
        System.out.printf("|%25s|%25s|\n", "Id phong ban", "Tên phong ban");
        System.out.println("+-------------------------+-------------------------+");
        if (departments.size() > 0) {
            for (Department department : departments) {
                System.out.printf("|%25s|%25s|\n", department.getDepartmentId(), department.getDepartmentName());
            }
        } else {
            System.out.printf("|%51s|\n", "Không có thông tin");
        }
        System.out.println("+-------------------------+-------------------------+");
    }
    public void showPosition(List<Position> positions) {
        System.out.println("+-------------------------+-------------------------+");
        System.out.printf("|%25s|%25s|\n", "Id chuc vu", "Tên chuc vu");
        System.out.println("+-------------------------+-------------------------+");
        if (positions.size() > 0) {
            for (Position position : positions) {
                System.out.printf("|%25s|%25s|\n", position.getPositionId(), position.getPositionName());
            }
        } else {
            System.out.printf("|%51s|\n", "Không có thông tin");
        }
        System.out.println("+-------------------------+-------------------------+");
    }
    public void menu() {
        while (true) {
            System.out.println("ACCOUNT");
            System.out.println("1. Hiển thị Account");
            System.out.println("2. Thêm Account");
            System.out.println("3. Xóa Account");
            System.out.println("4. Sửa Account");
            System.out.println("\nDEPARTMENT");
            System.out.println("5. Hiển thị Department");
            System.out.println("6. Thêm Department");
            System.out.println("7. Xóa Department");
            System.out.println("8. Sửa Department");
            System.out.println("\nPOSITION");
            System.out.println("9. Hiển thị Position");
            System.out.println("10. Thêm Position");
            System.out.println("11. Xóa Position");
            System.out.println("12. Sửa Position");
            System.out.println("\n0. Thoát");
            System.out.print("Mời chọn chức năng: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    hienThiAccount();
                    break;
                case "2":
                    themAccount();
                    break;
                case "3":
                    xoaAccount();
                    break;
                case "4":
                    suaAccount();
                    break;
                case "5":
                    hienThiDepartment();
                    break;
                case "6":
                    themDepartment();
                    break;
                case "7":
                    xoaDepartment();
                    break;
                case "8":
                    suaDepartment();
                    break;
                case "9":
                    hienThiPosition();
                    break;
                case "10":
                    themPosition();
                    break;
                case "11":
                    xoaPosition();
                    break;
                case "12":
                    suaPosition();
                    break;
                case "0":
                    System.exit(0);
                default:
                    System.out.println("Chọn sai! Chọn lại!");
            }
        }
    }
}