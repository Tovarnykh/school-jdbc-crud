package ua.foxminded.javaspring.tovarnykh.console_app.dao.commands;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.GroupsDao;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.fabric.FabricDao;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.pojo.Group;

class GroupsDaoTest extends DatabaseTest {

    @Test
    void getStudentsAmountInGroups_CheckIfAnyGroupsWereFound_False() throws Exception {
        GroupsDao groupsDAO = FabricDao.getGroupsDao();
        List<Group> groups = groupsDAO.getStudentsAmountInGroups(25);
        assertFalse(groups.isEmpty());
    }

}