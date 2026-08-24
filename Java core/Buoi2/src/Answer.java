public class Answer {
    int answerId;
    String content;
    Question question;
    boolean isCorrect;

    public Answer(int answerId, String content, Question question, boolean isCorrect) {
        this.answerId = answerId;
        this.content = content;
        this.question = question;
        this.isCorrect = isCorrect;
    }
}