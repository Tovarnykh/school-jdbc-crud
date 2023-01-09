package ua.foxminded.javaspring.tovarnykh.console_app.dao.commands;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import org.junit.jupiter.api.Test;

import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.GroupsDao;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.fabric.FabricDao;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.pojo.Group;

class GroupsDaoTest extends DatabaseTest {
    
    @Test
    void getStudentsAmountInGroups_CheckIfAnyGroupsWereFound_True() throws Exception {
        GroupsDao groupsDAO = FabricDao.getGroupsDao();
        List<Group> groups = groupsDAO.getStudentsAmountInGroups(25);
        assertTrue(!groups.isEmpty());
    }
    
    @Test
    void insertMultiply_CheckIfTenGroupsWhereGenerated_True() throws Exception {
        try (Statement statement = connection.createStatement();
                ResultSet resultSet = statement
                        .executeQuery("SELECT COUNT(group_id) as groups_count FROM groups")) {
            while (resultSet.next()) {
                assertTrue((resultSet.getInt("groups_count")>0));
            }
        }
    }

}
