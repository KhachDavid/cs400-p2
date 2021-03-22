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
