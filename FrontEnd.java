import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.*;

/**
 *
 */
public class FrontEnd extends Application {

    private static final BackEndDummy back = new BackEndDummy();
    private static Question currentQuestion = new Question(null, null, null, null);
    private static Topic currentTopic = new Topic(null);
    private static int bottomGridColumnCounter = 0;
    private BorderPane parent;
    private GridPane bottomGrid;
    private Label questionText;
    private Label mainText;
    private Label hint;
    private TextField answerField;
    private Stage changeTheTopic;
    private Stage addQuestion;
    private Stage addATopic;
    private Stage removeQuestion;

    private int hvcounter = 0;

    // Runs Before The Start
    @Override
    public void init() throws Exception {
        super.init();
        fillQuestions(); // TODO Call The real fill Questions
    }

    // Runs The Actual Application
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("CS400 Quizlet");

        // Setting the size of the window to be equals to the user's monitor parameters
        stage.setHeight(FrontEndUtil.screenBounds.getHeight() - (FrontEndUtil.screenBounds.getHeight() / 24));
        stage.setWidth(FrontEndUtil.screenBounds.getWidth() + (FrontEndUtil.screenBounds.getWidth() / 128));

        // Adding Decorative Style to the window
        stage.initStyle(StageStyle.DECORATED);
        createSceneForStage(stage);
        addDesktopIcon(stage);
        stage.show();

        // Initializing a modality window
        Stage modalityWindow = new Stage();
        modalityWindow.setTitle("Welcome Page");
        modalityWindow.initModality(Modality.APPLICATION_MODAL);
        addDesktopIcon(modalityWindow);
        modalityWindow.initStyle(StageStyle.UTILITY);
        modalityWindow.setHeight((FrontEndUtil.screenBounds.getHeight() - (FrontEndUtil.screenBounds.getHeight() / 24)) / 1.4);
        modalityWindow.setWidth((FrontEndUtil.screenBounds.getWidth() + (FrontEndUtil.screenBounds.getWidth() / 128)) / 1.4);

