package ua.foxminded.javaspring.tovarnykh.school_console_app.dao.aspects;

import java.io.IOException;
import java.io.InputStream;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

/**
* @author Victor Tovarnykh
* @version 0.15.0
* @since 0.1.0
*/
public class DatabaseProperties {

    private static final Properties DATABASE_PROPERTIES = new Properties();
    
    private DatabaseProperties() {

    }

    /**
     * Method name: getDriver
     * @return (String) Received driver.
     *
     *                      Inside the function: Get driver from property file.
     */
    public static String getDriver() {
        return DATABASE_PROPERTIES.getProperty("driver");
    }

    /**
     * Method name: getUserName
     * @return (String) Received username.
     *
     *                      Inside the function: Get username from property file.
     */
    public static String getUserName() {
        return DATABASE_PROPERTIES.getProperty("userName");
    }
    
    /**
     * Method name: getPassword
     * @return (String) Received password.
     *
     *                      Inside the function: Get password from property file.
     */
    public static String getPassword() {
        return DATABASE_PROPERTIES.getProperty("password");
    }
    
    public static void readPropertyFile(String fileName) {
        try {
            InputStream inputStream = DatabaseProperties.class.getClassLoader().getResourceAsStream(fileName);
            DATABASE_PROPERTIES.load(inputStream);
        } catch (InvalidPropertiesFormatException e) {
            System.out.println("Problem with a property variable trying to receive.");
        } catch (IOException e) {
            System.out.println("File with Database properties was not found.");
        }
    }

}