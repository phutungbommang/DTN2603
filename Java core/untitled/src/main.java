import java.util.Date;

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
        Position position1 = new Position(1, PositionName.pm);
        Position position2 = new Position(2, PositionName.test);
        Position position3 = new Position(3, PositionName.dev);
        Position position4 = new Position(4, PositionName.scrumMaster);
        // In
        System.out.println("\nPosition:");
        System.out.println("Position 1:" + position1.positionName);
        System.out.println("Position 2:" + position2.positionName);
        System.out.println("Position 3:" + position3.positionName);
        System.out.println("Position 4:" + position4.positionName);


        // Table 3: Account
        Account account1 = new Account(1, "Nguyen An",  "nguyenan",  "a@gmail.com", new Date(), department1, position1);
        Account account2 = new Account(2, "Tran Binh",  "tranbinh",  "b@gmail.com", new Date(), department2, position2);
        Account account3 = new Account(3, "Le Cuong",   "lecuong",   "c@gmail.com", new Date(), department3, position3);
        // In
        System.out.println("\nAccount");
        System.out.println("Account 1: " + account1.fullName + " | " + account1.email + " | Dept: " + account1.department.departmentName + " | Pos: " + account1.position.positionName);
        System.out.println("Account 2: " + account2.fullName + " | " + account2.email + " | Dept: " + account2.department.departmentName + " | Pos: " + account2.position.positionName);
        System.out.println("Account 3: " + account3.fullName + " | " + account3.email + " | Dept: " + account3.department.departmentName + " | Pos: " + account3.position.positionName);

        // Table 4: Group
        Group group1 = new Group(1, "Java Fresher", new Date(), account1.accountId);
        Group group2 = new Group(2, "SQL Group",    new Date(), account2.accountId);
        Group group3 = new Group(3, "Spring Boot",  new Date(), account3.accountId);
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
        Question question1 = new Question(1, "Java là gì?",              category1, typeQuestion1, account1, new Date());
        Question question2 = new Question(2, "SQL là gì?",               category2, typeQuestion2, account2, new Date());
        Question question3 = new Question(3, "Postman dùng để làm gì?",  category3, typeQuestion2, account3, new Date());
        // In
        System.out.println("\nQuestion");
        System.out.println("Question 1: " + question1.content + " | Category: " + question1.categoryQuestion.categoryName + " | Type: " + question1.typeQuestion.typeName);
        System.out.println("Question 2: " + question2.content + " | Category: " + question2.categoryQuestion.categoryName + " | Type: " + question2.typeQuestion.typeName);
        System.out.println("Question 3: " + question3.content + " | Category: " + question3.categoryQuestion.categoryName + " | Type: " + question3.typeQuestion.typeName);


        // Table 9: Answer
        Answer answer1 = new Answer(1, "Java là ngôn ngữ lập trình",              question1, true);
        Answer answer2 = new Answer(2, "SQL dùng để làm việc với cơ sở dữ liệu",  question2, true);
        Answer answer3 = new Answer(3, "Postman dùng để test API",                 question3, true);
        // In
        System.out.println("\nAnswer");
        System.out.println("Answer 1: " + answer1.content + " | isCorrect: " + answer1.isCorrect + " | Question: " + answer1.question.content);
        System.out.println("Answer 2: " + answer2.content + " | isCorrect: " + answer2.isCorrect + " | Question: " + answer2.question.content);
        System.out.println("Answer 3: " + answer3.content + " | isCorrect: " + answer3.isCorrect + " | Question: " + answer3.question.content);


        // Table 10: Exam
        Exam exam1 = new Exam(1, "EX001", "Java Basic",    category1);
        Exam exam2 = new Exam(2, "EX002", "SQL Basic",     category2);
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
    }
}


