package utils;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ScannerUtils {

    private static final Scanner scanner = new Scanner(System.in);

    public static String inputString(int min, int max) {
        while (true) {
            String text = scanner.nextLine().trim();

            if (text.length() < min || text.length() > max) {
                System.err.printf("Vui lòng nhập từ %d đến %d ký tự%n", min, max);
                continue;
            }

            return text;
        }
    }

    public static int inputInt(int min, int max) {
        while (true) {
            try {
                int number = Integer.parseInt(scanner.nextLine());

                if (number < min || number > max) {
                    System.err.printf("Vui lòng nhập từ %d đến %d%n", min, max);
                    continue;
                }

                return number;
            } catch (Exception e) {
                System.err.println("Vui lòng nhập số");
            }
        }
    }

    public static String inputEmail() {
        while (true) {
            String email = scanner.nextLine().trim();

            if (!email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
                System.err.println("Email không đúng định dạng");
                continue;
            }

            return email;
        }
    }

    public static String inputDate() {
        while (true) {
            String date = scanner.nextLine().trim();

            try {
                LocalDate.parse(date);
                return date;
            } catch (DateTimeParseException e) {
                System.err.println("Ngày không hợp lệ (yyyy-MM-dd)");
            }
        }
    }
}