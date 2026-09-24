package frontend;

import backend.controller.QLAccountController;
import backend.controller.QLDepartmentController;
import backend.controller.QLPostitionController;
import entity.Account;
import entity.Department;
import entity.Position;
import entity.PositionName;
import utils.ScannerUtils;

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
        System.out.println("Hiển thị toàn bộ account");
        List<Account> accounts = accountController.getAccounts();
        this.showAccount(accounts);
    }
    public void themAccount() {
        String username;
        while (true) {
            System.out.println("Nhập Username >6 và <100 kí tự: ");
            username = ScannerUtils.inputString(6, 100);
            if (isUsernameExist(username)) {
                System.err.println("Username đã tồn tại");
                continue;
            }
            break;
        }
        String email;
        while (true) {
            System.out.println("Nhập Email: ");
            email = ScannerUtils.inputEmail();

            if (isEmailExist(email)) {
                System.err.println("Email đã tồn tại");
                continue;
            }
            break;
        }
        System.out.println("Nhập FullName: ");
        String fullName = ScannerUtils.inputString(6, 100);
        int departmentId;
        while (true) {
            System.out.println("Nhập Department Id: ");
            departmentId = ScannerUtils.inputInt(1, Integer.MAX_VALUE);
            if (!isDepartmentExist(departmentId)) {
                System.err.println("Department không tồn tại");
                continue;
            }
            break;
        }
        int positionId;
        while (true) {
            System.out.println("Nhập Position Id: ");
            positionId = ScannerUtils.inputInt(1, Integer.MAX_VALUE);
            if (!isPositionExist(positionId)) {
                System.err.println("Position không tồn tại");
                continue;
            }
            break;
        }
        System.out.println("Nhập Create Date (yyyy-MM-dd): ");
        String createDate = ScannerUtils.inputDate();
        boolean check = accountController.themAccount(
                email,
                username,
                fullName,
                departmentId,
                positionId,
                createDate
        );
        System.out.println(check ? "Thêm account thành công" : "Thêm account không thành công");
    }
    public void xoaAccount() {
        int accountId;
        while (true) {
            System.out.println("Nhập ID Account muốn xóa: ");
            accountId = ScannerUtils.inputInt(1, Integer.MAX_VALUE);
            if (!isAccountExist(accountId)) {
                System.err.println("Account không tồn tại");
                continue;
            }
            break;
        }
        boolean check = accountController.xoaAccountTheoId(accountId);
        System.out.println(check ? "Xóa account thành công" : "Xóa account không thành công");
    }
    public void suaAccount() {
        int accountId;
        while (true) {
            System.out.println("Nhập ID Account muốn sửa: ");
            accountId = ScannerUtils.inputInt(1, Integer.MAX_VALUE);
            if (!isAccountExist(accountId)) {
                System.err.println("Account không tồn tại");
                continue;
            }
            break;
        }
        String usernameMoi;
        while (true) {
            System.out.println("Nhập Username mới: ");
            usernameMoi = ScannerUtils.inputString(6, 100);
            if (isUsernameExist(usernameMoi)) {
                System.err.println("Username đã tồn tại");
                continue;
            }
            break;
        }
        boolean check = accountController.suaUserTheoId(usernameMoi, accountId);
        System.out.println(check ? "Sửa account thành công" : "Sửa account không thành công");
    }

    public void hienThiDepartment() {
        System.out.println("Hiển thị toàn bộ department");
        List<Department> departments = departmentController.getDepartments();
        this.showDepartment(departments);
    }

    public void themDepartment() {
        System.out.println("Nhập tên phòng ban (3-50 kí tự): ");
        String departmentName = ScannerUtils.inputString(3,50);
        boolean check = departmentController.themDepartment(departmentName);
        if (check) {
            System.out.println("Thêm phòng ban thành công");
        } else {
            System.out.println("Thêm phòng ban không thành công");
        }
    }
    public void xoaDepartment() {
        System.out.println("Chức năng xóa department");
        int departmentId = 0;
        while (true) {
            System.out.println("Nhập ID phòng ban muốn xóa: ");
            if (!scanner.hasNextInt()) {
                System.err.println("Vui lòng nhập số");
                scanner.nextLine();
                continue;
            }
            departmentId = scanner.nextInt();
            scanner.nextLine();
            if (!isDepartmentExist(departmentId)) {
                System.err.println("Department Id không tồn tại. Nhập lại ");
                continue;
            }
            break;
        }

        boolean check = departmentController.xoaDepartmentTheoId(departmentId);
        if (check) {
            System.out.println("Xóa phòng ban thành công");
        } else {
            System.out.println("Xóa phòng ban không thành công!");
        }
    }
    public void suaDepartment() {
        int departmentId = 0;
        while (true) {
            System.out.println("Nhập ID phòng ban muốn sửa: ");
            if (!scanner.hasNextInt()) {
                System.err.println("Vui lòng nhập số");
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
    private void importCSV() {
        // đưa file csv vào thông qua 1 đường dẫn: C:\Users\media\OneDrive\Máy tính\DTN2603\Java core\csv\input_acc.csv
        System.out.println("Nhập vào đường dẫn file csv muốn import: ");
        String url = scanner.nextLine();
        // đưa đường dẫn cho controller
        String message = accountController.importCSV(url);
        // đọc file -> ds các tài liệu trong file
        // lưu ds các tài liệu đó vào DB
        System.out.println(message);
    }
    public void menu() {
        while (true) {
            System.out.println("ACCOUNT");
            System.out.println("1. Hiển thị Account");
            System.out.println("2. Thêm Account");
            System.out.println("3. Xóa Account");
            System.out.println("4. Sửa Account");
            System.out.println("5. Import Account từ file csv.");
            System.out.println("\nDEPARTMENT");
            System.out.println("6. Hiển thị Department");
            System.out.println("7. Thêm Department");
            System.out.println("8. Xóa Department");
            System.out.println("9. Sửa Department");
            System.out.println("\nPOSITION");
            System.out.println("10. Hiển thị Position");
            System.out.println("11. Thêm Position");
            System.out.println("12. Xóa Position");
            System.out.println("13. Sửa Position");
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
                    importCSV();
                case "6":
                    hienThiDepartment();
                    break;
                case "7":
                    themDepartment();
                    break;
                case "8":
                    xoaDepartment();
                    break;
                case "9":
                    suaDepartment();
                    break;
                case "10":
                    hienThiPosition();
                    break;
                case "11":
                    themPosition();
                    break;
                case "12":
                    xoaPosition();
                    break;
                case "13":
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