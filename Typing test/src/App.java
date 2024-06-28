import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.time.LocalTime;
import java.util.Random;

public class App extends Application {

    static String[] sentences = {
            "the Quick brown fox jump over the lazy dog",
            "Pack my box with five dozen liquor jugs",
            "How vexingly quick waltzing zebras jump",
            "Bright vixens jump; dozy fowl quack"
    };

    private double startTime;
    private Stage primaryStage;
    private Scene mainMenuScene; // Save the main menu scene

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        Label titleLabel = new Label("Typing Speed Game");
        titleLabel.setStyle("-fx-font-size: 36; -fx-font-weight: bold; -fx-text-fill: #A6A6A6;");

        Button startButton = new Button("Start");
        startButton.setPrefSize(100, 40);
        startButton.setFont(Font.font("Designer", FontWeight.BOLD, 16));
        startButton.setTextFill(Color.rgb(129, 129, 129));
        startButton.setOnAction(e -> {
            startGame1();
        });

        Label titleLabel2 = new Label("Air typer game");
        titleLabel2.setStyle("-fx-font-size: 36; -fx-font-weight: bold; -fx-text-fill: #A6A6A6;");

        Button startButton2 = new Button("Start");
        startButton2.setPrefSize(100, 40);
        startButton2.setFont(Font.font("Designer", FontWeight.BOLD, 16));
        startButton2.setTextFill(Color.rgb(129, 129, 129));
        startButton2.setOnAction(e -> {
            FallingTextAnimation fallingTextAnimation = new FallingTextAnimation();
            fallingTextAnimation.show(); // Show the FallingTextAnimation stage
        });

        VBox root = new VBox(20);
        root.getChildren().addAll(titleLabel, startButton, titleLabel2, startButton2);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        root.setBackground(new Background(new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY)));

        mainMenuScene = new Scene(root, 600, 400); // Save the main menu scene
        primaryStage.setScene(mainMenuScene);
        primaryStage.show();
    }

    private void startGame1() {
        Random random = new Random();
        String sentence = sentences[random.nextInt(sentences.length)];

        Label sentenceLabel = new Label(sentence);
        sentenceLabel.setStyle("-fx-font-size: 24; -fx-font-weight: bold; -fx-text-fill: #737373;");

        TextField textField = new TextField();
        textField.setStyle("-fx-font-size: 24; -fx-padding: 10; -fx-border-color: #A6A6A6; -fx-border-width: 2; -fx-border-radius: 5;");
        textField.setPrefWidth(20);
        textField.setOnAction(e -> {
            calculateWPM(sentence, textField.getText());
        });

        Button backButton = new Button("Back to Menu");
        backButton.setOnAction(e -> primaryStage.setScene(mainMenuScene)); // Set scene back to main menu

        startTime = LocalTime.now().toNanoOfDay(); // Store the start time

        VBox root = new VBox(20);
        root.getChildren().addAll(sentenceLabel, textField, backButton);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        root.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

        Scene gameScene = new Scene(root, 600, 400);
        primaryStage.setScene(gameScene);
    }

    private void calculateWPM(String sentence, String typedSentence) {
        double endTime = LocalTime.now().toNanoOfDay();
        double timeTaken = (endTime - startTime) / 1000000000.0;

        int numChars = sentence.length();
        int numCorrectChars = 0;

        for (int i = 0; i < Math.min(numChars, typedSentence.length()); i++) {
            if (sentence.charAt(i) == typedSentence.charAt(i)) {
                numCorrectChars++;
            }
        }

        int numWords = sentence.split("\\s+").length;
        double WPM = (numWords / timeTaken) * 60;

        int accuracy = (int) (((double) numCorrectChars / numChars) * 100);

        double CPM = (numCorrectChars / timeTaken) * 60; // Calculate CPM

        Label wpmLabel = new Label("Your WPM is " + String.format("%.2f", WPM) + "!");
        wpmLabel.setStyle("-fx-font-size: 24; -fx-font-weight: bold; -fx-text-fill: #007bff;");

        Label cpmLabel = new Label("Your CPM is " + String.format("%.2f", CPM) + "!");
        cpmLabel.setStyle("-fx-font-size: 24; -fx-font-weight: bold; -fx-text-fill: #007bff;");

        Label accuracyLabel = new Label("Your accuracy is " + accuracy + "%");
        accuracyLabel.setStyle("-fx-font-size: 24; -fx-font-weight: bold; -fx-text-fill: #007bff;");

        Button retryButton = new Button("Retry");
        retryButton.setOnAction(event -> startGame1());

        Button backButton = new Button("Back to Menu");
        backButton.setOnAction(event -> primaryStage.setScene(mainMenuScene)); // Set scene back to main menu

        VBox resultsBox = new VBox(20);
        resultsBox.getChildren().addAll(wpmLabel, cpmLabel, accuracyLabel, retryButton, backButton);
        resultsBox.setAlignment(Pos.CENTER);
        resultsBox.setPadding(new Insets(20));
        resultsBox.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

        Scene resultsScene = new Scene(resultsBox, 400, 300);
        primaryStage.setScene(resultsScene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
