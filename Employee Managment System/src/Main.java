import java.util.List;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
// Employee Managment System Project -- main method and GUI interfance in javafx
public class Main extends Application {
    //Create Global Variable
    private EmployeeDAO employeeDAO = new EmployeeDAO();
    private Label salaryLabel; 

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Employee Management System"); // Set main Heading

        // Create Vbox for creating Main Buttons
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(10));
        root.setBackground(new Background(new BackgroundFill(Color.rgb(150, 75, 0), CornerRadii.EMPTY, Insets.EMPTY)));


        Label labelTitle = new Label("Employee Managemnet System");
        labelTitle.setFont(Font.font("Algerian",FontWeight.BLACK,20));
        labelTitle.setTextFill(Color.WHEAT);

        Label label = new Label("Choose an option:");
        label.setTextFill(Color.WHITESMOKE);
        label.setFont(Font.font("Designer",FontWeight.BOLD,16));
        root.getChildren().addAll(labelTitle,label);

        //Start Creating Button
        Button createButton = new Button("Create Employee");
        createButton.setStyle("-fx-background-color: #D27D2D; -fx-text-fill: white;");
        createButton.setFont(Font.font("Designer",FontWeight.BOLD,12));
        createButton.setOnAction(e -> createEmployee(primaryStage));
        root.getChildren().add(createButton);

        Button readButton = new Button("Read Employees");
        readButton.setStyle("-fx-background-color: #D27D2D; -fx-text-fill: white;");
        readButton.setFont(Font.font("Designer",FontWeight.BOLD,12));
        readButton.setOnAction(e -> readEmployees(primaryStage));
        root.getChildren().add(readButton);

        Button updateButton = new Button("Update Employee");
        updateButton.setStyle("-fx-background-color: #D27D2D; -fx-text-fill: white;");
        updateButton.setFont(Font.font("Designer",FontWeight.BOLD,12));
        updateButton.setOnAction(e -> updateEmployee(primaryStage));
        root.getChildren().add(updateButton);

        Button deleteButton = new Button("Delete Employee");
        deleteButton.setStyle("-fx-background-color: #D27D2D; -fx-text-fill: white;");
        deleteButton.setFont(Font.font("Designer",FontWeight.BOLD,12));
        deleteButton.setOnAction(e -> deleteEmployee(primaryStage));
        root.getChildren().add(deleteButton);

        Button exitButton = new Button("Exit");
        exitButton.setStyle("-fx-background-color: #D27D2D; -fx-text-fill: white;");
        exitButton.setFont(Font.font("Designer",FontWeight.BOLD,12));
        exitButton.setOnAction(e -> System.exit(0));
        root.getChildren().add(exitButton);

        Scene scene = new Scene(root, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    // Function to perform Action
    private void createEmployee(Stage primaryStage) {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));
    
        Label nameLabel = new Label("Enter employee name:");
        gridPane.add(nameLabel, 0, 0);
        TextField nameField = new TextField();
        gridPane.add(nameField, 1, 0);
    
        Label ageLabel = new Label("Enter employee age:");
        gridPane.add(ageLabel, 0, 1);
        TextField ageField = new TextField();
        gridPane.add(ageField, 1, 1);
    
        Label departmentLabel = new Label("Enter employee department:");
        gridPane.add(departmentLabel, 0, 2);
        TextField departmentField = new TextField();
        gridPane.add(departmentField, 1, 2);
    
        Label salaryLabel = new Label("Enter employee salary:");
        gridPane.add(salaryLabel, 0, 3);
        TextField salaryField = new TextField();
        gridPane.add(salaryField, 1, 3);
    
        Button createButton = new Button("Create");
        createButton.setOnAction(e -> {
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String department = departmentField.getText();
            double salary = Double.parseDouble(salaryField.getText());
            employeeDAO.createEmployee(name, age, department, salary);
            Stage createStage = (Stage) createButton.getScene().getWindow();
            createStage.close();
        });
        gridPane.add(createButton, 1, 4);
    
        Scene scene = new Scene(gridPane, 400, 200);
        Stage createStage = new Stage();
        createStage.setScene(scene);
        createStage.show();
    }
    
    private void readEmployees(Stage primaryStage) {
        List<Employee> employees = employeeDAO.readEmployees();
        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(10));

        for (Employee employee : employees) {
            Label label = new Label("Name: " + employee.getName() + "\nAge: " + employee.getAge() + "\nDepartment: " + employee.getDepartment() + "\nSalary: " + employee.getSalary()
             + "\n----------------------------------------------------------------------");
            label.setFont(Font.font("",FontWeight.SEMI_BOLD,12));
            vBox.getChildren().add(label);
        }

        // Create a ScrollPane and set the VBox in its content
        ScrollPane scrollPane = new ScrollPane();  // setting scrollbar if exculdes over the size
        scrollPane.setContent(vBox);
        scrollPane.setFitToWidth(true); 

        // Create a scene and set the ScrollPane in its root
        Scene scene = new Scene(scrollPane, 400, 400);

        // Create a new stage for displaying the employees
        Stage readStage = new Stage();
        readStage.setScene(scene);
        readStage.setTitle("Employee List");
        readStage.show();
    }

    private void updateEmployee(Stage primaryStage) {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));
    
        Label nameLabel = new Label("Enter employee name to update:");
        gridPane.add(nameLabel, 0, 0);
        TextField nameField = new TextField();
        gridPane.add(nameField, 1, 0);
    
        Label ageLabel = new Label("Enter new employee age:");
        gridPane.add(ageLabel, 0, 1);
        TextField ageField = new TextField();
        gridPane.add(ageField, 1, 1);
    
        Label departmentLabel = new Label("Enter new employee department:");
        gridPane.add(departmentLabel, 0, 2);
        TextField departmentField = new TextField();
        gridPane.add(departmentField, 1, 2);
    
        Label salaryLabel = new Label("Enter new employee salary:");
        gridPane.add(salaryLabel, 0, 3);
        TextField salaryField = new TextField();
        gridPane.add(salaryField, 1, 3);
    
        Button updateButton = new Button("Update");
        updateButton.setOnAction(e -> {
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String department = departmentField.getText();
            double salary = Double.parseDouble(salaryField.getText());
            employeeDAO.updateEmployee(name, age, department, salary);
            Stage deleteStage = (Stage) updateButton.getScene().getWindow();
            deleteStage.close();
        });
        gridPane.add(updateButton, 1, 4);
    
        Scene scene = new Scene(gridPane, 400, 200);
        Stage updateStage = new Stage();
        updateStage.setScene(scene);
        updateStage.show();
    }

    private void deleteEmployee(Stage primaryStage) {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));

        Label nameLabel = new Label("Enter employee name to delete:");
        gridPane.add(nameLabel, 0, 0);
        TextField nameField = new TextField();
        gridPane.add(nameField, 1, 0);

        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> {
            String name = nameField.getText();
            employeeDAO.deleteEmployee(name);
            Stage deleteStage = (Stage) deleteButton.getScene().getWindow();
            deleteStage.close();
        });
        gridPane.add(deleteButton, 1, 1);

        Scene scene = new Scene(gridPane, 400, 200);
        Stage deleteStage = new Stage();
        deleteStage.setScene(scene);
        deleteStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}