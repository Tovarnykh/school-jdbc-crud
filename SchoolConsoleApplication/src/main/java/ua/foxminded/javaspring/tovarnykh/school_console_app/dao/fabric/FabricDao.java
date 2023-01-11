package ua.foxminded.javaspring.tovarnykh.school_console_app.dao.fabric;

import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.CoursesDao;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.GroupsDao;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.StudentsCoursesDao;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.StudentsDao;

public final class FabricDao {

    private static StudentsDao studentsDAO = new StudentsDao();
    private static StudentsCoursesDao studentsCoursesDAO = new StudentsCoursesDao();
    private static GroupsDao groupsDAO = new GroupsDao();
    private static CoursesDao coursesDAO = new CoursesDao();

    public static StudentsDao getStudentsDao() {
        return studentsDAO;
    }

    public static StudentsCoursesDao getStudentsCoursesDao() {
        return studentsCoursesDAO;
    }

    public static GroupsDao getGroupsDao() {
        return groupsDAO;
    }

    public static CoursesDao getCoursesDao() {
        return coursesDAO;
    }

}