        createSceneForModality(modalityWindow);
        addDesktopIcon(modalityWindow);
        modalityWindow.show();
    }

    // Creates a scene for the modality window
    public void createSceneForModality(Stage modality) {
        VBox parent = new VBox();
        parent.setAlignment(Pos.TOP_CENTER);
        Label labelText = addTextLabels(parent, "CS400 Quizlet!", FrontEndUtil.black, FrontEndUtil.doubleExtraLargeFont, "center", 0, 0);

        GridPane creators = new GridPane();
        creators.setPadding(new Insets(15, 15, 15, 15));
        Label createdBy = addTextLabels(parent, "Created By", FrontEndUtil.black, FrontEndUtil.extraLargeFont, "center", 0, 1);
        Label n1 = addTextLabels(creators, "Abhimanyu Gupta: Data Wrangler", FrontEndUtil.black, FrontEndUtil.ITALIC_FONT, "center", 1, 0);
        Label n2 = addTextLabels(creators, "Adilnur Istekov: Integration Manager", FrontEndUtil.black, FrontEndUtil.ITALIC_FONT, "center", 2, 0);
        Label n3 = addTextLabels(creators, "David Khachatryan: Frontend Developer", FrontEndUtil.black, FrontEndUtil.ITALIC_FONT, "center", 3, 0);
        Label n4 = addTextLabels(creators, "Zhiyuan Han: Backend Developer", FrontEndUtil.black, FrontEndUtil.ITALIC_FONT, "center", 4, 0);
        n1.setPadding(new Insets(15, 15, 15, 15));
        n2.setPadding(new Insets(15, 15, 15, 15));
        n3.setPadding(new Insets(15, 15, 15, 15));
        n4.setPadding(new Insets(15, 15, 15, 15));

        parent.getChildren().add(creators);

        Scene modal = new Scene(parent);
        modal.getStylesheets().add("stylesheets/modality.css");
        modality.setScene(modal);
    }

    // Creates a scene for the main stage
    public void createSceneForStage(Stage stage) {

        //--------------------------------------------
        // Create the root node
        parent = new BorderPane();
        // Main layer
        //---------------------------------------------


        //----------------------------------------------
        // Create the HBox node
        HBox parentTopChild = new HBox();
        parentTopChild.setSpacing(20);
        parentTopChild.setPadding(new Insets(5, 5, 5, 5)); // add padding
        parent.setTop(parentTopChild);

        // Bottom grid
        bottomGrid = new GridPane();
        parent.setBottom(bottomGrid);

        // Add menus
        createQuestionMenu(parentTopChild);
        createTopicsMenu(parentTopChild);
        Button hintButton = createButton(parentTopChild, "Get a Hint", "right",  0,0, Optional.empty());
        Button randomButton = createButton(parentTopChild, "Random Topic", "right",  0,0, Optional.empty());
        // Navigation Bar represented as an HBox
        //----------------------------------------------


        //-----------------------------------------------
        // Build the center of the Border pane as a VBox
        VBox parentCenterChild = new VBox();
        parentCenterChild.setAlignment(Pos.TOP_CENTER);
        parent.setCenter(parentCenterChild);

        // Add some header and main messages
        mainText = addTextLabels(parentCenterChild, "First, Let us Pick A Topic. Press Ctrl/Cmd + C", FrontEndUtil.plainRed, FrontEndUtil.largeFont, "center", 0, 0);
        mainText.setPadding(new Insets(30));
        // Create an image hyperlink
        ImageView image = new ImageView("https://2.gravatar.com/avatar/ba9ac7f7a62a76857490f8ebd1d17a03?s=40&d=https%3A%2F%2F2.gravatar.com%2Favatar%2Fad516503a11cd5ca435acc9bb6523536%3Fs%3D40&r=PG");
        Hyperlink link = addHyperlink(parentCenterChild, "Click to get a question","center",0, 0, Optional.of(image), Optional.empty());
        link.setPadding(new Insets(0,0,10,0));
        // The main VBox that is in at the very top of the center of the Border Lane
        //------------------------------------------------


        //----------------------------------------------
        // Show a message after a hyperlink click
        GridPane questionGrid = new GridPane();
        questionGrid.setVgap(10);


        questionText = addTextLabels(questionGrid, "", FrontEndUtil.plainRed, FrontEndUtil.mediumFont, "center", 0, 0);
        parentCenterChild.getChildren().add(questionGrid);
        // The Question Grid as  Grid Pane
        // Represented as a grid pane to keep the formatting
        //-------------------------------------------------


        // --------------------------------------------------------
        // Add buttons and answer form
        VBox buttonAndAnswerForm = new VBox();
        buttonAndAnswerForm.setSpacing(10);

        // answer form
        answerField = new TextField();
        answerField.setPromptText("Type Your Answer");
        answerField.setMinWidth(50);
        answerField.setPrefWidth(50);
        answerField.setMaxWidth(400);

        // add listener
        answerField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                answerField.setPrefWidth(answerField.getText().length() * 7); // why 7? Totally trial number.
            }
        });

        answerField.setPadding(new Insets(10, 10, 10, 10));
        buttonAndAnswerForm.getChildren().addAll(answerField);

        answerField.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER){
                if (bottomGridColumnCounter >= 8) {
                    bottomGridColumnCounter = 0;
                    bottomGrid = new GridPane();
                    parent.setBottom(bottomGrid);

                    if(FrontEndUtil.checkCorrectness(currentQuestion, answerField.getText()).equals("Correct!")) {
                        Hyperlink hyperlink = addHyperlink(parent.getBottom(), "", "", bottomGridColumnCounter, 0, Optional.of(new ImageView("https://i.imgur.com/vG1TsSJ.png")), Optional.of(currentQuestion));
                    }
                    else {
                        Hyperlink hyperlink = addHyperlink(parent.getBottom(), "", "", bottomGridColumnCounter, 0, Optional.of(new ImageView("https://i.imgur.com/Hikt7vW_d.png")), Optional.of(currentQuestion));
                    }
                }
                else if (FrontEndUtil.checkCorrectness(currentQuestion, answerField.getText()).equals("Correct!")) {
                    Hyperlink hyperlink = addHyperlink(parent.getBottom(), "", "", bottomGridColumnCounter, 0, Optional.of(new ImageView("https://i.imgur.com/vG1TsSJ.png")), Optional.of(currentQuestion));
                }
                else {
                    Hyperlink hyperlink = addHyperlink(parent.getBottom(), "", "", bottomGridColumnCounter, 0, Optional.of(new ImageView("https://i.imgur.com/Hikt7vW_d.png")), Optional.of(currentQuestion));
                }
                bottomGridColumnCounter++;
                System.out.println(bottomGridColumnCounter);
            }
        });
        // answer form end

        // submit button
        Button button1 = createButton(buttonAndAnswerForm, "Submit Your Answer", "center", 0, 2, Optional.of(answerField));
        button1.setPadding(new Insets(10, 10, 10, 10));

        // hint text
        hint = addTextLabels(buttonAndAnswerForm, "", FrontEndUtil.pinkRed, FrontEndUtil.smallFont, "", 0, 0);

        buttonAndAnswerForm.setAlignment(Pos.CENTER);
        questionGrid.add(buttonAndAnswerForm, 0, 1);
        // Part of the question grid. In the second row
        //--------------------------------------------------------------------------------

        // The structure of the Border Pane is as follows
        //                       (Border         Pane         root)
        //                              |                      |
        //                              v                      v
        //                    (HBox parentTopChild)      (VBox parentCenterChild)
        //                                                     |
        //                                                     V
        //                                               (Grid Pane questionGrid)
        //                                                     |
        //                                                     V
        //                                               (VBox buttonAndAnswerForm) -> Hint

        // Configuring Hyperlink and Button Actions
        link.setOnAction(e -> {
            try{ currentQuestion = showAQuestionBasedOnAClick(link, questionText, currentTopic); }
            catch (NullPointerException ignore) {};
            hint.setText("");

        });

        hintButton.setOnAction(e -> {
            //TODO Maybe Add More Useful Hints
            try { hint.setText("The answer starts with " + currentQuestion.answer.substring(0, 3)); }
            catch (NullPointerException ignored) {};
        });

        randomButton.setOnAction(e -> {
            Topic t = back.getARandomTopic();
            currentTopic = t;
            mainText.setText("Get Questions From " + FrontEndUtil.replaceUnderScoreWithWhiteSpace(currentTopic.name));
            try {
                changeTheTopic.close();
            }
            catch (NullPointerException ignored) {};
        });

        // Add Shortcuts

        changeTheTopicPanel(false);
        addATopicPanel(false);
        removeQuestionPanel(false);
        addQuestionPanel(false);

        createSceneForChangeTopic(changeTheTopic);
        createSceneForAddTopic(addATopic);
        createSceneForRemoveQuestion(removeQuestion);
        createSceneForAddQuestion(addQuestion);
        FrontEndUtil.keyBoardShortCuts(addATopic, addQuestion, removeQuestion, changeTheTopic, parent, stage);

        // Configure the scene to work with the stage
        Scene scene = new Scene(parent);
        scene.getStylesheets().add("stylesheets/main.css");
        stage.setScene(scene);
    }

    /**
     * Adds the message string to be shown on the screen
     * @param parent is the parent node that controls all the elements (image, text) on the screen
     * @param message is the text that the label contains
     */
    public Label addTextLabels(Object parent, String message, Paint paint, Font font, String loc,
                                int row, int column) {
        Label label = new Label(message);

        label.setTextFill(paint);
        label.setFont(font);
        label.setId("--" + FrontEndUtil.removeWhiteSpace(message));
        label.setWrapText(true);
        label.setTextAlignment(TextAlignment.CENTER);
        label.setEffect(new Lighting());
        if (parent instanceof VBox) {
            VBox tmp = (VBox) parent;
            tmp.getChildren().add(label);
        }

        else if (parent instanceof HBox) {
            HBox tmp = (HBox) parent;
            tmp.getChildren().add(label);
        }

        else if (parent instanceof BorderPane) {
            BorderPane tmp = (BorderPane) parent;

            switch (loc) {
                case "center" -> { label.setAlignment(Pos.CENTER); tmp.setCenter(label); }
                case "left" -> tmp.setLeft(label);
                case "right" -> tmp.setRight(label);
                case "bottom" -> tmp.setBottom(label);
                default -> tmp.setTop(label);
            }
        }


        else if (parent instanceof GridPane) {
            GridPane tmp = (GridPane) parent;
            tmp.setAlignment(Pos.TOP_CENTER);

            //label.setMaxWidth((FrontEndUtil.screenBounds.getWidth() - 400) / 2);
            label.setWrapText(true);
            tmp.add(label, column, row);
        }

        return label;
    }

    /**
     *
     * @param parent
     * @param message
     * @param image
     */
    public void addImageLabels(Object parent, String message, ImageView image, String loc, int column, int row) {

        if (message.equals("Correct!")) {
            System.out.println();
        }

        Label label = new Label(message, image);
        label.setWrapText(true);
        label.setId("--" + FrontEndUtil.removeWhiteSpace(message));

        if (parent instanceof VBox) {
            VBox tmp = (VBox) parent;
            tmp.getChildren().add(label);
        }

        else if (parent instanceof HBox) {
            HBox tmp = (HBox) parent;
            tmp.getChildren().add(label);
        }

        else if (parent instanceof BorderPane) {
            BorderPane tmp = (BorderPane) parent;
            switch (loc) {
                case "center" -> { tmp.setCenter(label); label.setAlignment(Pos.CENTER);}
                case "left" -> tmp.setLeft(label);
                case "right" -> tmp.setRight(label);
                case "bottom" -> tmp.setBottom(label);
                default -> tmp.setTop(label);
            }
        }

        else if (parent instanceof GridPane) {
            GridPane tmp = (GridPane) parent;
            tmp.add(label, column, row);
        }
    }

    /**
     *
     * @param parent
     * @param message
     * @param loc
     * @param image
     * @return
     */
    public Hyperlink addHyperlink(Object parent, String message, String loc, int column, int row, Optional<ImageView> image, Optional<Question> question) {
        Hyperlink hyperlink = null;
        ImageView realImage = image.isPresent() ? image.get() : null;

        if (realImage == null) hyperlink = new Hyperlink(message);
        else hyperlink = new Hyperlink(message, realImage);

        Question realQuestion = question.isPresent() ? question.get() : null;

        hyperlink.setId("--" + FrontEndUtil.removeWhiteSpace(message));
        hyperlink.setWrapText(true);

        if (parent instanceof VBox) {
            VBox tmp = (VBox) parent;
            tmp.getChildren().add(hyperlink);
        }

        else if (parent instanceof HBox) {
            HBox tmp = (HBox) parent;
            tmp.getChildren().add(hyperlink);
        }

        else if (parent instanceof BorderPane) {
            BorderPane tmp = (BorderPane) parent;
            switch (loc) {
                case "center" -> { tmp.setCenter(hyperlink); hyperlink.setAlignment(Pos.CENTER);}
                case "left" -> tmp.setLeft(hyperlink);
                case "right" -> tmp.setRight(hyperlink);
                case "bottom" -> tmp.setBottom(hyperlink);
                default -> tmp.setTop(hyperlink);
            }
        }

        else if (parent instanceof GridPane) {
            GridPane tmp = (GridPane) parent;
            tmp.add(hyperlink, column, row);

            if (realQuestion != null) {
                hyperlink.setOnAction(e -> {
                    questionText.setText(realQuestion.question);
                    hint.setText("");
                    answerField.clear();
                    answerField.setPromptText("The correct answer is: " + realQuestion.answer);
                    currentQuestion = realQuestion;
                    mainText.setText("Get Questions From " + FrontEndUtil.replaceUnderScoreWithWhiteSpace(currentTopic.name));
                });
            }
        }

        if (message.contains("topic")) {
            message = message.substring(5, message.length());
            hyperlink.setText(message);

            String finalMessage = message;
            System.out.println(finalMessage);
            hyperlink.setOnAction(e -> {
                currentTopic = new Topic(finalMessage);
                mainText.setText("Get Questions From " + FrontEndUtil.replaceUnderScoreWithWhiteSpace(currentTopic.name));
                changeTheTopic.close();
                questionText.setText("");
            });
        }

        return hyperlink;
    }

    /**
     *
     * @param parent1
     * @param message
     * @param loc
     * @param column
     * @param row
     * @return
     */
    public Button createButton(Object parent1, String message, String loc, int column, int row, Optional<TextField> textField) {
        Button button = new Button(message);
        button.setMnemonicParsing(true);
        button.setId("--" + FrontEndUtil.removeWhiteSpace(message));
        button.setWrapText(true);

        TextField realText = textField.isPresent() ? textField.get() : null;

        if (message.equals("Submit Your Answer")) {
            button.setOnAction(e -> {
                assert realText != null;
                try {
                    if (bottomGridColumnCounter >= 8) {
                        bottomGrid = new GridPane();
                        parent.setBottom(bottomGrid);
                        bottomGridColumnCounter = 0;
                        if (FrontEndUtil.checkCorrectness(currentQuestion, realText.getText()).equals("Correct!")) {
                            addHyperlink(parent.getBottom(), "", "", bottomGridColumnCounter, 0, Optional.of(new ImageView("https://i.imgur.com/vG1TsSJ.png")), Optional.of(currentQuestion));
                        } else {
                            addHyperlink(parent.getBottom(), "", "", bottomGridColumnCounter, 0, Optional.of(new ImageView("https://i.imgur.com/Hikt7vW_d.png")), Optional.of(currentQuestion));
                        }
                    } else if (FrontEndUtil.checkCorrectness(currentQuestion, realText.getText()).equals("Correct!")) {
                        addHyperlink(parent.getBottom(), "", "", bottomGridColumnCounter, 0, Optional.of(new ImageView("https://i.imgur.com/vG1TsSJ.png")), Optional.of(currentQuestion));
                    } else {
                        addHyperlink(parent.getBottom(), "", "", bottomGridColumnCounter, 0, Optional.of(new ImageView("https://i.imgur.com/Hikt7vW_d.png")), Optional.of(currentQuestion));
                    }
                    bottomGridColumnCounter++;
                }
                catch (NullPointerException ignored){}

                System.out.println(bottomGridColumnCounter);
            });
        }

        if (parent1 instanceof VBox) {
            VBox tmp = (VBox) parent1;
            tmp.getChildren().add(button);
        }

        else if (parent1 instanceof HBox) {
            HBox tmp = (HBox) parent1;
            tmp.getChildren().add(button);
        }

        else if (parent1 instanceof BorderPane) {
            BorderPane tmp = (BorderPane) parent1;
            switch (loc) {
                case "center" -> tmp.setCenter(button);
                case "left" -> tmp.setLeft(button);
                case "right" -> tmp.setRight(button);
                case "bottom" -> tmp.setBottom(button);
                default -> tmp.setTop(button);
            }
        }

        else if (parent1 instanceof GridPane) {
            GridPane tmp = (GridPane) parent1;
            tmp.add(button, column, row);
        }

        return button;
    }

    /**
     *  @param hyperlink
     * @param text
     */
    public Question showAQuestionBasedOnAClick(Hyperlink hyperlink, Label text, Topic topic) {
        if (topic.name == null) {
            hint.setText("Please Choose A Topic From The \"Change the Topic\" menu by pressing Ctrl/Cmd + C");
        }

        Question question1 = new Question(null, null, null, null);
        try {
            question1 = back.getQuestionFromATopic(topic);
        }
        catch (NoSuchElementException ignored) {};
        text.setText(question1.question);
        return question1;
    }

    /**
     *
     * @param parent
     */
    public void createQuestionMenu(HBox parent) {
        MenuItem menuItem = new MenuItem ("_Add A Question                                    Ctrl+A");
        MenuItem menuItem1 = new MenuItem("_Remove/Edit Current Question             Ctrl+R");

        MenuButton menuButton = new MenuButton("_Questions", null, menuItem, menuItem1);
        menuButton.setId("question-menu");

        menuItem.setOnAction(e -> {
            addQuestionPanel(true);
        });

        menuItem1.setOnAction(e -> {
            removeQuestionPanel(true);
        });

        // set mnemonic parsing
        menuItem.setMnemonicParsing(true);
        menuItem1.setMnemonicParsing(true);
        menuButton.setMnemonicParsing(true);

        parent.getChildren().add(menuButton);
    }

    public void addQuestionPanel(boolean showWindow) {
        addQuestion = new Stage();
        addQuestion.setTitle("Add A Question");
        addQuestion.initStyle(StageStyle.DECORATED);
        addQuestion.setHeight((FrontEndUtil.screenBounds.getHeight() - (FrontEndUtil.screenBounds.getHeight() / 24)) / 1.2);
        addQuestion.setWidth((FrontEndUtil.screenBounds.getWidth() + (FrontEndUtil.screenBounds.getWidth() / 128)) / 1.2);

        createSceneForAddQuestion(addQuestion);
        addDesktopIcon(addQuestion);
        if (showWindow) addQuestion.show();
    }

    public void removeQuestionPanel(boolean showWindow) {
        removeQuestion = new Stage();
        removeQuestion.setTitle("Remove/Edit Current Question");
        removeQuestion.initStyle(StageStyle.DECORATED);
        removeQuestion.setHeight((FrontEndUtil.screenBounds.getHeight() - (FrontEndUtil.screenBounds.getHeight() / 24)) / 1.2);
        removeQuestion.setWidth((FrontEndUtil.screenBounds.getWidth() + (FrontEndUtil.screenBounds.getWidth() / 128)) / 1.2);

        createSceneForRemoveQuestion(removeQuestion);
        addDesktopIcon(removeQuestion);
        if (showWindow) removeQuestion.show();
    }

    public void changeTheTopicPanel(boolean showWindow) {
        changeTheTopic = new Stage();
        changeTheTopic.setTitle("Change The Topic");
        changeTheTopic.initStyle(StageStyle.DECORATED);
        changeTheTopic.setHeight((FrontEndUtil.screenBounds.getHeight() - (FrontEndUtil.screenBounds.getHeight() / 24)) / 1.2);
        changeTheTopic.setWidth((FrontEndUtil.screenBounds.getWidth() + (FrontEndUtil.screenBounds.getWidth() / 128)) / 1.2);

        createSceneForChangeTopic(changeTheTopic);
        addDesktopIcon(changeTheTopic);
        if (showWindow) changeTheTopic.show();
    }

    public void addATopicPanel(boolean showWindow) {
        addATopic = new Stage();
        addATopic.setTitle("Add A Topic");
        addATopic.initStyle(StageStyle.DECORATED);
        addATopic.setHeight((FrontEndUtil.screenBounds.getHeight() - (FrontEndUtil.screenBounds.getHeight() / 24)) / 1.2);
        addATopic.setWidth((FrontEndUtil.screenBounds.getWidth() + (FrontEndUtil.screenBounds.getWidth() / 128)) / 1.2);

        createSceneForAddTopic(addATopic);
        addDesktopIcon(addATopic);
        if (showWindow) addATopic.show();
    }

    /**
     *
     * @param parent
     */
    public void createTopicsMenu(HBox parent) {
        MenuItem menuItem = new MenuItem ("_Add A Topic                      Ctrl+T");
        MenuItem menuItem1 = new MenuItem("_Change The Topic             Ctrl+C");

        MenuButton menuButton = new MenuButton("_Topics", null, menuItem, menuItem1);
        menuButton.setId("topic-menu");

        menuItem.setOnAction(e -> {
            addATopicPanel(true);
        });

        menuItem1.setOnAction(e -> {
            changeTheTopicPanel(true);
        });

        // set pneumonic parsing
        menuItem.setMnemonicParsing(true);
        menuItem1.setMnemonicParsing(true);
        menuButton.setMnemonicParsing(true);

        parent.getChildren().add(menuButton);
    }

    /**
     *
     * @param addQuestion
     */
    private void createSceneForAddQuestion(Stage addQuestion) {
        GridPane addTopicParent = new GridPane();
        Label questionLabel = addTextLabels(addTopicParent, "Question", FrontEndUtil.black, FrontEndUtil.smallFont, "", 0, 0);
        questionLabel.setAlignment(Pos.TOP_CENTER);

        TextField newQuestion = new TextField();
        addTopicParent.add(newQuestion, 1, 0);
        newQuestion.setPromptText("Add: Question");
        newQuestion.setMinWidth(200);
        newQuestion.setMaxWidth(400);

        Label answerLabel = addTextLabels(addTopicParent, "Answer", FrontEndUtil.black, FrontEndUtil.smallFont, "", 1, 0);
        answerLabel.setAlignment(Pos.TOP_CENTER);
        TextField answer = new TextField();
        addTopicParent.add(answer, 1, 1);
        answer.setPromptText("Add: Answer");
        answer.setMinWidth(200);
        answer.setMaxWidth(400);

        Button submitButton = createButton(addTopicParent, "Add The Question", "center", 1, 2, Optional.empty());
        Label warningText = addTextLabels(addTopicParent, "Add to topic " +currentTopic.name, FrontEndUtil.black, FrontEndUtil.smallFont, "", 3, 1);

        newQuestion.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                answerField.setPrefWidth(answerField.getText().length() * 8); // why 8? Totally trial number.
            }
        });

        submitButton.setOnAction(e -> {
            Question newQ = new Question(newQuestion.getText(), answer.getText(), "easy", currentTopic);
            back.addQuestion(newQ);
            warningText.setText("Question Added");
        });

        // Customize Grid Pane
        addTopicParent.setVgap(20);
        addTopicParent.setHgap(20);
        addTopicParent.setPadding(new Insets(50));

        // Configurations For The Scene
        FrontEndUtil.closeNode(addTopicParent, addQuestion);
        Scene scene = new Scene(addTopicParent);
        scene.getStylesheets().add("stylesheets/main.css");
        addQuestion.setScene(scene);
    }

    /**
     *
     * @param removeQuestion
     */
    private void createSceneForRemoveQuestion(Stage removeQuestion) {
        GridPane removeQuestionParent = new GridPane();
        if (currentTopic.name != null) {
            Label searchTerm = addTextLabels(removeQuestionParent, "Questions From " + currentTopic.name, FrontEndUtil.black, FrontEndUtil.largeFont, "", 0, 0);
        }
        else {
            Label searchTerm = addTextLabels(removeQuestionParent, "Choose A Topic First!", FrontEndUtil.black, FrontEndUtil.largeFont, "", 0, 0);
        }
        int counter2 = 1;
        for (int i = back.questionList.size() - 1; i >= 0; i--) {
            try {
                if (back.questionList.get(i).topic.equals(currentTopic)) {

                    // Remove Question Button
                    Button removeQuestionButton = createButton(removeQuestionParent, "Remove", "", 1, counter2, Optional.empty());

                    int finalI = i;
                    removeQuestionButton.setOnAction(e -> {
                        //TODO Extremely Inefficient...Needs to be fixed
                        //deleteRow(removeQuestionParent, finalCounter);
                        back.removeQuestion(back.questionList.get(finalI));
                        removeQuestionPanel(true);
                        removeQuestion.close();
                    });

                    // Add Text Field
                    TextField questionBox = new TextField();
                    removeQuestionParent.add(questionBox, 0, counter2);
                    questionBox.setMaxWidth(1000);
                    questionBox.setText(back.questionList.get(i).question);
                    questionBox.setDisable(true);

                    // Edit Question Button
                    Button editQuestionButton = createButton(removeQuestionParent, "Edit", "", 2, counter2, Optional.empty());
                    int finalI1 = i;
                    editQuestionButton.setOnAction(e -> {
                        if (editQuestionButton.getText().equals("Edit")) {
                            editQuestionButton.setText("Save");
                            questionBox.setDisable(false);
                        }
                        else if (editQuestionButton.getText().equals("Save")) {
                            back.questionList.get(finalI1).question = questionBox.getText();
                            editQuestionButton.setText("Edit");
                            questionBox.setDisable(true);
                        }
                    });

                    // Configure the Text Box
                    questionBox.textProperty().addListener(new ChangeListener<String>() {
                        @Override
                        public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                            answerField.setPrefWidth(answerField.getText().length() * 8); // why 8? Totally trial number.
                            editQuestionButton.setDisable(t1.length() < 5); // The Question Must Have At Least 5 characters?
                        }
                    });

                    counter2++;
                }
            }
            catch(NullPointerException ignored){};
        }

        // Customize Grid Pane
        removeQuestionParent.setVgap(20);
        removeQuestionParent.setHgap(20);
        removeQuestionParent.setPadding(new Insets(20));

        // Configurations For The Scene
        FrontEndUtil.closeNode(removeQuestionParent, removeQuestion);
        Scene scene = new Scene(removeQuestionParent);
        scene.getStylesheets().add("stylesheets/main.css");
        removeQuestion.setScene(scene);
    }

    private void deleteRow(GridPane grid, final int row) {
        Set<Node> deleteNodes = new HashSet<>();
        for (Node child : grid.getChildren()) {
            // get index from child
            Integer rowIndex = GridPane.getRowIndex(child);

            // handle null values for index=0
            int r = rowIndex == null ? 0 : rowIndex;

            if (r > row) {
                // decrement rows for rows after the deleted row
                GridPane.setRowIndex(child, r-1);
                System.out.println(child.getClass().toString() + " was removed from row " + String.valueOf(rowIndex) + "to row " + String.valueOf(GridPane.getRowIndex(child)));
            } else if (r == row) {
                // collect matching rows for deletion
                deleteNodes.add(child);
            }
        }

        // remove nodes from row
        grid.getChildren().removeAll(deleteNodes);
    }

    /**
     *
     * @param addTopic
     */
    private void createSceneForAddTopic(Stage addTopic) {
        GridPane addTopicParent = new GridPane();
        Label searchTerm = addTextLabels(addTopicParent, "Topic Name", FrontEndUtil.black, FrontEndUtil.smallFont, "", 0, 0);
        searchTerm.setAlignment(Pos.TOP_CENTER);

        TextField newTopicName = new TextField();
        addTopicParent.add(newTopicName, 1, 0);
        newTopicName.setPromptText("Add: Topic Name");
        newTopicName.setMaxWidth(400);

        Button submitButton = createButton(addTopicParent, "Submit And Start Adding Questions", "center", 1, 1, Optional.empty());
        Label warningText = addTextLabels(addTopicParent, "", FrontEndUtil.black, FrontEndUtil.smallFont, "", 2, 1);

        newTopicName.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (submitButton.isDisabled()) {
                    submitButton.setDisable(false);
                    warningText.setText("");
                }

                if (back.topicExists(newTopicName.getText())) {
                    submitButton.setDisable(true);
                    warningText.setText("A Topic With Such Name Already Exists");
                }
            }
        });

        // Customize Grid Pane
        addTopicParent.setVgap(20);
        addTopicParent.setHgap(20);
        addTopicParent.setPadding(new Insets(50));

        // Submit Button Config
        submitButton.setOnAction(e -> {
            back.addTopic(newTopicName.getText());
            addTopic.close();
            currentTopic = new Topic(newTopicName.getText());
            addQuestionPanel(true);
        });

        newTopicName.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER && !submitButton.isDisabled()){
                back.addTopic(newTopicName.getText());
                addTopic.close();
                currentTopic = new Topic(newTopicName.getText());
                addQuestionPanel(true);
            }
        });

        // Configurations For The Scene
        FrontEndUtil.closeNode(addTopicParent, addATopic);
        Scene scene = new Scene(addTopicParent);
        scene.getStylesheets().add("stylesheets/main.css");
        addTopic.setScene(scene);
    }

    /**
     *
     * @param changeTopic
     */
    private void createSceneForChangeTopic(Stage changeTopic) {
        BorderPane mainParent = new BorderPane();
        GridPane topicParent = new GridPane();
        GridPane topicChild = new GridPane();

        topicParent.setVgap(10);
        mainParent.setCenter(topicParent);

        // Options menu
        HBox parentTopChild = new HBox();
        parentTopChild.setSpacing(20);
        parentTopChild.setPadding(new Insets(5, 5, 5, 5)); // add padding
        createChangeTopicMenu(parentTopChild, topicChild);
        mainParent.setTop(parentTopChild);

        Label searchTerm = addTextLabels(topicParent, "Search For A Topic", FrontEndUtil.black, FrontEndUtil.largeFont, "", 0, 0);
        searchTerm.setAlignment(Pos.BASELINE_CENTER);

        // Scroll Pane Config
        ScrollPane scrollPane = new ScrollPane(topicParent);
        scrollPane.setPrefSize(600, 200);
        scrollPane.setContent(topicParent);
        scrollPane.setFitToHeight(true);
        scrollPane.setPannable(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        // Text Field
        TextField searchField = new TextField();
        topicParent.add(searchField, 0, 1);
        searchField.setPromptText("Search: Topic Name");
        searchField.setMinWidth(50);
        searchField.setPrefWidth(50);
        searchField.setMaxWidth(400);

        topicChild.setVgap(10);
        topicParent.add(topicChild, 0, 2);
        searchField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                searchField.setPrefWidth(searchField.getText().length() * 6); // why 6? Totally trial number.

                FrontEndUtil.removeEverythingFromGridPane(topicChild);
                if (searchField.getText().length() > 0) {
                    Set<Topic> topicSet = back.searchTopic(searchField.getText());
                    int counterColumn = 0;
                    int counterRow = 0;
                    for (Topic t : topicSet) {
                        addHyperlink(topicChild, "topic" + t.name, "", counterColumn % 3, counterRow, Optional.empty(), Optional.empty());
                        counterColumn++;
                        if (counterColumn % 3 == 0) counterRow++;
                    }
                }
            }
        });

        // Configurations For The Scene
        FrontEndUtil.closeNode(mainParent, changeTopic);
        Scene scene = new Scene(mainParent);
        scene.getStylesheets().add("stylesheets/main.css");
        changeTopic.setScene(scene);
    }

    private void createChangeTopicMenu(HBox parentTopChild, GridPane topicChild) {
        Button allQuestionsButton = createButton(parentTopChild, "List All Topics", "right",  0,0, Optional.empty());
        Button randomButton = createButton(parentTopChild, "Activate A Random Topic", "right",  0,0, Optional.empty());

        allQuestionsButton.setOnAction(e -> {
            int counterColumn = 0;
            int counterRow = 0;
            for (Topic t : back.topicList) {
                addHyperlink(topicChild, "topic" + t.name, "", counterColumn % 3, counterRow, Optional.empty(), Optional.empty());
                counterColumn++;
                if (counterColumn % 3 == 0) counterRow++;
            }
        });

        randomButton.setOnAction(e -> {
           Topic t = back.getARandomTopic();
           currentTopic = t;
           mainText.setText("Get Questions From " + FrontEndUtil.replaceUnderScoreWithWhiteSpace(currentTopic.name));
           changeTheTopic.close();
        });
    }

    /**
     *
     * @param stage
     */
    public void addDesktopIcon(Stage stage) {
        stage.getIcons().add(new Image("image\\logo.png"));
    }

    // Runs After The Application Is Stopped
    @Override
    public void stop() throws Exception {
        super.stop();
    }

    public static void fillQuestions() {
        back.addQuestion(new Question("Capital of Norway?", "Oslo",
                "easy", new Topic("Geography")));
        back.addQuestion(new Question("What is the nickname of Texas?", "The Lone Star state",
                "medium", new Topic("Geography")));
        back.addQuestion(new Question("How many bones are there in the human body?", "206",
                "medium", new Topic("Anatomy")));
        back.addQuestion(new Question("Who discovered the Cell?", "Robert Hooke",
                "medium", new Topic("Biology")));
        back.addQuestion(new Question("What does HB mean in biological terms?", "Hemoglobin",
                "medium", new Topic("Biology")));
        back.addQuestion(new Question("How many bones are there in the human body?", "206",
                "medium", new Topic("Anatomy")));
        back.addQuestion(new Question("What word did Alexander Bell propose to use as a phone call greeting?", "Ahoy",
                "medium", new Topic("History")));
        back.addQuestion(new Question("What is the earliest surviving system of laws", "Code of Hammurabi",
                "hard", new Topic("History")));
        back.addQuestion(new Question("What was the last battle of the Napoleonic Wars?", "Battle of Wavre",
                "hard", new Topic("History")));
        back.addQuestion(new Question("What was the first city to reach a population of one million?", "Rome",
                "hard", new Topic("History")));
        back.addQuestion(new Question("What famous general was once attacked by rabbits?", "Napoleon",
                "hard", new Topic("History")));
        back.addQuestion(new Question("Who was the first U.S. President to be impeached?", "Andrew Johnson",
                "medium", new Topic("History")));
        back.addQuestion(new Question("In what city did American colonists famously dress as Native Americans and dump an entire shipment of East India Company tea into the harbor?",
                "Boston", "easy", new Topic("History")));
        back.addQuestion(new Question("What does HTML stand for?", "Hypertext Markup Language",
                "medium", new Topic("Tech")));
        back.addQuestion(new Question("When was the Bitcoin launched?", "2009",
                "hard", new Topic("Tech")));
        back.addQuestion(new Question("What does MSN stand for?", "Microsoft Network",
                "easy", new Topic("Tech")));
        back.addQuestion(new Question("Where was Skype developed?", "Estonia",
                "hard", new Topic("Tech")));
        back.addQuestion(new Question("Who is googled more than Jesus?", "Justin Bieber",
                "medium", new Topic("Tech")));

        back.addTopic(("Tech"));
        back.addTopic(("History"));
        back.addTopic(("Anatomy"));
        back.addTopic(("Biology"));
        back.addTopic(("Geography"));
        back.addTopic(("sdfjkhdjkfh"));
        back.addTopic(("jksahkjhdjksh_jsdhskjhd"));
        back.addTopic(("shjkdshdjshds"));
        back.addTopic(("jkhjkhjkhjkh"));
        back.addTopic(("jkhjkhkjhjkh"));
        back.addTopic(("jkhjkhjkhjkhjkh"));
        back.addTopic(("jkhdsjkhcsdjkh"));
        back.addTopic(("mnbsmnbsn"));
        back.addTopic(("kjhnxbcnmcb"));
        back.addTopic(("nmbmnbmnbmnb"));
        back.addTopic(("bnmcfg"));
        back.addTopic(("tywfetwgdsgadhvsagdhcs"));
        back.addTopic(("uyuiyuiyuioyuioyiuoy"));
        back.addTopic(("zxcxzczxcxzcxzxczcxxcz"));
        back.addTopic(("vcvcvcvcvcvvcvcvcvcvccv"));
        back.addTopic(("bnnbnbbnbnnmmnmnhhg"));
        back.addTopic(("luiliuliuliuilil"));
        back.addTopic(("ppppoouyuterrer"));
        back.addTopic(("dsvddfhdsgj"));
        back.addTopic(("kljjhljfhfj"));
        back.addTopic(("yuuyuiyuiyiuyuiyuiy"));
        back.addTopic(("gjghghjvbnvhgfrert"));
        back.addTopic(("ghvffhvfvhgfvhgfvhgfv"));
        back.addTopic(("hvghfvhgfhgvhfvhfv"));
        back.addTopic(("vghfvhgfvhgfvghvf"));
        back.addTopic(("vghfvrterdr"));
        back.addTopic(("vgfghfvghfvhgvh"));
        back.addTopic(("vhgfhvhgvhfvhgvhgv"));
        back.addTopic(("vcvcvcvcvcvvcvcvcvcvccv"));
        back.addTopic(("bnnbnbbnbnnmmnmnhhg"));
        back.addTopic(("luiliuliuliuilil"));
        back.addTopic(("ppppoouyuterrer"));
        back.addTopic(("dsvddfhdsgj"));
        back.addTopic(("kljjhljfhfj"));
        back.addTopic(("yuuyuiyuiyiuyuiyuiy"));
        back.addTopic(("gjghghjvbnvhgfrert"));
        back.addTopic(("ghvffhvfvhgfvhgfvhgfv"));
        back.addTopic(("hvghfvhgfhgvhfvhfv"));
        back.addTopic(("vghfvhgfvhgfvghvf"));
        back.addTopic(("vghfvrterdr"));
        back.addTopic(("vgfghfvghfvhgvh"));
        back.addTopic(("vhgfhvhgvhfvhgvhgv"));
    }
}