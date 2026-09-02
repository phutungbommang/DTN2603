import java.util.Arrays;

public class Exercise5 {
    public static void Question1(Department department) {
        System.out.println(department);
    }
    public static void Question2(Department[] departments) {
        for (Department department : departments) {
            System.out.println(department);
        }
    }
    public static void Question3(Department department) {
        System.out.println(department.departmentName);
    }
    public static void Question4(Department department) {
        if (department.departmentName.equals("Phong A")) {
            System.out.println("Dung");
        } else {
            System.out.println("Sai");
        }
    }
    public static void Question5(Department dep1, Department dep2) {
        if (dep1.departmentName.equals(dep2.departmentName)) {
            System.out.println("Bang nhau");
        } else {
            System.out.println("Khong bang nhau");
        }
    }
    public static void Question6(Department[] departments) {
        Arrays.sort(departments,
                (d1, d2) -> d1.departmentName.compareTo(d2.departmentName));

        for (Department department : departments) {
            System.out.println(department.departmentName);
        }
    }
}
