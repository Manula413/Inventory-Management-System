/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.swing.JOptionPane;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Manula
 */
public class EmployeeManager {

    private EmployeeDAO employeeDAO;

    public EmployeeManager() {
        this.employeeDAO = new EmployeeDAO(); // Initialize the EmployeeDAO
    }

    public void saveEmployee(String firstName, String lastName, String email, String role, String password, String address, String dateOfBirth, String startDate, boolean isActive) throws ParseException {
        // Create an Employee object with the provided details
        Employee employee = new Employee(firstName, lastName, email, role, password, address, dateOfBirth, startDate, isActive);

        // Save the employee using the EmployeeDAO
        employeeDAO.saveEmployee(employee);
    }

    public List<Employee> getAllEmployees() throws ParseException {
        return employeeDAO.getAllEmployees();
    }
    // Other methods for employee management can be added here
}
