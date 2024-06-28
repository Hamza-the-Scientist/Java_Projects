import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;

public class FallingTextAnimation extends Stage {

    private TextField textField;
    private Label resultLabel;
    private Pane root;
    private Random random;
    private String[] words;
    private boolean gameOver = false;
    private Rectangle border;
    private Timeline timeline;
    private Stage popupStage; // Declare popupStage as a class variable
    private Text fallingText; // Declare fallingText as a class variable
    private TranslateTransition transition; // Declare transition as a class variable
    private int score = 0; // Score variable

    public FallingTextAnimation() {
        this.setTitle("Falling Text Animation Game");
        setupGameWindow();
    }

    private void setupGameWindow() {
        // Initialize game window components
        root = new Pane();
        root.setPrefSize(400, 400);

        // Add red border at the bottom
        border = new Rectangle(0, 390, 400, 10);
        border.setFill(Color.RED);
        root.getChildren().add(border);

        // Add text field and label at the bottom
        HBox inputBox = new HBox(10);
        inputBox.setAlignment(Pos.CENTER);
        inputBox.setPadding(new Insets(10));
        textField = new TextField();
        textField.setPromptText("Type the falling word");
        textField.setPrefWidth(200); // Set text field width
        resultLabel = new Label("Score: " + score); // Initialize score label
        inputBox.getChildren().addAll(textField, resultLabel);
        VBox vbox = new VBox();
        vbox.getChildren().add(root);
        vbox.getChildren().add(inputBox);
        vbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vbox);
        this.setScene(scene);

        // Initialize random word generator
        words = new String[]{"apple", "banana", "cherry", "date", "elderberry", "orange", "grapes", "guava", "coconut", "after", "forward", "cloth"};
        random = new Random();
        gameOver = false;

        // Start the game
        startNextWord();
    }

    private void startNextWord() {
        if (gameOver) {
            return;
        }

        // Generate a random word
        String fallingWord = words[random.nextInt(words.length)];

        // Create a Text node for the falling word
        fallingText = new Text(fallingWord);
        fallingText.setX(50 + random.nextInt(300));
        fallingText.setY(0);

        // Create a TranslateTransition for the falling word
        transition = new TranslateTransition(Duration.millis(5000), fallingText);
        transition.setFromY(0);
        transition.setToY(390);
        transition.setCycleCount(1);

        // Initialize the Timeline to check the position of the falling word
        timeline = new Timeline(new KeyFrame(Duration.millis(100), event -> {
            if (fallingText.getBoundsInParent().getMaxY() >= 390) {
                if (!textField.getText().equals(fallingWord)) {
                    gameOver = true;
                    showGameOverPopup();
                    timeline.stop();
                    transition.stop();
                }
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        transition.setOnFinished(event -> {
            if (!gameOver) {
                root.getChildren().remove(fallingText);
                startNextWord();
            }
        });

        transition.play();

        root.getChildren().add(fallingText);

        // Add event handler to text field to submit answer when Enter is pressed
        textField.setOnAction(event -> {
            if (textField.getText().equals(fallingText.getText())) {
                textField.clear();
                root.getChildren().remove(fallingText);
                timeline.stop();
                transition.stop();
                score++; // Increment score on correct answer
                resultLabel.setText("Score: " + score); // Update score label
                startNextWord(); // Start next word
            } else {
                textField.clear();
            }
        });
    }

    private void showGameOverPopup() {
        popupStage = new Stage();
        popupStage.initOwner(this.getScene().getWindow());
    
        VBox popupVbox = new VBox(10);
        popupVbox.setAlignment(Pos.CENTER); // Center align the VBox
        popupVbox.setPadding(new Insets(10));
    
        Label gameOverLabel = new Label("Game Over!");
        gameOverLabel.setStyle("-fx-font-size: 24; -fx-font-weight: bold;");
    
        Label scoreLabel = new Label("Your score: " + score);
        scoreLabel.setStyle("-fx-font-size: 18;");
    
        Button retryButton = new Button("Retry");
        retryButton.setOnAction(event -> {
            gameOver = false;
            score = 0; // Reset score
            resultLabel.setText("Score: " + score); // Update score label
            textField.clear();
            root.getChildren().clear();
            root.getChildren().add(border); // Add the red border back
            popupStage.close();
            startNextWord();
        });
    
        popupVbox.getChildren().addAll(gameOverLabel, scoreLabel, retryButton);
    
        Scene popupScene = new Scene(popupVbox, 250, 150);
        popupStage.setScene(popupScene);
        popupStage.show();
    }    
}
