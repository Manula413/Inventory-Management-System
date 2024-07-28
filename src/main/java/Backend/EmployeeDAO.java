/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

/**
 *
 * @author Manula
 */
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    // Method to save an employee record to the database
    public void saveEmployee(Employee employee) {
        String sql = "INSERT INTO employee (first_name, last_name, email, role, password, address, dob, start_date, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnector.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, employee.getFirstName());
            pstmt.setString(2, employee.getLastName());
            pstmt.setString(3, employee.getEmail());
            pstmt.setString(4, employee.getRole());
            pstmt.setString(5, employee.getPassword());
            pstmt.setString(6, employee.getAddress());
            pstmt.setDate(7, new java.sql.Date(employee.getDateOfBirth().getTime()));
            pstmt.setDate(8, new java.sql.Date(employee.getStartDate().getTime()));
            pstmt.setString(9, employee.isActive() ? "Active" : "Inactive");

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Employee saved successfully.");
            } else {
                System.out.println("Failed to save employee.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("An error occurred while saving employee.");
        }
    }

    // Method to retrieve all employees
    public List<Employee> getAllEmployees() throws ParseException {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT first_name, last_name, email, role, start_date, status FROM employee";
        try (Connection conn = DatabaseConnector.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                String role = rs.getString("role");
                Date startDate = rs.getDate("start_date");
                String status = rs.getString("status");

                Employee employee = new Employee(firstName, lastName, email, role, startDate, status);
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }
}
