/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * @author Manula
 */



/**
 * DatabaseConnector is responsible for creating and managing the database connection.
 * Configuration details are loaded from a properties file.
 */
public class DatabaseConnector {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseConnector.class);
    private static Connection connection = null;

    static {
        boolean isInitialized = false;
        try (InputStream input = DatabaseConnector.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (input == null) {
                logger.error("Sorry, unable to find db.properties");
            } else {
                logger.info("db.properties file found.");
                Properties prop = new Properties();
                prop.load(input);

                String driver = prop.getProperty("db.driver");
                String url = prop.getProperty("db.url");
                String user = prop.getProperty("db.user");
                String password = prop.getProperty("db.password");

                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, password);
                logger.info("Connection Established");
                isInitialized = true;
            }
        } catch (Exception e) {
            logger.error("Connection Failed", e);
        }

        if (!isInitialized) {
            connection = null;
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}