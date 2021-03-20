import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.*;

public class BackEndDeveloperTests {
    public static QuestionTree qt;

    @BeforeEach
    public void setup() {
        qt = new QuestionTree();
    }

    /**
     * Test Basic functionality of adding question to the tree
     * Ideally result in following topic tree
     *                  Geography
     *                 /        \
     *   Computer Science       Physics
     * Each have One Question inside
     *
     * Pass if and only if
     * 1. size is correct
     * 2. Nodes are in correct position
     * 3. Nodes are with correct color
     */
    @Test
    public void testAddQuestionBasic() {
        qt = new QuestionTree();
        //Add Question about Computer Science
        qt.addQuestion(new Question("q1", "a1", "Easy", "Computer Science"));
        assertEquals(1, qt.size);
        assertEquals("Computer Science",qt.root.data);
        assertEquals(true, qt.root.isBlack);

        //Add Question about Geography
        qt.addQuestion(new Question("q2", "a2", "Hard", "Geography"));
        assertEquals(2, qt.size);
        assertEquals("Computer Science",qt.root.data);
        assertEquals(true, qt.root.isBlack);
        assertEquals("Geography",qt.root.rightChild.data);
        assertEquals(false, qt.root.rightChild.isBlack);

        //Add Question about Physics
        qt.addQuestion(new Question("q3", "a3", "Moderate", "Physics"));
        assertEquals(3, qt.size);
        assertEquals("Geography",qt.root.data);
        assertEquals(true, qt.root.isBlack);
        assertEquals("Computer Science", qt.root.leftChild.data);
        assertEquals(false, qt.root.leftChild.isBlack);
        assertEquals("Physics", qt.root.rightChild.data);
        assertEquals(false, qt.root.rightChild.isBlack);
    }

    @Test
    /**
     * Test embedded Chaining functionality of adding question to the tree
     * Ideally result in following topic tree
     *                  Geography
     *                 /        \
     *   Computer Science       Physics
     *
     *  Geography:          q2, q4, q5
     *  Computer Science:   q1, q7, q8
     *  Physics:            q3, q6
     *
     *  Pass if and only if sizes of lists are correct
     */
    public void testAddQuestionChaining() {
        testAddQuestionBasic();

        qt.addQuestion(new Question("q7", "a7", "Hard", "Computer Science"));
        qt.addQuestion(new Question("q8", "a8", "Hard", "Computer Science"));
        assertEquals(3, qt.getQuestionList("Computer Science").size());

        qt.addQuestion(new Question("q4", "a4", "Hard", "Geography"));
        qt.addQuestion(new Question("q5", "a5", "Hard", "Geography"));
        assertEquals(3, qt.getQuestionList("Geography").size());

        qt.addQuestion(new Question("q6", "a6", "Hard", "Physics"));
        assertEquals(2, qt.getQuestionList("Physics").size());


        //qt.printTree();
    }

    /**
     * Test functionality of getQuestionFromATopic()
     * The function should randomly return a Question Object from specified topic
     *
     * Pass if the only if
     * 1. The tree remain intact
     * 2. The Questions returned should be in specified topics
     */

    @Test
    public void testGetQuestion() {
        testAddQuestionChaining();

        Question t1 = qt.getQuestionFromATopic("Computer Science");
        assertEquals(3, qt.getQuestionList("Computer Science").size());
        assertEquals("Computer Science", t1.topic);

        Question t2 = qt.getQuestionFromATopic("Geography");
        assertEquals(3, qt.getQuestionList("Geography").size());
        assertEquals("Geography", t2.topic);

        Question t3 = qt.getQuestionFromATopic("Physics");
        assertEquals(2, qt.getQuestionList("Physics").size());
        assertEquals("Physics", t3.topic);

    }

    /**
     * Test deletion of questions in the tree.
     * The deletion will not remove the Topic Node from the tree
     * Only remove questions from embedded chains from Topic Node
     *
     * The test will Randomly remove questions from the tree
     * Using the functionality getQuestionFromATopic
     *
     *  Pass if and only if
     *  1. sizes of lists are correct.
     *  2. The tree does not contain removed questions.
     */
    @Test
    public void testRemoveQuestion() {
        testAddQuestionChaining();

        Question t1 = qt.getQuestionFromATopic("Computer Science");
        qt.removeQuestion(t1);
        assertEquals(2, qt.getQuestionList("Computer Science").size());
        assertEquals(false, qt.contains(t1));

        Question t2 = qt.getQuestionFromATopic("Geography");
        qt.removeQuestion(t2);
        assertEquals(2, qt.getQuestionList("Geography").size());
        assertEquals(false, qt.contains(t2));

        Question t3 = qt.getQuestionFromATopic("Physics");
        qt.removeQuestion(t3);
        assertEquals(1, qt.getQuestionList("Physics").size());
        assertEquals(false, qt.contains(t3));

        qt.printTree();
    }


}
