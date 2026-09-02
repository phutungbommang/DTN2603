import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Exercise1.Question1();
        Exercise1.Question2();
        Exercise1.Question3();
        Exercise1.Question4();

        Exercise2.Question1();

        Exercise3.Question1();
        Exercise3.Question2();
        Exercise3.Question3();


        Exercise4.Question1();
        Exercise4.Question2();
        Exercise4.Question3();
        Exercise4.Question4();
        Exercise4.Question5();
        Exercise4.Question6();
        Exercise4.Question7();
        Group group1 = new Group(1, "Java Fresher");
        Group group2 = new Group(2, "C# Fresher");
        Group group3 = new Group(3, "Java Web");
        Group group4 = new Group(4, "Testing");
        Group group5 = new Group(5, "Java");
        Group[] groups = {group1, group2, group3, group4, group5};
        Exercise4.Question8(groups);
        Exercise4.Question9(groups);
        Exercise4.Question10();
        Exercise4.Question11();
        Exercise4.Question12();
        Exercise4.Question13();
        Exercise4.Question14();
        Exercise4.Question15();
        Exercise4.Question16();

        Department dep1 = new Department(1, "Sale");
        Department dep2 = new Department(2, "Marketing");
        Department dep3 = new Department(3, "Accounting");
        Department dep4 = new Department(4, "Waiting room");
        Department dep5 = new Department(5, "Boss of director");
        Department[] departments = {dep1, dep2, dep3, dep4, dep5};
        Exercise5.Question1(dep1);
        Exercise5.Question2(departments);
        Exercise5.Question3(dep1);
        Exercise5.Question4(dep1);
        Exercise5.Question5(dep1, dep2);
        Exercise5.Question6(departments);
    }
}
