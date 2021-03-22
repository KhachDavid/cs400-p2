import java.util.List;

/**
 * Class to declare one Question object, with a number of characteristics.
 */
public class Question implements QuestionInterface{
  private String question;
  private String answer;
  private String difficulty;
  private String topic;

  /**
   * Constructor to define a question object
   * @param q String representing question
   * @param a String representing answer
   * @param d String representing difficulty
   * @param t String representing topic
   */
  public Question(String q, String a, String d, String t) {
    question = q;
    answer = a;
    difficulty = d;
    topic = t;
  }

  /**
   * Getter method to retrieve the question.
   * @return a String representing the question.
   */
  @Override public String getQuestion() {
    return question;
  }

  /**
   * Getter method to retrieve the answer.
   * @return a String representing the answer.
   */
  @Override public String getAnswer() {
    return answer;
  }

  /**
   * Getter method to retrieve the difficulty.
   * @return a String representing the difficulty.
   */
  @Override public String getDifficulty() {
    return difficulty;
  }

  /**
   * Getter method to retrieve the topic.
   * @return a String representing the topic.
   */
  @Override public String getTopic() {
    return topic;
  }

  /**
   * Override method to define a new way of comparing two question objects.
   * @param o the object to compare with.
   * @return a boolean representing whether two Questions are equal or not.
   */
  @Override
  public boolean equals(Object o) {
    Question a = (Question) o;
    if (getQuestion().equals(a.getQuestion())) {
      return true;
    }
    else {
      return false;
    }
  }
}
