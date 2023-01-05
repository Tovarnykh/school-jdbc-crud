package ua.foxminded.javaspring.tovarnykh.school_console_app.commands;

import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;

import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.GroupsDao;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.fabric.FabricDao;
import ua.foxminded.javaspring.tovarnykh.school_console_app.main.ConsolePrinter;

/**
 *
 * @author Victor Tovarnykh
 * @version 0.15.0
 * @since 0.1.0
 */
public class FindGroup implements ControllerCommand {

    @Override
    public void execute() throws SQLException {
        System.out.print("""
                ╔════════════════════════════════════════╗
                ║Insert a number of students` to find    ║
                ║groups.                                 ║
                ╟────────────────────────────────────────╢
                 in:
                 """);

        int amountOfStudents = ConsolePrinter.readNumber();
        StringBuilder resultList = new StringBuilder();
        GroupsDao groupsDAO = FabricDao.getGroupsDao();
        CachedRowSet cachedSet = groupsDAO.getStudentsAmountInGroups(amountOfStudents);

        resultList.append(ConsolePrinter.SEPARATOR);
        resultList.append(String.format(" %-20s | %13s %n", "Group name", "Students"));
        resultList.append(ConsolePrinter.SEPARATOR);
        while (cachedSet.next()) {
            resultList.append(String.format(" %-20s | %11d %n", cachedSet.getString("group_name"),
                    cachedSet.getInt("inscribed_students")));
        }
        cachedSet.close();
        System.out.println(resultList);
        ConsolePrinter.closeSection();
    }
}