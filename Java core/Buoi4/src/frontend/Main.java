package frontend;

import backend.QLTV;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        QLTV qltv = new QLTV();
        Scanner sc = new Scanner(System.in);
            while (true) {
            System.out.println("\nQuan ly tai lieu");
            System.out.println("1. Them moi tai lieu");
            System.out.println("2. Xoa tai lieu theo ma");
            System.out.println("3. Hien thi thong tin tai lieu");
            System.out.println("4. Tim kiem tai lieu theo loai");
            System.out.println("5. Thoat");
            System.out.print("Chon: ");
            int chon = sc.nextInt();
            sc.nextLine();
            switch (chon) {
                case 1:
                    qltv.themMoiTaiLieu();
                    break;
                case 2:
                    qltv.xoaTaiLieuTheoMaTaiLieu();
                    break;
                case 3:
                    qltv.hienThiThongTinTaiLieu();
                    break;
                case 4:
                    qltv.timKiemTaiLieuTheoLoai();
                    break;
                case 5:
                    System.out.println("Thoat chuong trinh");
                    return;
                default:
                    System.out.println("Lua chon khong hop le, vui long nhap lai!");
            }
        }
    }
}
