import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Screen;
import javafx.stage.Stage;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.NotActiveException;

/**
 *
 */
public class FrontEndUtil {

    // Colors
    public static final Paint pinkRed = Color.web("#eb3464");
    public static final Paint plainRed = Color.web("#ff0043");
    public static final Paint black = Color.web("#000000");
    public static final Paint backgroundColor = Color.web("#fcc603");

    // Fonts
    public static final Font smallFont = new Font("Tahoma", 10);
    public static final Font mediumFont = new Font("Tahoma", 20);
    public static final Font largeFont = new Font("Tahoma", 30);
    public static final Font extraLargeFont = new Font("Tahoma", 40);
    public static final Font doubleExtraLargeFont = new Font("Tahoma", 60);
    public static final Font maxLargeFont = new Font("Tahoma", 80);

    public static final Font ITALIC_FONT =
            Font.font(
                    "Tahoma",
                    FontPosture.ITALIC,
                    largeFont.getSize()
            );

    // Getting the dimensions of the user's monitor
    public static final Rectangle2D screenBounds = Screen.getPrimary().getBounds();

    // Image Views
    public static ImageView wrongImage = null;
    public static ImageView correctImage = null;



    /**
     *
     * @throws NotActiveException
     */
    public FrontEndUtil() throws NotActiveException {
        throw new NotActiveException("Frontend Util is Not Instantiable");
    }

    /**
     *
     * @param str
     * @return
     */
    public static String removeWhiteSpace(String str) {
        return str.replaceAll("\\s+", "-");
    }

    /**
     *
     * @param question
     * @param answer
     * @return
     */
    public static String checkCorrectness(Question question, String answer) {
        try{
            if (question.answer.equals(answer)) {
                playCorrectSound();
            } else {
                playIncorrectSound();
            }
        }
        catch (NullPointerException ignored) {}

        return question.answer.equals(answer) ? "Correct!" : "Incorrect!";
    }

    // Audio courtesy
    // https://www.youtube.com/watch?v=2naim9F4010
    public static void playIncorrectSound() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src\\audio\\wrong.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch(Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    // Audio courtesy
    // https://www.youtube.com/watch?v=lonIG_K4hz4
    public static void playCorrectSound() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src\\audio\\right.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch(Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    public static String replaceUnderScoreWithWhiteSpace(String str) {
        return str.replaceAll("_", " ");
    }

    public static void removeEverythingFromGridPane(GridPane gridPane) {
        gridPane.getChildren().clear();
    }

    public static void keyBoardShortCuts(Stage addATopic, Stage addQuestion, Stage removeQuestion, Stage changeTheTopic, BorderPane parent, Stage main) {
        parent.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.C && event.isControlDown()){
                changeTheTopic.show();
            }
            else if (event.getCode() == KeyCode.R && event.isControlDown()){
                removeQuestion.show();
            }
            else if (event.getCode() == KeyCode.A && event.isControlDown()){
                addQuestion.show();
            }
            else if (event.getCode() == KeyCode.T && event.isControlDown()){
                addATopic.show();
            }
            else if (event.getCode() == KeyCode.ESCAPE) {
                main.close();
            }
        });
    }

    public static void closeNode(Node node, Stage stage) {
        node.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                stage.close();
            }
        });
    }
}