package ua.foxminded.javaspring.tovarnykh.school_console_app.dao.fabric;

import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.CoursesDAO;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.GroupsDAO;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.StudentsCoursesDAO;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.StudentsDAO;

public final class FabricDAO {
    
    static StudentsDAO studentsDAO = new StudentsDAO();
    static StudentsCoursesDAO studentsCoursesDAO = new StudentsCoursesDAO();
    static GroupsDAO groupsDAO = new GroupsDAO();
    static CoursesDAO coursesDAO = new CoursesDAO();
    
    public static StudentsDAO getStudents() {
        return studentsDAO;
    }
    
    public static StudentsCoursesDAO getStudentsCourses() {
        return studentsCoursesDAO;
    }
    
    public static GroupsDAO getGroups() {
        return groupsDAO;
    }
    
    public static CoursesDAO getCourses() {
        return coursesDAO;
    }
    
}