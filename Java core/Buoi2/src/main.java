import javax.crypto.spec.PSource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        // Table 1: Department
        Department department1 = new Department(1, "Sale");
        Department department2 = new Department(2, "Marketing");
        Department department3 = new Department(3, "IT");
        // In
        System.out.println("Department:");
        System.out.println("Department 1:" + department1.departmentName);
        System.out.println("Department 2:" + department2.departmentName);
        System.out.println("Department 3:" + department3.departmentName);


        // Table 2: Position
        Position position1 = new Position(1, PositionName.PM);
        Position position2 = new Position(2, PositionName.TEST);
        Position position3 = new Position(3, PositionName.DEV);
        Position position4 = new Position(4, PositionName.SCRUMMASTER);
        // In
        System.out.println("\nPosition:");
        System.out.println("Position 1:" + position1.positionName);
        System.out.println("Position 2:" + position2.positionName);
        System.out.println("Position 3:" + position3.positionName);
        System.out.println("Position 4:" + position4.positionName);


        // Table 3: Account
        Account account1 = new Account(1, "Nguyen An", "nguyenan", "a@gmail.com", new Date(), department1, position1);
        Account account2 = new Account(2, "Tran Binh", "tranbinh", "b@gmail.com", new Date(), department2, position2);
        Account account3 = new Account(3, "Le Cuong", "lecuong", "c@gmail.com", new Date(), department3, position3);
        // In
        System.out.println("\nAccount");
        System.out.println("Account 1: " + account1.fullName + " | " + account1.email + " | Dept: " + account1.department.departmentName + " | Pos: " + account1.position.positionName);
        System.out.println("Account 2: " + account2.fullName + " | " + account2.email + " | Dept: " + account2.department.departmentName + " | Pos: " + account2.position.positionName);
        System.out.println("Account 3: " + account3.fullName + " | " + account3.email + " | Dept: " + account3.department.departmentName + " | Pos: " + account3.position.positionName);

        // Table 4: Group
        Group group1 = new Group(1, "Java Fresher", new Date(), account1.accountId);
        Group group2 = new Group(2, "SQL Group", new Date(), account2.accountId);
        Group group3 = new Group(3, "Spring Boot", new Date(), account3.accountId);
        // In
        System.out.println("\nGroup");
        System.out.println("Group 1: " + group1.groupName + " | CreatorID: " + group1.creatorId);
        System.out.println("Group 2: " + group2.groupName + " | CreatorID: " + group2.creatorId);
        System.out.println("Group 3: " + group3.groupName + " | CreatorID: " + group3.creatorId);


        // Table 5: GroupAccount
        GroupAccount groupAccount1 = new GroupAccount(group1, account1, new Date());
        GroupAccount groupAccount2 = new GroupAccount(group2, account2, new Date());
        GroupAccount groupAccount3 = new GroupAccount(group3, account3, new Date());
        // In
        System.out.println("\nGroupAccount");
        System.out.println("GroupAccount 1: Group=" + groupAccount1.group.groupName + " | Account=" + groupAccount1.account.fullName + " | JoinDate=" + groupAccount1.joinDate);
        System.out.println("GroupAccount 2: Group=" + groupAccount2.group.groupName + " | Account=" + groupAccount2.account.fullName + " | JoinDate=" + groupAccount2.joinDate);
        System.out.println("GroupAccount 3: Group=" + groupAccount3.group.groupName + " | Account=" + groupAccount3.account.fullName + " | JoinDate=" + groupAccount3.joinDate);


        // Table 6: TypeQuestion
        TypeQuestion typeQuestion1 = new TypeQuestion(1, TypeName.Essay);
        TypeQuestion typeQuestion2 = new TypeQuestion(2, TypeName.multipleChoice);
        TypeQuestion typeQuestion3 = new TypeQuestion(3, TypeName.Essay);
        // In
        System.out.println("\nTypeQuestion");
        System.out.println("TypeQuestion 1: " + typeQuestion1.typeName);
        System.out.println("TypeQuestion 2: " + typeQuestion2.typeName);
        System.out.println("TypeQuestion 3: " + typeQuestion3.typeName);


        // Table 7: CategoryQuestion
        CategoryQuestion category1 = new CategoryQuestion(1, "Java");
        CategoryQuestion category2 = new CategoryQuestion(2, "SQL");
        CategoryQuestion category3 = new CategoryQuestion(3, "Postman");
        // In
        System.out.println("\nCategoryQuestion");
        System.out.println("Category 1: " + category1.categoryName);
        System.out.println("Category 2: " + category2.categoryName);
        System.out.println("Category 3: " + category3.categoryName);


        // Table 8: Question
        Question question1 = new Question(1, "Java là gì?", category1, typeQuestion1, account1, new Date());
        Question question2 = new Question(2, "SQL là gì?", category2, typeQuestion2, account2, new Date());
        Question question3 = new Question(3, "Postman dùng để làm gì?", category3, typeQuestion2, account3, new Date());
        // In
        System.out.println("\nQuestion");
        System.out.println("Question 1: " + question1.content + " | Category: " + question1.categoryQuestion.categoryName + " | Type: " + question1.typeQuestion.typeName);
        System.out.println("Question 2: " + question2.content + " | Category: " + question2.categoryQuestion.categoryName + " | Type: " + question2.typeQuestion.typeName);
        System.out.println("Question 3: " + question3.content + " | Category: " + question3.categoryQuestion.categoryName + " | Type: " + question3.typeQuestion.typeName);


        // Table 9: Answer
        Answer answer1 = new Answer(1, "Java là ngôn ngữ lập trình", question1, true);
        Answer answer2 = new Answer(2, "SQL dùng để làm việc với cơ sở dữ liệu", question2, true);
        Answer answer3 = new Answer(3, "Postman dùng để test API", question3, true);
        // In
        System.out.println("\nAnswer");
        System.out.println("Answer 1: " + answer1.content + " | isCorrect: " + answer1.isCorrect + " | Question: " + answer1.question.content);
        System.out.println("Answer 2: " + answer2.content + " | isCorrect: " + answer2.isCorrect + " | Question: " + answer2.question.content);
        System.out.println("Answer 3: " + answer3.content + " | isCorrect: " + answer3.isCorrect + " | Question: " + answer3.question.content);


        // Table 10: Exam
        Exam exam1 = new Exam(1, "EX001", "Java Basic", category1);
        Exam exam2 = new Exam(2, "EX002", "SQL Basic", category2);
        Exam exam3 = new Exam(3, "EX003", "Postman Basic", category3);
        // In
        System.out.println("\nExam");
        System.out.println("Exam 1: " + exam1.title + " | Code: " + exam1.code + " | Category: " + exam1.categoryQuestion.categoryName);
        System.out.println("Exam 2: " + exam2.title + " | Code: " + exam2.code + " | Category: " + exam2.categoryQuestion.categoryName);
        System.out.println("Exam 3: " + exam3.title + " | Code: " + exam3.code + " | Category: " + exam3.categoryQuestion.categoryName);


        // =========================
        // Table 11: ExamQuestion
        // =========================
        ExamQuestion examQuestion1 = new ExamQuestion(exam1, question1);
        ExamQuestion examQuestion2 = new ExamQuestion(exam2, question2);
        ExamQuestion examQuestion3 = new ExamQuestion(exam3, question3);

        System.out.println("\nExamQuestion:");
        System.out.println(examQuestion1.exam.code + '/' + examQuestion1.question.content);
        System.out.println(examQuestion2.exam.code + '/' + examQuestion2.question.content);
        System.out.println(examQuestion3.exam.code + '/' + examQuestion3.question.content);

        // IF
        System.out.println("----IF----");
        // Question 1:
        // Kiểm tra account thứ 2
        // Nếu không có phòng ban (tức là department == null) thì sẽ in ra text "Nhân viên này chưa có phòng ban"
        // Nếu không thì sẽ in ra text "Phòng ban của nhân viên này là …"
        System.out.println("Question 1");
        if (account2.department == null) {
            System.out.println("Nhan vien chua co phong ban");
        } else {
            System.out.println("Phong ban cua nhan vien nay la" + account2.department.departmentName);
        }
        System.out.println("\n");
        // Question 2:
        // Kiểm tra account thứ 2
        // Nếu không có group thì sẽ in ra text "Nhân viên này chưa có group"
        // Nếu có mặt trong 1 hoặc 2 group thì sẽ in ra text "Group của nhân viên này là Java Fresher, C# Fresher"
        // Nếu có mặt trong 3 Group thì sẽ in ra text "Nhân viên này là người quan trọng, tham gia nhiều group"
        // Nếu có mặt trong 4 group trở lên thì sẽ in ra text "Nhân viên này là người hóng chuyện, tham gia tất cả các group"
        System.out.println("Question 2");
        int count2 = 0;
        String tenGroup2 = "";
        GroupAccount[] groupAccount = {groupAccount1, groupAccount2, groupAccount3};
        for (GroupAccount account : groupAccount) {
            if (account.account.accountId == account2.accountId) {
                count2++;
                if (tenGroup2.isEmpty()) {
                    tenGroup2 += account.group.groupName;
                } else {
                    tenGroup2 += ", " + account.group.groupName;
                }
            }
        }
        if (count2 == 0) {
            System.out.println("Nhan vien nay chua co group");
        } else if (count2 <= 2) {
            System.out.println("Group cua nhan vien nay la " + group2.groupName);
        } else if (count2 == 3) {
            System.out.println("Nhan vien nay la nguoi quan trong, tham gia tat ca group");
        } else {
            System.out.println("Nhan vien nay la nguoi hong chuyen, tham gia tat ca group");
        }
        System.out.println("\n");
        // Question 3: Sử dụng toán tử ternary để làm Question 1
        System.out.println("Question 3");
        String kQua3 = (account2.department == null) ? "Nhan vien nay chua co phong ban" : "Phong ban cua nhan vien nay la" + account2.department.departmentName;
        System.out.println(kQua3);
        System.out.println("\n");
        // Question 4:
        // Sử dụng toán tử ternary để làm yêu cầu sau:
        // Kiểm tra Position của account thứ 1
        // Nếu Position = Dev thì in ra text "Đây là Developer"
        // Nếu không phải thì in ra text "Người này không phải là Developer"
        System.out.println("Question 4");
        String kQua4 = (account1.position.positionName == PositionName.DEV) ? "Đay la Developer" : "Nguoi nay ko phai Developer";
        System.out.println(kQua4);
        System.out.println("\n");
        System.out.println("\n");
        // SWITCH CASE
        System.out.println("----SWITCH CASE----");
        // Question 5:
        // Lấy ra số lượng account trong nhóm thứ 1 và in ra theo format sau:
        // Nếu số lượng account = 1 thì in ra "Nhóm có một thành viên"
        // Nếu số lượng account = 2 thì in ra "Nhóm có hai thành viên"
        // Nếu số lượng account = 3 thì in ra "Nhóm có ba thành viên"
        // Còn lại in ra "Nhóm có nhiều thành viên"
        System.out.println("Question 5");
        int soLuong = 0;
        for (GroupAccount account : groupAccount) {
            soLuong++;
        }
        switch (soLuong) {
            case 1:
                System.out.println("Nhom co mot thanh vien");
                break;
            case 2:
                System.out.println("Nhom co hai thanh vien");
                break;
            case 3:
                System.out.println("Nhom co ba thanh vien");
                break;
            default:
                System.out.println("Nhom co nhiue thanh vien");
                break;
        }
        System.out.println("\n");
        // Question 6:
        // Sử dụng switch case để làm lại Question 2
        System.out.println("Question 6");
        int count6 = 0;
        String tenGroup6 = "";
        for (GroupAccount account : groupAccount) {
            if (account.account.accountId == account2.accountId) {
                count6++;
                if (tenGroup6.isEmpty()) {
                    tenGroup6 += account.group.groupName;
                } else {
                    tenGroup6 += ", " + account.group.groupName;
                }
                switch (count6) {
                    case 0:
                        System.out.println("Nhan vien nay chua co group");
                        break;
                    case 1:
                    case 2:
                        System.out.println("Group cua nhan vien nay la " + tenGroup6);
                        break;
                    case 3:
                        System.out.println("Nhan vien nay la nguoi quan trong, tham gia tat ca group");
                        break;
                    default:
                        System.out.println("Nhan vien nay la nguoi hong chuyen, tham gia tat ca cac group");
                        break;
                }
            }
        }
        System.out.println("\n");
        // Question 7:
        // Sử dụng switch case để làm lại Question 4
        System.out.println("Question 7");
        switch (account1.position.positionName) {
            case DEV:
                System.out.println("Đay la Developer");
                break;
            default:
                System.out.println("Nguoi nay khong phai Developer");
                break;
        }
        System.out.println("\n");
        System.out.println("\n");
        // FOREACH
        System.out.println("----FOREACH----");
        // Question 8: In ra thông tin các account bao gồm: Email, FullName và tên phòng ban của họ
        System.out.println("Question 8");
        Account[] account = {account1, account2, account3};
        Department[] departments = {department1, department2, department3};
        for (Account account8 : account) {
            System.out.println("Email la:" + account8.email + " | FullName la:" + account8.fullName + " | Phong ban la:" + account8.department.departmentName);
        }
        System.out.println("\n");
        // Question 9: In ra thông tin các phòng ban bao gồm: id và name
        System.out.println("Question 9");
        for (Department department : departments) {
            System.out.println("ID: " + department.departmentId + " | Name: " + department.departmentName);
        }
        System.out.println("\n");
        System.out.println("\n");
        // FOR
        System.out.println("----FOR----");
        // Question 10:
        // In ra thông tin các account bao gồm: Email, FullName và tên phòng ban của
        // họ theo định dạng như sau:
        // Thông tin account thứ 1 là:
        // Email: NguyenVanA@gmail.com
        // Full name: Nguyễn Văn A
        //  Phòng ban: Sale
        //  Thông tin account thứ 2 là:
        //  Email: NguyenVanB@gmail.com
        //  Full name: Nguyễn Văn B
        //  Phòng ban: Marketting
        System.out.println("Question 10");
        for (int i = 0; i < account.length; i++) {
            System.out.println("Thong tin account thu " + (i + 1) + " la:");
            System.out.println("Email: " + account[i].email);
            System.out.println("Full name: " + account[i].fullName);
            System.out.println("Phong ban: " + account[i].department.departmentName);
        }
        System.out.println("\n");
        //  Question 11:
        //  In ra thông tin các phòng ban bao gồm: id và name theo định dạng sau:
        //  Thông tin department thứ 1 là:
        //  Id: 1
        //  Name: Sale
        //  Thông tin department thứ 2 là:
        //  Id: 2
        //  Name: Marketing
        System.out.println("Question 11");
        for (int i = 0; i < departments.length; i++) {
            System.out.println("Thong tin department thu " + (i + 1) + " la:");
            System.out.println("Id: " + departments[i].departmentId);
            System.out.println("Name: " + departments[i].departmentName);
        }
        System.out.println("\n");
        //  Question 12:
        //  Chỉ in ra thông tin 2 department đầu tiên theo định dạng như Question 10
        System.out.println("Question 12");
        for (int i = 0; i < departments.length; i++) {
            if (i == 2) {
                break;
            }
            System.out.println("Thong tin department thu " + (i + 1) + " la:");
            System.out.println("Id: " + departments[i].departmentId);
            System.out.println("Name: " + departments[i].departmentName);
        }
        System.out.println("\n");
        //  Question 13:
        //  In ra thông tin tất cả các account ngoại trừ account thứ 2
        System.out.println("Question 13");
        for (int i = 0; i < account.length; i++) {
            if (i == 1) {
                continue;
            }
            System.out.println("Email: " + account[i].email + " | FullName: " + account[i].fullName);
        }
        System.out.println("\n");
        //  Question 14:
        //  In ra thông tin tất cả các account có id < 4
        System.out.println("Question 14");
        for (int i = 0; i < account.length; i++) {
            if (account[i].accountId < 4) {
                System.out.println("Email: " + account[i].email + " | FullName: " + account[i].fullName);
            }
        }
        System.out.println("\n");
        //  Question 15:
        //  In ra các số chẵn nhỏ hơn hoặc bằng 20
        System.out.println("Question 15");
        System.out.print("Cac so chan nho hon hoac bang 20 la:");
        for (int i = 2; i <= 20; i += 2) {
            System.out.print(i + " ");
        }
        System.out.println("\n");
        System.out.println("\n");
        // WHILE
        System.out.println("----WHILE----");
        // Question 16:
        // Làm lại các Question ở phần FOR bằng cách sử dụng WHILE kết hợp với lệnh break, continue
        System.out.println("Question 16 - cau10");
        int i = 0;
        while (i < account.length) {
            System.out.println("Thong tin account thu " + (i + 1) + " la:");
            System.out.println("Email: " + account[i].email);
            System.out.println("Full name: " + account[i].fullName);
            System.out.println("Phong ban: " + account[i].department.departmentName);
            i++;
        }
        System.out.println("\n");
        System.out.println("Question 16 - cau 12");
        i = 0;
        while (i < departments.length) {
            if (i == 2) {
                break;
            }
            System.out.println("Thong tin department thu " + (i + 1) + " la:");
            System.out.println("Id: " + departments[i].departmentId);
            System.out.println("Name: " + departments[i].departmentName);
            i++;
        }
        System.out.println("\n");
        System.out.println("Question 16 - cau 13");
        i = 0;
        while (i < account.length) {
            if (i == 1) {
                i++;
                continue;
            }
            System.out.println("Email: " + account[i].email + " | FullName: " + account[i].fullName);
            i++;
        }
        System.out.println("Question 16 - cau 15");
        i = 2;
        System.out.print("Cac so chan nho hon hoac bang 20 la:");
        while (i <= 20) {
            System.out.print(i + " ");
            i += 2;
        }
        System.out.println("\n");
        System.out.println("\n");
        // DO-WHILE
        System.out.println("----DO-WHILE----");
        // Question 17:
        // Làm lại các Question ở phần FOR bằng cách sử dụng DO-WHILE kết hợp với lệnh break, continue
        System.out.println("Question 17 - cau 10");
        i = 0;
        do {
            System.out.println("Thong tin account thu " + (i + 1) + " la:");
            System.out.println("Email: " + account[i].email);
            System.out.println("Full name: " + account[i].fullName);
            System.out.println("Phong ban: " + account[i].department.departmentName);
            i++;
        } while (i < account.length);
        System.out.println("\n");

        System.out.println("Question 17 - cau 12");
        i = 0;
        do {
            if (i == 2) break;
            System.out.println("Thong tin deaprtment thu " + (i + 1) + " la:");
            System.out.println("Id: " + departments[i].departmentId);
            System.out.println("Name: " + departments[i].departmentName);
            i++;
        } while (i < departments.length);
        System.out.println("\n");

        System.out.println("Question 17 - cau 13");
        i = 0;
        do {
            if (i == 1) {
                i++;
                continue;
            }
            System.out.println("Email: " + account[i].email + " | FullName: " + account[i].fullName);
            i++;
        } while (i < account.length);

        System.out.println("Question 17 - cau 15");
        i = 2;
        System.out.print("Cac so chan nho hon hoc bang 20 la:");
        do {
            System.out.print(i + " ");
            i += 2;
        } while (i <= 20);
        System.out.println("\n");
        System.out.println("\n");
        System.out.println("\n");
        System.out.println("\n");
        // Exercise 2: System out printf
        System.out.println("----EXERCISE 2----");
        // Question 1:
        // Khai báo 1 số nguyên = 5 và sử dụng lệnh System out printf để in ra số nguyên đó
        System.out.println("Question 1");
        int soNguyen = 5;
        System.out.printf("%d%n", soNguyen);
        System.out.println("\n");
        // Question 2:
        // Khai báo 1 số nguyên = 100 000 000 và sử dụng lệnh System out printf để in
        // ra số nguyên đó thành định dạng như sau: 100,000,000
        System.out.println("Question 2");
        int soLon = 100_000_000;
        System.out.printf("%,d%n", soLon);
        System.out.println("\n");
        // Question 3:
        // Khai báo 1 số thực = 5,567098 và sử dụng lệnh System out printf để in ra số
        // thực đó chỉ bao gồm 4 số đằng sau
        double soThuc = 5.567098;
        System.out.printf("%.4f%n", soThuc);
        System.out.println("\n");
        // Question 4:
        // Khai báo Họ và tên của 1 học sinh và in ra họ và tên học sinh đó theo định dạng như sau:
        // Họ và tên: "Nguyễn Văn A" thì sẽ in ra trên console như sau:
        // Tên tôi là "Nguyễn Văn A" và tôi đang độc thân.
        System.out.println("Question 4");
        String hoTen = "Nguyen Van A";
        System.out.printf("Ten toi la \"%s\" va toi dang doc than.", hoTen);
        System.out.println("\n");
        // Question 5:
        // Lấy thời gian bây giờ và in ra theo định dạng sau: 24/04/2020 11h:16p:20s
        System.out.println("Question 5");
        Date now = new Date();
        System.out.printf("%td/%tm/%tY %tHh:%tMp:%tSs%n", now, now, now, now, now, now);
        // Question 6:
        // In ra thông tin account (như Question 8 phần FOREACH) theo định dạng table (giống trong Database)
        System.out.println("Question 6");
        System.out.println("Danh sach account la:");
        System.out.println("+-----+--------------------+--------------------+---------------");
        System.out.printf("%-5s %-20s %-20s %-15s%n", "ID", "Full Name", "Email", "Department");
        System.out.println("+-----+--------------------+--------------------+---------------");
        for (Account account4 : account) {
            System.out.printf("%-5d %-20s %-20s %-15s%n",
                    account4.accountId, account4.fullName, account4.email, account4.department.departmentName);
        }
        System.out.println("\n");
        System.out.println("\n");
        System.out.println("\n");
        // Exercise 3: Date Format
        System.out.println("----Exercise 3----");
        Date examDate = new Date();
        // Question 1:
        // In ra thông tin Exam thứ 1 và property create date sẽ được format theo định dạng vietnamese
        System.out.println("Question 1");
        SimpleDateFormat sdfVN = new SimpleDateFormat("EEEE, dd 'thang' MM 'nam' yyyy", new java.util.Locale("vi", "VN"));
        System.out.println(sdfVN.format(examDate));
        System.out.println("\n");
        // Question 2:
        // In ra thông tin: Exam đã tạo ngày nào theo định dạng
        // Năm – tháng – ngày – giờ – phút – giây
        System.out.println("Question 2");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        System.out.println(sdf2.format(examDate));
        System.out.println("\n");
        // Question 3:
        // Chỉ in ra năm của create date property trong Question 2
        System.out.println("Question 3");
        System.out.println(new SimpleDateFormat("yyyy").format(examDate));
        System.out.println("\n");
        // Question 4:
        // Chỉ in ra tháng và năm của create date property trong Question 2
        System.out.println("Question 4");
        System.out.println(new SimpleDateFormat("MM/yyyy").format(examDate));
        System.out.println("\n");
        // Question 5:
        // Chỉ in ra "MM-DD" của create date trong Question 2
        System.out.println("Question 5");
        System.out.println(new SimpleDateFormat("MM-dd").format(examDate));
        System.out.println("\n");
        System.out.println("\n");
        System.out.println("\n");
        // Exercise 4: Random Number
        System.out.println("----Exercise 4----");
        Random random = new Random();
        // Question 1:
        // In ngẫu nhiên ra 1 số nguyên
        System.out.println("Question 1");
        System.out.println(random.nextInt());
        System.out.println("\n");
        // Question 2:
        // In ngẫu nhiên ra 1 số thực
        System.out.println("Question 2");
        System.out.println(random.nextDouble());
        System.out.println("\n");
        // Question 3:
        // Khai báo 1 array bao gồm các tên của các bạn trong lớp, sau đó in ngẫu nhiên ra tên của 1 bạn
        System.out.println("Question 3");
        String[] tenLop = {"An", "Binh", "Cuong", "Dung", "Em", "Phong"};
        System.out.println(tenLop[random.nextInt(tenLop.length)]);
        System.out.println("\n");
        // Question 4:
        // Lấy ngẫu nhiên 1 ngày trong khoảng thời gian 24-07-1995 tới ngày 20-12- 1995
        System.out.println("question 4");
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Date start = sdf.parse("24-07-1995");
            Date end = sdf.parse("20-12-1995");
            long randomTime = start.getTime()
                    + (long) (Math.random() * (end.getTime() - start.getTime()));
            Date randomDate = new Date(randomTime);
            System.out.println(sdf.format(randomDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("\n");
        // Question 5:
        // Lấy ngẫu nhiên 1 ngày trong khoảng thời gian 1 năm trở lại đây
        System.out.println("Question 5");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date oneYearAgo = new Date(
                now.getTime() - 365L * 24 * 60 * 60 * 1000
        );
        long randomTime = oneYearAgo.getTime()
                + (long) (Math.random() * (now.getTime() - oneYearAgo.getTime()));
        Date randomDate = new Date(randomTime);
        System.out.println(sdf.format(randomDate));
        System.out.println("\n");
        // Question 6:
        // Lấy ngẫu nhiên 1 ngày trong quá khứ.
        System.out.println("Question 6");
        Date tenYearsAgo = new Date(
                now.getTime() - 10L * 365 * 24 * 60 * 60 * 1000
        );
        long randomTime1 = tenYearsAgo.getTime()
                + (long) (Math.random() * (now.getTime() - tenYearsAgo.getTime()));
        Date randomDate1 = new Date(randomTime1);
        System.out.println(sdf.format(randomDate1));
        System.out.println("\n");
        // Question 7:
        // Lấy ngẫu nhiên 1 số có 3 chữ số.
        System.out.println("Question 7");
        System.out.println(100 + random.nextInt(900));
        System.out.println("\n");
        System.out.println("\n");
        System.out.println("\n");
        // Exercise5: Input from console
        System.out.println("---- Exercise5----");
        // Question 1:
        // Viết lệnh cho phép người dùng nhập 3 số nguyên vào chương trình.
        System.out.println("Question 1");
        Scanner sc = new Scanner(System.in);
        System.out.println("Moi nhap vao 3 so nguyen");
        System.out.println("Moi nhap vao so thu 1:");
        int a = sc.nextInt();
        System.out.println("Moi nhap vao so thu 2:");
        int b = sc.nextInt();
        System.out.println("Moi nhap vao so thu 3:");
        int c = sc.nextInt();
        System.out.println("Da nhap:" + a + " " + b + " " + c);
        System.out.println("\n");
        // Question 2:
        // Viết lệnh cho phép người dùng nhập 2 số thực vào chương trình.
        System.out.println("Question 2");
        System.out.println("Moi nhap vao 2 so thuc:");
        System.out.print("Số 1: ");
        double d1 = sc.nextDouble();
        System.out.print("Số 2: ");
        double d2 = sc.nextDouble();
        System.out.println("Da nhap:" + d1 + " " + d2);
        System.out.println("\n");
        // Question 3:
        // Viết lệnh cho phép người dùng nhập họ và tên.
        System.out.println("Question 3");
        sc.nextLine();
        System.out.println("Moi nhap ho và ten: ");
        String tenNhap = sc.nextLine();
        System.out.println("Ho và ten: " + tenNhap);
        System.out.println("\n");
        // Question 4:
        // Viết lệnh cho phép người dùng nhập vào ngày sinh nhật của họ.
        System.out.println("Question 4");
        System.out.print("Moi nhap vao ngay sinh (dd-MM-yyyy): ");
        String ngaySinh = sc.nextLine();
        try {
            Date dob = new SimpleDateFormat("dd-MM-yyyy").parse(ngaySinh);
            System.out.println("Ngay sinh: " + new SimpleDateFormat("dd/MM/yyyy").format(dob));
        } catch (ParseException e) {
            System.out.println("Đinh dang ko hop le.");
        }
        System.out.println("\n");
        // Question 5:
        // Viết lệnh cho phép người dùng tạo account (viết thành method)
        // Đối với property Position, Người dùng nhập vào 1 2 3 4 5 và vào
        // chương trình sẽ chuyển thành Position.Dev, Position.Test, Position.ScrumMaster, Position.PM.
        System.out.println("Question 5");
        System.out.println("Tao account:");
        System.out.print("Full name: ");
        String fullName = sc.nextLine();
        System.out.print("Username: ");
        String userName = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.println("Chon Position:");
        System.out.println("1.Dev");
        System.out.println("2.Test");
        System.out.println("3.ScrumMaster");
        System.out.println("4.PM");
        System.out.print("Nhập số: ");
        int posChoice = sc.nextInt();
        sc.nextLine();
        PositionName positionName;
        switch (posChoice) {
            case 1:
                positionName = PositionName.DEV;
                break;
            case 2:
                positionName = PositionName.TEST;
                break;
            case 3:
                positionName = PositionName.SCRUMMASTER;
                break;
            default:
                positionName = PositionName.PM;
                break;
        }
        Account newAccount = new Account(99, fullName, userName, email, new Date(), department1, new Position(99, positionName));
        System.out.println("Tao account thanh cong: " + newAccount.fullName + " | " + newAccount.position.positionName);
        System.out.println("\n");
        // Question 6:
        // Viết lệnh cho phép người dùng tạo department (viết thành method)
        System.out.println("Question 6");
        System.out.println("Tao department:");
        System.out.print("Department name: ");
        String deptName = sc.nextLine();
        Department newDept = new Department(99, deptName);
        System.out.println("Tao department thanh công: " + newDept.departmentName);
        System.out.println("\n");
        // Question 7:
        // Nhập số chẵn từ console
        System.out.println("Question 7");
        System.out.println("nhap vao so c");
        int soChan = sc.nextInt();
        while (true) {
            if (soChan % 2 == 0) {
                System.out.println("Hop le: " + soChan);
                break;
            }
            System.out.print("So le, nhap lai: ");
        }
        sc.nextLine();
        System.out.println("\n");
        // Question 8:
        // Viết chương trình thực hiện theo flow sau:
        // Bước 1:
        // Chương trình in ra text "mời bạn nhập vào chức năng muốn sử dụng"
        // Bước 2:
        // Nếu người dùng nhập vào 1 thì sẽ thực hiện tạo account
        // Nếu người dùng nhập vào 2 thì sẽ thực hiện chức năng tạodepartment
        // Nếu người dùng nhập vào số khác thì in ra text "Mời bạn nhập
        // lại" và quay trở lại bước 1
        // Question 9:
        // Viết method cho phép người dùng thêm group vào account theo flow sau:
        // Bước 1: In ra tên các usernames của user cho người dùng xem
        // Bước 2: Yêu cầu người dùng nhập vào username của account
        // Bước 3: In ra tên các group cho người dùng xem
        // Bước 4: Yêu cầu người dùng nhập vào tên của group
        // Bước 5: Dựa vào username và tên của group người dùng vừa chọn, hãy thêm account vào group đó .
        // Question 10: Tiếp tục Question 8 và Question 9// Bổ sung thêm vào bước 2 của Question 8 như sau:
        // Nếu người dùng nhập vào 3 thì sẽ thực hiện chức năng thêm group vào account
        // Bổ sung thêm Bước 3 của Question 8 như sau:
        // Sau khi người dùng thực hiện xong chức năng ở bước 2 thì in ra dòng text để hỏi   người dùng "Bạn có muốn thực hiện chức năng khác không?". Nếu người dùng chọn "Có" thì quay lại bước 1, nếu người dùng chọn "Không" thì kết thúc chương trình (sử dụng lệnh return để kết thúc chương trình).
        // Question 11: Tiếp tục Question 10
        // Bổ sung thêm vào bước 2 của Question 8 như sau:
        // Nếu người dùng nhập vào 4 thì sẽ thực hiện chức năng thêm account vào 1 nhóm ngẫu nhiên, chức năng sẽ được cài đặt như sau:
        // Bước 1: In ra tên các usernames của user cho người dùng xem
        // Bước 2: Yêu cầu người dùng nhập vào username của account
        // Bước 3: Sau đó chương trình sẽ chọn ngẫu nhiên 1 group
        // Bước 4: Thêm account vào group chương trình vừa chọn ngẫu nhiên
        Group[] group = {group1, group2, group3};
        System.out.println("Question 8");
        System.out.println("Moi ban nhap vao chuc nang muon su dung");
        System.out.println("1. Tao account");
        System.out.println("2. Tao department");
        System.out.println("3. Them group vao account");
        System.out.println("4. Them account vao group ngau nhien");
        while (true) {
            int nhap = sc.nextInt();
            sc.nextLine();
            if (nhap == 1) {
                System.out.print("Full name: ");
                String full = sc.nextLine();
                System.out.print("Username: ");
                String user = sc.nextLine();
                System.out.print("Email: ");
                String em = sc.nextLine();
                Account account4 = new Account(100, full, user, em, new Date(), department1, position1);
                System.out.println("Tao account thanh cong: " + account4.fullName + " | " + account4.position.positionName);

            } else if (nhap == 2) {
                System.out.print("Department name: ");
                String depName = sc.nextLine();
                Department newd = new Department(100, depName);
                System.out.println("Tao department thanh cong: " + newd.departmentName);

            } else if (nhap == 3) {
                addAccountToGroup(sc, account, group, groupAccount);
            } else if (nhap == 4) {
                System.out.println("Danh sach username:");
                for (Account acc : account) System.out.println("- " + acc.userName);
                System.out.print("Nhap username: ");
                String uname = sc.nextLine();
                Account foundAcc = null;
                for (Account acc : account) {
                    if (acc.userName.equals(uname)) {
                        foundAcc = acc;
                        break;
                    }
                }
                if (foundAcc != null) {
                    Group randomGroup = group[random.nextInt(group.length)];
                    GroupAccount newGA = new GroupAccount(randomGroup, foundAcc, new Date());
                    System.out.println("Group ngau nhien: " + randomGroup.groupName);
                    System.out.println("Da them " + newGA.account.fullName + " vao group " + newGA.group.groupName);
                } else {
                    System.out.println("Khong tim thay account.");
                }
            } else {
                System.out.println("Moi ban nhap lai");
                continue;
            }
            System.out.println("Ban co muon thuc hien chuc nang khac khong?");
            System.out.println("1. Co");
            System.out.println("2. Khong");
            int ans = sc.nextInt();
            sc.nextLine();
            if (ans == 2) {
                break;
            }
        }
        soChan();
        soNguyen();
        inAccount(account);
    }

    public static void addAccountToGroup(Scanner scanner, Account[] account, Group[] group, GroupAccount[] groupAccount) {
        System.out.println("Danh sach username:");
        for (Account accounts : account) {
            System.out.println(accounts.userName);
        }
        Account selectedAccount = null;
        while (selectedAccount == null) {
            System.out.print("Nhap username: ");
            String username = scanner.nextLine();

            for (Account acc : account) {
                if (acc.userName.equals(username)) {
                    selectedAccount = acc;
                    break;
                }
            }
            if (selectedAccount == null) {
                System.out.println("Khong tim thay account, vui long nhap lai!");
            }
        }
        System.out.println("Danh sach group:");
        for (Group groups : group) {
            System.out.println(groups.groupName);
        }
        Group selectedGroup = null;
        while (selectedGroup == null) {
            System.out.print("Nhap ten group: ");
            String groupName = scanner.nextLine();

            for (Group g : group) {
                if (g.groupName.equals(groupName)) {
                    selectedGroup = g;
                    break;
                }
            }
            if (selectedGroup == null) {
                System.out.println("Khong tim thay group, vui long nhap lai!");
            }
        }
        for (int i = 0; i < groupAccount.length; i++) {
            if (groupAccount[i] == null) {
                groupAccount[i] = new GroupAccount(selectedGroup, selectedAccount, new Date());
                System.out.println("Them thanh cong!");
                break;
            }
        }
    }

    //  Exercise 6: Method
    //  Question 1:
    //  Tạo method để in ra các số chẵn nguyên dương nhỏ hơn 10
    public static void soChan() {
        for (int i = 0; i < 10; i += 2) {
            System.out.println(i);
        }
    }

    //  Question 2:
    //  Tạo method để in thông tin các account
    public static void inAccount(Account[] accounts) {
        for (Account account : accounts) {
            System.out.println("ID: " + account.accountId);
            System.out.println("Email: " + account.email);
            System.out.println("Username: " + account.userName);
            System.out.println("Full Name: " + account.fullName);
        }
    }
    //  Question 3:
    //  Tạo method để in ra các số nguyên dương nhỏ hơn 10
    public static void soNguyen() {
        for (int i =1; i<10  ; i++) {
            System.out.println(i);
        }
    }

}


