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

    // ACCOUNT
    public void hienThiAccount() {
        System.out.println("==== Hiển thị toàn bộ account ====");
        // ds tài liệu để hieern thị
        List<Account> accounts = accountController.getAccounts();
        this.showAccount(accounts);
    }

    public void themAccount() {
        System.out.print("Nhập Email: ");
        String email = scanner.nextLine();
        System.out.print("Nhập Username: ");
        String username = scanner.nextLine();
        System.out.print("Nhập FullName: ");
        String fullName = scanner.nextLine();
        System.out.print("Nhập Department Id: ");
        int departmentId = scanner.nextInt();
        System.out.print("Nhập Position Id: ");
        int positionId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nhập Create Date: ");
        String createDate = scanner.nextLine();
        boolean check = accountController.themAccount(email, username, fullName, departmentId, positionId, createDate);
        if (check) {
            System.out.println("Thêm thành công");
        } else {
            System.out.println("Thêm thất bại");
        }
    }

    public void xoaAccount() {
        System.out.print("Nhập ID Account cần xóa: ");
        Integer accountId = scanner.nextInt();
        scanner.nextLine();
        boolean check = accountController.xoaAccountTheoId(accountId);
        if (check) {
            System.out.println("Xóa thành công");
        }
    }

    public void suaAccount() {
        System.out.print("Nhập ID Account cần sửa: ");
        Integer accountId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nhập Username mới: ");
        String usernameMoi = scanner.nextLine();
        boolean check = accountController.suaUserTheoId(usernameMoi, accountId);
        if (check) {
            System.out.println("Sửa thành công");
        }
    }

    // DEPARTMENT
    public void hienThiDepartment() {
        System.out.println("==== Hiển thị toàn bộ department ====");
        // ds tài liệu để hieern thị
        List<Department> departments = departmentController.getDepartments();
        this.showDepartment(departments);
    }

    public void themDepartment() {
        System.out.print("Nhập tên phòng ban: ");
        String departmentName = scanner.nextLine();
        boolean check = departmentController.themDepartment(departmentName);
        if (check) {
            System.out.println("Thêm thành công");
        } else {
            System.out.println("Thêm thất bại");
        }
    }

    public void xoaDepartment() {
        System.out.print("Nhập ID phòng ban cần xóa: ");
        Integer departmentId = scanner.nextInt();
        scanner.nextLine();
        boolean check = departmentController.xoaDepartmentTheoId(departmentId);
        if (check) {
            System.out.println("Xóa thành công");
        }
    }

    public void suaDepartment() {
        System.out.print("Nhập ID phòng ban cần sửa: ");
        Integer departmentId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nhập tên phòng ban mới: ");
        String departmentName = scanner.nextLine();
        boolean check = departmentController.suaDepartmentNameTheoId(departmentId, departmentName);
        if (check) {
            System.out.println("Sửa thành công");
        }
    }

    // POSITION
    public void hienThiPosition(){
        System.out.println("==== Hiển thị toàn bộ position ====");
        // ds tài liệu để hieern thị
        List<Position> positions = positionController.getPositions();
        this.showPosition(positions);
    }
    public void xoaPosition(){
        System.out.print("Nhập ID Position cần xóa: ");
        Integer positionId = scanner.nextInt();
        scanner.nextLine();
        boolean check = positionController.xoaPositionTheoId(positionId);
        if (check) {
            System.out.println("Xóa thành công");
        }
    }
    public void themPosition(){
        System.out.println("1. DEV");
        System.out.println("2. TEST");
        System.out.println("3. SCRUM_MASTER");
        System.out.println("4. PM");
        System.out.print("Chọn chức vụ: ");
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
            case "4":
                positionName = PositionName.PM;
                break;
        }
        boolean check = positionController.themPosition(positionName);
        if (check) {
            System.out.println("Thêm thành công");
        } else {
            System.out.println("Thêm thất bại");
        }

    }
    public void suaPosition(){
        System.out.print("Nhập ID Position cần sửa: ");
        Integer positionId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("1. DEV");
        System.out.println("2. TEST");
        System.out.println("3. SCRUM_MASTER");
        System.out.println("4. PM");
        System.out.print("Mời chọn Position mới: ");
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
            case "4":
                positionName = PositionName.PM;
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ!");
                return;
        }
        boolean check = positionController.suaPositionNameTheoId(positionId,positionName);
        if (check) {
            System.out.println("Sửa thành công");
        }
    }

    public void showAccount(List<Account> accounts) {
        System.out.println("+----+------------------------------+------------------+--------------------+----------------------+------------------+");
        System.out.printf("|%-4s|%-30s|%-18s|%-20s|%-22s|%-18s|%n",
                " ID", " Email", " Username", " Full Name", " Department", " Position");
        System.out.println("+----+------------------------------+------------------+--------------------+----------------------+------------------+");
        if (accounts.size() > 0) {
            for (Account account : accounts) {
                System.out.printf("|%-4d|%-30s|%-18s|%-20s|%-22s|%-18s|%n", account.getAccountId(), account.getEmail(), account.getUsername(),account.getFullName(),account.getDepartment(),account.getPosition());
            }
        } else {
            System.out.printf("|%77s|\n", "Không có thông tin");
        }
        System.out.println("+-------------------------+-------------------------+-------------------------+");
    }
    public void showDepartment(List<Department> departments){
        System.out.println("+-------------------------+-------------------------+");
        System.out.printf("|%25s|%25s|\n", "Id phong ban", "Tên phong ban");
        System.out.println("+-------------------------+-------------------------+");
        if (departments.size() > 0) {
            for (Department department : departments) {
                System.out.printf("|%25s|%25s|\n", department.getDepartmentId(), department.getDepartmentName());
            }
        } else {
            System.out.printf("|%77s|\n", "Không có thông tin");
        }
        System.out.println("+-------------------------+-------------------------+");
    }
    public void showPosition(List<Position> positions){
        System.out.println("+-------------------------+-------------------------+");
        System.out.printf("|%25s|%25s|\n", "Id chuc vu", "Tên chuc vu");
        System.out.println("+-------------------------+-------------------------+");
        if (positions.size() > 0) {
            for (Position position : positions) {
                System.out.printf("|%25s|%25s|\n", position.getPositionId(), position.getPositionName());
            }
        } else {
            System.out.printf("|%77s|\n", "Không có thông tin");
        }
        System.out.println("+-------------------------+-------------------------+");
    }
    public void menu() {
        while (true) {
            System.out.println("\n========= QUẢN LÝ HỆ THỐNG =========");

            System.out.println("===== ACCOUNT =====");
            System.out.println("1. Hiển thị Account");
            System.out.println("2. Thêm Account");
            System.out.println("3. Xóa Account");
            System.out.println("4. Sửa Account");

            System.out.println("\n===== DEPARTMENT =====");
            System.out.println("5. Hiển thị Department");
            System.out.println("6. Thêm Department");
            System.out.println("7. Xóa Department");
            System.out.println("8. Sửa Department");

            System.out.println("\n===== POSITION =====");
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
                    System.out.println("Thoát chương trình!");
                    return;

                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }
}
