import java.util.List;

public interface BackendInterface {
	List<QuestionInterface> listQuestions(); // list all the questions in the database
	QuestionInterface getQuestion(int qid); // get a question by its identifier
	List<QuestionInterface> testMe(int number); // get "number" random questions
	int removeQuestion(int qid); // remove a question
	int addQuestion(String question, List<String> options, String answer); // add a new question to database
	boolean checkAnswer(int qid, String answer); // check if given answer is correct
}
