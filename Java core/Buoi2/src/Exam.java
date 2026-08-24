public class Exam {
    int examId;
    String code;
    String title;
    CategoryQuestion categoryQuestion;

    public Exam(int examId, String code, String title, CategoryQuestion categoryQuestion) {
        this.examId = examId;
        this.code = code;
        this.title = title;
        this.categoryQuestion = categoryQuestion;
    }
}