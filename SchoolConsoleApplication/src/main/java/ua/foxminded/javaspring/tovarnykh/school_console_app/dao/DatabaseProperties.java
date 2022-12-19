package ua.foxminded.javaspring.tovarnykh.school_console_app.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

/**
* @author Victor Tovarnykh
* @version 0.15.0
* @since 0.1.0
*/
public class DatabaseProperties {

    private static final Properties DATABASE_PROPERTIES = new Properties();

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

    static {
        try {
            String path = Thread.currentThread().getContextClassLoader().getResource("databaseProperties.xml")
                    .getPath();
            DATABASE_PROPERTIES.loadFromXML(new FileInputStream(path));
        } catch (InvalidPropertiesFormatException e) {
            System.out.println("There is a problem with a property format wich trying to recieve.");
        } catch (IOException e) {
            System.out.println("File with Database properties was not found.");
        }
    }

    private DatabaseProperties() {

    }

}