import java.util.Scanner;

public class Exercise4 {
    public static void Question1() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap vao xau ki tu :");
        String kiTu = sc.nextLine();
        String thayThe = kiTu.replace(" ", "");
        int soKiTu = thayThe.length();
        System.out.println("Chuoi ban dau la : " + kiTu);
        System.out.println("Chuoi sau khi xpa khoan trang la : " + thayThe);
        System.out.println("So ki tu la : " + soKiTu);
    }

    public static void Question2() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap vao xau ki tu 1 :");
        String kiTu1 = sc.nextLine();
        System.out.println("Nhap vao xau ki tu 2 :");
        String kiTu2 = sc.nextLine();
        System.out.println(kiTu1 + " " + kiTu2);
    }

    public static void Question3() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap vao ten :");
        String ten = sc.nextLine();
        ten = ten.substring(0, 1).toUpperCase() + ten.substring(1).toLowerCase();
        System.out.println(ten);
    }

    public static void Question4() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap vao ten :");
        String ten = sc.nextLine();
        for (int i = 0; i < ten.length(); i++) {
            System.out.println("Ky tu thu " + (i + 1) + " la : " + ten.charAt(i));
        }
    }

    public static void Question5() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap vao ho : ");
        String ho = sc.nextLine();
        System.out.print("Nhap vao ten: ");
        String ten = sc.nextLine();
        System.out.println("Ho va ten day du la : " + ho + " " + ten);
    }

    public static void Question6() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ho ten :");
        String hoTen = sc.nextLine().trim();
        String[] words = hoTen.split("\\s+");
        System.out.println("Ho la : " + words[0]);
        System.out.println("Ten la : " + words[words.length - 1]);
        String middleName = "";
        for (int i = 1; i < words.length - 1; i++) {
            middleName += words[i] + " ";
        }
        System.out.println("Ten dem la : " + middleName.trim());
    }

    public static void Question7() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ho ten :");
        String hoTen = sc.nextLine();
        hoTen = hoTen.trim().replaceAll("\\s+", " ");
        String[] tu = hoTen.split(" ");
        String ketQua = "";
        for (String tuu : tu) {
            ketQua += tuu.substring(0, 1).toUpperCase()
                    + tuu.substring(1).toLowerCase()
                    + " ";
        }
        System.out.println("Ho va ten sau chuan hoa la : " + ketQua.trim());
    }

    public static void Question8(Group[] groups) {
        for (Group group : groups) {
            if (group.groupName.contains("Java")) {
                System.out.println(group.groupName);
            }
        }
    }

    public static void Question9(Group[] groups) {
        for (Group group : groups) {
            if (group.groupName.equals("Java")) {
                System.out.println(group.groupName);
            }
        }
    }

    public static void Question10() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap vao chuoi 1 :");
        String s1 = sc.nextLine();
        System.out.print("Nhap vao chuoi 2 :");
        String s2 = sc.nextLine();
        String ketQua = "";
        for (int i = s1.length() - 1; i >= 0; i--) {
            ketQua += s1.charAt(i);
        }
        if (ketQua.equals(s2)) {
            System.out.println("OK");
        } else {
            System.out.println("KO");
        }
    }

    public static void Question11() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap vao chuoi :");
        String chuoi = sc.nextLine();
        int count = 0;
        for (int i = 0; i < chuoi.length(); i++) {
            if (chuoi.charAt(i) == 'a') {
                count++;
            }
        }
        System.out.println("So lan xuat hien ki tu a la : " + count);
    }

    public static void Question12() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap vao chuoi :");
        String chuoi = sc.nextLine();
        String ketQua = "";
        for (int i = chuoi.length() - 1; i >= 0; i--) {
            ketQua += chuoi.charAt(i);
        }
        System.out.println(ketQua);
    }

    public static void Question13() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap vao chuoi :");
        String chuoi = sc.nextLine();
        boolean kiemTra = true;
        if (chuoi == null || chuoi.isEmpty()) {
            kiemTra = false;
        } else {
            for (int i = 0; i < chuoi.length(); i++) {
                if (Character.isDigit(chuoi.charAt(i))) {
                    kiemTra = false;
                    break;
                }
            }
        }
        System.out.println(kiemTra);
    }

    public static void Question14() {
        String chuoi = "VTI Academy";
        chuoi = chuoi.replace('e', '*');
        System.out.println(chuoi);
    }

    public static void Question15() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap vao chuoi :");
        String chuoi = sc.nextLine();
        chuoi = chuoi.trim();
        String[] tu = chuoi.split(" ");
        for (int i = tu.length - 1; i >= 0; i--) {
            System.out.print(tu[i] + " ");
        }
    }

    public static void Question16() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap vao chuoi :");
        String chuoi = sc.nextLine();
        System.out.print("Nhap n :");
        int n = sc.nextInt();
        if (chuoi.length() % n != 0) {
            System.out.println("KO");
        } else {
            for (int i = 0; i < chuoi.length(); i += n) {
                System.out.println(chuoi.substring(i, i + n));
            }
        }
    }
}
