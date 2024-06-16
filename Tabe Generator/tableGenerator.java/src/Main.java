import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {

        // Making all Objects of TextField For Taking input
        TextField table1Field = new TextField();
        table1Field.setPromptText("Enter Table 1");        // Enter Palceholder in the TextField
        TextField table2Field = new TextField();
        table2Field.setPromptText("Enter Table 2");        // Enter Palceholder in the TextField
        TextField table3Field = new TextField();
        table3Field.setPromptText("Enter Table 3");         // Enter Palceholder in the TextField
        TextField startField = new TextField();
        startField.setPromptText("Enter Starting Point");   // Enter Palceholder in the TextField
        TextField endField = new TextField();
        endField.setPromptText("Enter Ending Point");       // Enter Palceholder in the TextField
        Button printButton = new Button("Print Table");     // Making Button Object for print the table
        TextArea consoleArea = new TextArea();              // Assign the console area for tables

        //Heading
        Label title = new Label("Table Generator");                 
        title.setFont(Font.font("Nexa Bold",FontWeight.BOLD,20));
        title.setTextFill(Color.GREY);

        VBox inputBox = new VBox(10);
        inputBox.getChildren().addAll(table1Field, table2Field, table3Field, startField, endField,printButton);
        inputBox.setStyle("-fx-padding: 10;" );

        BorderPane root = new BorderPane();
        root.setTop(title);
        root.setAlignment(title, Pos.CENTER);
        root.setLeft(inputBox);
        root.setCenter(consoleArea);

        printButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Input values store in to variable for further use
                int table1 = Integer.parseInt(table1Field.getText());
                int table2 = Integer.parseInt(table2Field.getText());
                int table3 = Integer.parseInt(table3Field.getText());
                int start = Integer.parseInt(startField.getText());
                int end = Integer.parseInt(endField.getText());


                //Running loop for print 3 table in the row
                for (int i = start; i <= end; i++) {
                    System.out.print(table1 + " x " + i + " = " + table1 * -i + "\t" + table2 + " x " + i + " = " + table2 * -i + "\t" + table3 + " x " + i + " = " + table3 * -i + "\n");

                    consoleArea.appendText(String.format("%d x %-2d = %-5d    |  %d x %-2d = %-5d    |  %d x %-2d = %-5d\n", table1, i, table1 * -i, table2, i, table2 * -i, table3, i, table3 * -i));
                }
            }
        });

        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.setTitle("Table Generator");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}