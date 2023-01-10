package ua.foxminded.javaspring.tovarnykh.school_console_app.commands;

import java.sql.SQLException;
import java.util.List;

import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.GroupsDao;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.fabric.FabricDao;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.pojo.Group;
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
        int amountOfStudents = 0;

        System.out.print("""
                ╔════════════════════════════════════════╗
                ║Insert a number of students` to find    ║
                ║groups.                                 ║
                ╟────────────────────────────────────────╢
                 in:
                 """);

        amountOfStudents = ConsolePrinter.readNumber();
        StringBuilder resultList = new StringBuilder();
        GroupsDao groupsDAO = FabricDao.getGroupsDao();
        List<Group> groups = groupsDAO.getStudentsAmountInGroups(amountOfStudents);

        resultList.append(ConsolePrinter.SEPARATOR);
        resultList.append(String.format(" %-20s | %13s %n", "Group name", "Students"));
        resultList.append(ConsolePrinter.SEPARATOR);

        groups.forEach(group -> resultList
                .append(String.format(" %-20s | %11d %n", 
                        group.getGroupName(), 
                        group.getInscribedStudents())));
        System.out.println(resultList);
        ConsolePrinter.closeSection();

    }
}