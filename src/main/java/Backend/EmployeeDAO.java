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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    // Method to save an employee record to the database
    public void saveEmployee(Employee employee) {
        String sql = "INSERT INTO employee (first_name, last_name, email, role, password, address, dob, start_date, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
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

    // Other methods for updating, deleting, and retrieving individual employee records could be added here
}
