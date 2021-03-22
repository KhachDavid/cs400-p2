import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;


public class BackEndDummy {

    public List<Question> questionList;
    public Set<Topic> topicList;

    public BackEndDummy() {
        questionList = new ArrayList<>();
        topicList = new HashSet<>();
    }

    public void addQuestion(Question question) {
        questionList.add(question);
    }

    public void removeQuestion(Question question) {
        questionList.remove(question);
    }

    public String listAllQuestions(Topic topic) {
        String retValue = "";

        for (Question q : questionList) {
            if (topic.equals(q.topic)) {
                retValue = retValue + q.id + "." + q.question + "\n";
            }
        }
        return retValue;
    }

    public Question getQuestionFromATopic(Topic topic) {
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

        return tmp.get(rnd);
    }

    public void addTopic(String topic) {
        topicList.add(new Topic(topic));
    }

    public Set<Topic> searchTopic(String search) {
        Set<Topic> st = new HashSet<>();
        for (Topic t : topicList) {
            if (t.name.contains(search)) {
                st.add(t);
            }
        }
        return st;
    }

    public void rateQuestion(Question question, String rating) {
        question.difficulty = rating;
    }

    public Topic getARandomTopic() {
        Random random = new Random();
        int rnd = random.nextInt(topicList.size());
        ArrayList<Topic> tp = new ArrayList<>(topicList);
        return tp.get(rnd);
    }

    public boolean topicExists(String text) {
        for (Topic t : topicList) {
            if (t.name.equals(text)) {
                System.out.println(t.name);
                return true;
            }
        }
        return false;
    }
}