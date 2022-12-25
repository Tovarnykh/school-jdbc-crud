package ua.foxminded.javaspring.tovarnykh.school_console_app.commands;

import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;

import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.statements.SelectStudents;
import ua.foxminded.javaspring.tovarnykh.school_console_app.main.ConsolePrinter;

/**
 *
 * @author Victor Tovarnykh
 * @version 0.15.0
 * @since 0.1.0
 */
public class FindStudents implements Command {

    @Override
    public void execute() throws SQLException {
        System.out.print("""
                ╔══════════════════════════════════════════╗
                ║Insert a course name to find it`s students║
                ╟──────────────────────────────────────────╢
                 in:
                 """);
        
        String courseName = ConsolePrinter.readLine();
        StringBuilder resultList = new StringBuilder();
        CachedRowSet cachedSet = SelectStudents.select(courseName);
        
        resultList.append(ConsolePrinter.SEPARATOR);
        resultList.append(String.format(" %25s %s %n", "Students of", courseName));
        resultList.append(ConsolePrinter.SEPARATOR);
        while (cachedSet.next()) {
            resultList.append(String.format(" %28s%n", cachedSet.getString("student")));
        }
        System.out.println(resultList.toString());
        ConsolePrinter.closeWindow();
    }

}