import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

public class QuestionTree extends RedBlackTree <String> {

    /**
     * Search for Question List of data's topic
     * @param data specified question
     * @return The List of given topic
     */
    public List<Question> search(Question data) {
        Node<String> res = search(data.topic);
        if (res != null) return ((TopicNode) res).questions;
        else return null;
    }

    /**
     * Check if A Question exists in the tree.
     * @param data specified question
     * @return true if the tree contains the question, false otherwise.
     */
    public boolean contains(Question data) {
        return search(data).contains(data);
    }

    public List<Question> getQuestionList(String topic) {
        Node<String> res = search(topic);
        if (res != null) {
            return ((TopicNode) res).questions;
        } else return null; //The topic does not exist
    }
    /**
     * Add Question data to the tree.
     * @param data question need to be inserted.
     * @return true if the question is successfully inserted.
     */
    public boolean addQuestion(Question data) {
        List<Question> res = search(data);
        if (res != null) {
            // We have the list of this data's topic
            res.add(data);
            return true;
        } else {
            //List of the topic does not exist
            TopicNode tpNode = (TopicNode) super.insert(data.topic, new TopicNode());
            tpNode.questions.add(data);
            return true;
        }
    }

    /**
     * Remove the question from the tree
     * @param data the question needs to be removed
     * @return true if question is successfully removed
     */
    public boolean removeQuestion(Question data) {
        List<Question> res = search(data);
        if (res != null) {
            return res.remove(data);
        } else {
            //Question does not exist in the tree
            return false;
        }
    }

    public String listAllQuestions() {
        String retValue = "";

        for (Node<String> node : this) {
            TopicNode topicNode = (TopicNode) node;
            for (Question q : topicNode.questions) {
                retValue = retValue + q.id + "." + q.question + "\n";
            }
        }

        return retValue;
    }

    public Question getQuestionFromATopic(String topic) throws NoSuchElementException {
        Random random = new Random();
        List<Question> qList = getQuestionList(topic);
        return qList.get(random.nextInt(qList.size()));
    }

    public void rateQuestion(Question question, String rating) {
        question.difficulty = rating;
    }

    public void printTree() {
        System.out.println(this.root.toString());
        for (Node<String> node : this) {
            TopicNode topicNode = (TopicNode) node;
            System.out.println(topicNode.data + ":");
            for (Question q : topicNode.questions) {
                System.out.println("    " + q.id + "." + q.question);
            }
        }
    }

}
