import java.util.Date;

public class Question {
    int questionId;
    String content;
    CategoryQuestion categoryQuestion;
    TypeQuestion typeQuestion;
    Account createId;
    Date createDate;

    public Question(int questionId, String content, CategoryQuestion categoryQuestion, TypeQuestion typeQuestion, Account createId, Date createDate) {
        this.questionId = questionId;
        this.content = content;
        this.categoryQuestion = categoryQuestion;
        this.typeQuestion = typeQuestion;
        this.createId = createId;
        this.createDate = createDate;
    }
}
