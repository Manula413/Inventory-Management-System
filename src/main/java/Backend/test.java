/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Backend;
import java.sql.Connection;

/**
 *
 * @author Manula
 */
public class test {

    /**
     * @param args the command line arguments
     */
   public static void main(String[] args) {
        Connection connection = DatabaseConnector.getConnection();
        if (connection != null) {
            System.out.println("Connection was successful!");
        } else {
            System.out.println("Failed to establish a connection.");
        }
    }
}
