/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.sql.Connection;
import java.sql.SQLException;


/**
 *
 * @author Manula
 */
public class DatabaseManager {

    private static Connection connection;

    // Initialize the database connection when the application starts
    static {
        connection = DatabaseConnector.getConnection();
    }

    // Method to get the database connection
    public static Connection getConnection() {
        return connection;
    }

    // Close the database connection when the application shuts down
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
