import java.util.HashSet;
import java.util.Set;

public class Topic {

    public String name;
    public Set<Question> listOfQuestions;

    public Topic(String name) {
        this.name = name;
        listOfQuestions = new HashSet<>();
    }

    public void addQuestion(Question question) {
        listOfQuestions.add(question);
    }

    public void removeQuestion(Question question) {
        listOfQuestions.remove(question);
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Topic && ((Topic) o).name.equals(this.name);
    }
}
