import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application{
    private TextField textfield = new TextField();
    private long num1 = 0;
    private String op = "";
    private boolean start = true;

    @Override
    public void start(Stage stage) throws Exception{
        textfield.setFont(Font.font(20));
        textfield.setPrefHeight(50);
        textfield.setAlignment(Pos.CENTER_RIGHT);
        textfield.setEditable(false);

        StackPane stackPane = new StackPane();
        stackPane.setPadding(new Insets(10));
        stackPane.getChildren().add(textfield);

        TilePane tilePane = new TilePane();
        tilePane.setVgap(10);
        tilePane.setHgap(10);
        tilePane.setAlignment(Pos.TOP_CENTER);
        tilePane.getChildren().addAll(
            createButtonForNumber("7"),
            createButtonForNumber("8"),
            createButtonForNumber("9"),
            createButtonForOperator("/"),

            createButtonForNumber("4"),
            createButtonForNumber("5"),
            createButtonForNumber("6"),
            createButtonForOperator("x"),

            createButtonForNumber("1"),
            createButtonForNumber("2"),
            createButtonForNumber("3"),
            createButtonForOperator("-"),

            createButtonForNumber("0"),
            createButtonForClearButton("C"),
            createButtonForOperator("="),
            createButtonForOperator("+")
        );

        BorderPane root = new BorderPane();
        root.setTop(stackPane);
        root.setCenter(tilePane);
        Scene scene = new Scene(root,250,310);
        stage.setResizable(false);
        stage.setTitle("CALCULATOR");
        stage.setScene(scene);
        stage.show();
    }

    // make Functions for making button
    private Button createButtonForNumber (String ch){
        Button button = new Button(ch);
        button.setFont(Font.font(18));
        button.setPrefSize(50, 50);
        button.setOnAction(this::processNumber);
        return button;
    }

    // make functions for making Opearator button
    private Button createButtonForOperator(String ch){
        Button button = new Button(ch);
        button.setFont(Font.font(18));
        button.setPrefSize(50, 50);
        button.setOnAction(this::processOperator);
        return button;
    }

    // make function for making Clear button
    private Button createButtonForClearButton(String ch){
        Button button = new Button(ch);
        button.setFont(Font.font(18));
        button.setPrefSize(50, 50);
        button.setOnAction(e->{
            textfield.setText("");
            op = "";
            start = true;
        });
        return button;
    }

    // Function for taking input of number
    private void processNumber(ActionEvent e){
        if(start){
            textfield.setText("");
            start = false;
        }
        String value = ((Button)e.getSource()).getText();
        textfield.setText(textfield.getText() + value);
    }

    // Function for applied action on operator
    private void processOperator(ActionEvent e){
        String value = ((Button)e.getSource()).getText();
        if(!value.equals("=")){
            if(!op.isEmpty()){
                return;
            }
            num1 = Long.parseLong(textfield.getText());
            op = value;
            textfield.setText("");
        } else {
            if(op.isEmpty()){
                return;
            }
            long num2 = Long.parseLong(textfield.getText());
            float result = Calculate(num1, num2, op);
            textfield.setText(String.valueOf(result));
            start = true;
            op = "";
        }
    }

    //making calculate function to perform calculation
        private float Calculate(long num1, long num2, String Opearator){
            switch (Opearator) {
                case "+": return num1 + num2;
                case "-": return num1 - num2;
                case "x": return num1 * num2;
                case "/": 
                    if(num2 == 0){
                        return 0;
                    } else {
                        return (float)num1/num2;
                    }
                default: return 0;
                
            }
        }

    public static void main(String args[]){
        launch();
    }
}