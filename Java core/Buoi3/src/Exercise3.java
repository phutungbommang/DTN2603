public class Exercise3 {
    public static void Question1(){
        System.out.println("Question 3.1");
        Integer luong = 5000;
        float luongFloat = luong;
        System.out.printf("%.2f%n", luongFloat);
        System.out.println("\n");
    }
    public static void Question2(){
        String so = "1234567";
        int soDoi = Integer.parseInt(so);
        System.out.println("convert String đó ra số int :" + soDoi);
        System.out.println("\n");
    }
    public static void Question3(){
        Integer soInteger = Integer.valueOf("1234567");
        int soInt = soInteger;
        System.out.println("convert số trên thành datatype int :" + soInt);
        System.out.println("\n");
    }
}
