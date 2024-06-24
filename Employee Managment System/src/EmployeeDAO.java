import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Connecting the vscode to database MS Access through JDBC-OBDC
public class EmployeeDAO {
    private Connection conn;

    public EmployeeDAO() {
        try {
            // Initialize the database connection
            conn = DriverManager.getConnection("jdbc:ucanaccess://F:\\Intership of java\\Task 2\\Employee Managment System\\Employees.accdb");
        } catch (SQLException e) {
            System.err.println("Error initializing database connection: " + e.getMessage());
        }
    }

    public void createEmployee(String name, int age, String department, double salary) {
        try {
            // Create a prepared statement to insert a new employee
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO employee(Name, Age, Department, Salary) VALUES (?,?,?,?)");
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.setString(3, department);
            pstmt.setDouble(4, salary);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error creating employee: " + e.getMessage());
        }
    }

    public List<Employee> readEmployees() {
        List<Employee> employees = new ArrayList<>();
        try {
            // Create a prepared statement to select all employees
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM employee");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setName(rs.getString("Name"));
                employee.setAge(rs.getInt("Age"));
                employee.setDepartment(rs.getString("Department"));
                employee.setSalary(rs.getDouble("Salary"));
                employees.add(employee);
            }
        } catch (SQLException e) {
            System.err.println("Error reading employees: " + e.getMessage());
        }
        return employees;
    }

    public void updateEmployee(String name, int age, String department, double salary) {
        try {
            // Create a prepared statement to update an employee
            PreparedStatement pstmt = conn.prepareStatement("UPDATE employee SET Age = ?, Department = ?, Salary = ? WHERE Name = ?");
            pstmt.setInt(1, age);
            pstmt.setString(2, department);
            pstmt.setDouble(3, salary);
            pstmt.setString(4, name);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating employee: " + e.getMessage());
        }
    }

    public void deleteEmployee(String name) {
        try {
            // Create a prepared statement to delete an employee
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM employee WHERE Name = ?");
            pstmt.setString(1, name);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting employee: " + e.getMessage());
        }
    }
}