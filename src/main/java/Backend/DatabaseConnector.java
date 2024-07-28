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
 * DatabaseConnector is responsible for creating and managing the database
 * connection. Configuration details are loaded from a properties file.
 */
public class DatabaseConnector {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseConnector.class);
    private static Properties properties = new Properties();

    static {
        try (InputStream input = DatabaseConnector.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (input == null) {
                logger.error("Sorry, unable to find db.properties");
            } else {
                logger.info("db.properties file found.");
                properties.load(input);
                Class.forName(properties.getProperty("db.driver"));
            }
        } catch (Exception e) {
            logger.error("Failed to load database properties", e);
        }
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            String url = properties.getProperty("db.url");
            String user = properties.getProperty("db.user");
            String password = properties.getProperty("db.password");
            connection = DriverManager.getConnection(url, user, password);
            logger.info("Connection Established");
        } catch (SQLException e) {
            logger.error("Connection Failed", e);
        }
        return connection;
    }
}
