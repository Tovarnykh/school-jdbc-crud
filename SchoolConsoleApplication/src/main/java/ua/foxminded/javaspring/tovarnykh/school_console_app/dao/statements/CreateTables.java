package ua.foxminded.javaspring.tovarnykh.school_console_app.dao.statements;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.jdbc.ScriptRunner;

import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.aspects.ConnectionAspect;

public class CreateTables {

    private CreateTables() {

    }

    public static void create(List<String> executableScripts) {
        executableScripts.forEach(script -> {
            try (Connection connection = ConnectionAspect.getConnection();
                    InputStream inputStream = CreateTables.class.getResourceAsStream(script);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                ScriptRunner scriptRunner = new ScriptRunner(connection);

                scriptRunner.setLogWriter(null);
                scriptRunner.runScript(reader);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                System.out.println("Such script was not found.");
            } catch (IOException e) {
                System.out.println("Cant open Reader class.");
            }
        });
    }
}
