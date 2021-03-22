import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Question {

    private static final AtomicInteger count = new AtomicInteger(1);
    public String question;
    public final String answer;
    public String difficulty;
    public int id;
    public Topic topic;

    public Question(String question, String answer, String difficulty, Topic topic) {
        this.question = question;
        this.answer = answer;
        this.difficulty = difficulty;
        this.id = count.incrementAndGet();
        this.topic = topic;
    }
}

