import org.junit.*;
import static org.junit.Assert.*;

public class FrontEndDeveloperTests {

	/**
	 * This method tests the functionality 
	 * of adding a question from the frontend
	 */
	@Test
	public void TestAddQuestion() {
		BackEndDummy test = new BackEndDummy();
		String question = "What is the capital city of Peru?";
		test.addQuestion(question);
		String allQuestions = test.listAllQuestions();
		assertEquals(allQuestions, question);
		fail("Not Implemented");	
	}
	
	/**
	 * This method tests the functionality 
	 * of removing a question from the frontend
	 */
	@Test
	public void TestRemoveQuestion(){
		BackEndDummy test = new BackEndDummy();
		String question = "What is the capital city of Peru?";
	        test.removeQuestion(question);
  		String allQuestions = test.listAllQuestions();
                assertEquals(allQuestions, question);
		fail("Not Implemented");
	}
	
	/**
	 * This method tests the functionality
	 * of showing a message based on the
	 * answer's correctness
	 */ 
	@Test
	public void TestMessageBasedonAnEvent(){
		BackEndDummy test = new BackEndDummy();
		FrontEndDummy test1 = new FrontEndDummy();
		String message = test1.getUserAnswer("What is the capital city of Peru?", "Buenos Aires");
		assertEquals(message, "Wrong Answer");
		message = test1.getUserAnswer("What is the capital city of Peru?", "Lima");		
		assertEquals(message, "Hooray!!!");
		fail("Not Implemented");
	}
	
	/**
	 * This method tests the functionality 
	 * of getting a question from a given topic
	 */ 
	@Test
	public void TestQuestionFromATopic() {
		BackEndDummy test = new BackEndDummy();
		String question = test.getQuestionFromATopic("History");
		// assertEquals(question.topic, "History");		
		fail("Not Implemented");
	}
	
	/**
	 * This method tests the functionality
	 * of rating the difficulty of the question
	 */ 
	@Test
	public void TestGiveDifficultyRatingToTheQuestion() {
		BackEndDummy test = new BackEndDummy();
		String question = "";
		String rating = "easy";
		test.rateQuestion(question, rating);
		// assertEquals(question.rating, rating);
		fail("Not Implemented");
	}
}

