import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataWranglerTests {

  //Tests whether question  returned properly.
  @Test
  public void testGetQuestion() throws IOException {
    QuestionDataReader test = new QuestionDataReader();
    FileReader readTest = new FileReader("src/data/S10_question_answer_pairs.txt");
    assertEquals(test.read(readTest).get(0).getQuestion(),"Was Alessandro Volta a professor of chemistry?");
  }

  //Tests whether answer returned properly.
  @Test
  public void testGetAnswer() throws IOException {
    QuestionDataReader test = new QuestionDataReader();
    FileReader readTest = new FileReader("src/data/S10_question_answer_pairs.txt");
    assertEquals(test.read(readTest).get(0).getAnswer(),"Alessandro Volta was not a professor of chemistry.");
  }

  //Tests whether the topic is returned properly.
  @Test
  public void getTopic() throws IOException {
    QuestionDataReader test = new QuestionDataReader();
    FileReader readTest = new FileReader("src/data/S10_question_answer_pairs.txt");
    assertEquals(test.read(readTest).get(0).getTopic(),"Alessandro_Volta");
  }

  //Tests whether the difficulty is returned properly.
  @Test
  public void getDifficulty() throws IOException {
    QuestionDataReader test = new QuestionDataReader();
    FileReader readTest = new FileReader("src/data/S10_question_answer_pairs.txt");
    assertEquals(test.read(readTest).get(0).getDifficulty(),"easy");
  }

  //Tests whether the size after the file is read through matches the expected size.
  @Test
  public void sizeCheck() throws IOException {
    QuestionDataReader test = new QuestionDataReader();
    FileReader readTest = new FileReader("src/data/S10_question_answer_pairs.txt");
    assertEquals(test.read(readTest).size(),831);
  }
}
