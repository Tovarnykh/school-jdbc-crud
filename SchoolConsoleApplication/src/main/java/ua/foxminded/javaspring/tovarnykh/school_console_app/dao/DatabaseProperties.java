package ua.foxminded.javaspring.tovarnykh.school_console_app.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

public class DatabaseProperties {

    private static final Properties DATABASE_PROPERTIES = new Properties();
    
    public static int querryNumber = 0;

    public static String getDriver() {
        return DATABASE_PROPERTIES.getProperty("driver");
    }

    public static String getUserName() {
        return DATABASE_PROPERTIES.getProperty("userName");
    }

    public static String getPassword() {
        return DATABASE_PROPERTIES.getProperty("password");
    }

    static {
        try {
            String path = Thread.currentThread().getContextClassLoader().getResource("").getPath()
                    + "databaseProperties.xml";
            DATABASE_PROPERTIES.loadFromXML(new FileInputStream(path));
        } catch (InvalidPropertiesFormatException e) {
            System.out.println("There is a problem with a property format trying to recieve.");
        } catch (IOException e) {
            System.out.println("File with properties of Database was not found.");
        }
    }

    private DatabaseProperties() {

    }

}
