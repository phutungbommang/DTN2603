package frontend;

import backend.QLAccount;
import backend.QLDepartment;
import backend.QLPosition;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        QLAccount qlAccount = new QLAccount();
        QLDepartment qlDepartment = new QLDepartment();
        QLPosition qlPosition = new QLPosition();
        while (true) {
            System.out.println("\nMenu");
            System.out.println("1. Hiển thị Account");
            System.out.println("2. Thêm Account");
            System.out.println("3. Xóa Account");
            System.out.println("4. Sửa Username Account");
            System.out.println("5. Hiển thị Department");
            System.out.println("6. Thêm Department");
            System.out.println("7. Xóa Department");
            System.out.println("8. Sửa Department");
            System.out.println("9. Hiển thị Position");
            System.out.println("10. Thêm Position");
            System.out.println("11. Xóa Position");
            System.out.println("12. Sửa Position");
            System.out.println("0. Thoát");
            System.out.print("Mời chọn chức năng: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    qlAccount.hienThiAccount();
                    break;
                case 2:
                    qlAccount.themAccount();
                    break;
                case 3:
                    qlAccount.xoaAccountTheoId();
                    break;
                case 4:
                    qlAccount.suaUsernameTheoId();
                    break;
                case 5:
                    qlDepartment.hienThiDepartment();
                    break;
                case 6:
                    qlDepartment.themDepartment();
                    break;
                case 7:
                    qlDepartment.xoaDepartmentTheoId();
                    break;
                case 8:
                    qlDepartment.suaDepartmentNameTheoId();
                    break;
                case 9:
                    qlPosition.hienThiPosition();
                    break;

                case 10:
                    qlPosition.themPosition();
                    break;

                case 11:
                    qlPosition.xoaPositionTheoId();
                    break;

                case 12:
                    qlPosition.suaPositionNameTheoId();
                    break;

                case 0:
                    System.out.println("Thoát chương trình!");
                    return;

                default:
                    System.out.println("Vui lòng nhập");
            }
        }
    }
}