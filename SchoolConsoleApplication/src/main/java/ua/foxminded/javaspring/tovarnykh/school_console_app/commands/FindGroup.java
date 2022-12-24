package ua.foxminded.javaspring.tovarnykh.school_console_app.commands;

import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;

import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.statements.SelectGroup;
import ua.foxminded.javaspring.tovarnykh.school_console_app.main.ConsolePrinter;

/**
*
* @author Victor Tovarnykh
* @version 0.15.0
* @since 0.1.0
*/
public class FindGroup implements Command {

    @Override
    public void execute() throws SQLException {
            System.out.print("""
                    ╔════════════════════════════════════════╗
                    ║Insert a number of students` to find    ║
                    ║courses.                                ║
                    ╟────────────────────────────────────────╢
                     in:
                     """);
            int amountOfStudents = ConsolePrinter.readNumber();
            StringBuilder resultList = new StringBuilder();
            CachedRowSet cachedSet = SelectGroup.select(amountOfStudents);
            
            resultList.append(ConsolePrinter.SEPARATOR);
            resultList.append(String.format(" %-20s | %s %n", "Course name", "Inscribed students"));
            resultList.append(ConsolePrinter.SEPARATOR);
            while (cachedSet.next()) {
                resultList.append(String.format(" %-20s | %11d %n", cachedSet.getString("course_name"),
                        cachedSet.getInt("inscribed_students")));
            }
            cachedSet.close();
            System.out.println(resultList.toString());
            ConsolePrinter.closeWindow();
    }
}