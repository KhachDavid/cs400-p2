import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

public class BackEndDummy {

    private final List<Question> questionList;

    public BackEndDummy() {
        questionList = new ArrayList<>();
    }

    public void addQuestion(Question question) {
        questionList.add(question);
    }

    public void removeQuestion(Question question) {
        questionList.remove(question);
    }

    public String listAllQuestions() {
        String retValue = "";

        for (Question q : questionList) {
            retValue = retValue + q.id + "." + q.question + "\n";
        }
        return retValue;
    }

    public String getQuestionFromATopic(String topic) throws NoSuchElementException {
        Random random = new Random();
        List<Question> tmp = new ArrayList<>();
        for (Question q : questionList) {
            if (q.topic.equals(topic)) {
                tmp.add(q);
            }
        }

        if (tmp.size() == 0) {
            throw new NoSuchElementException();
        }

        int rnd = random.nextInt(tmp.size());
        return tmp.get(rnd).question;
    }

    public void rateQuestion(Question question, String rating) {
        question.difficulty = rating;
    }
}


