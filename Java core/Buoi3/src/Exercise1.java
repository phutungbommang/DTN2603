import java.util.Random;
import java.util.Scanner;

public class Exercise1 {
    public static void Question1(){
        System.out.println("Question 1");
        float luongAccount1 =  5240.5f;
        float luongAccount2 =  10970.055f;
        int tronAccount1 = (int) 5240.5f;
        int tronAccount2 = (int) 10970.055f;
        System.out.println("So lam tron la cua account 1 la :" + tronAccount1);
        System.out.println("So lam tron la cua account 2 la :" + tronAccount2);
        System.out.println("\n");
    }
    public static void Question2() {
        System.out.println("Question 2");
        Random random = new Random();
        int so = random.nextInt(100000);
        System.out.printf("%05d",so);
        System.out.println("\n");
    }
    public static void Question3() {
        System.out.println("Question 3");
        Random random = new Random();
        int so = random.nextInt(100000);
        System.out.printf("So random la :" + "%05d%n",so);
        int soDu =  so % 100;
        System.out.printf("2 so cuoi la :" + "%02d", soDu);
        System.out.println("\n");
    }
    public static void Question4() {
        System.out.println("Question 4");
        Scanner sc = new Scanner(System.in);
        System.out.println("Moi nhap vao so a :");
        int a = sc.nextInt();
        System.out.println("Moi nhap vao so b :");
        int b = sc.nextInt();
        System.out.println("Thuong cua chung la :" + a/b);
        System.out.println("\n");
    }

}
