package ua.foxminded.javaspring.tovarnykh.school_console_app.dao.fabric;

import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.CoursesDAO;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.GroupsDAO;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.StudentsCoursesDAO;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.StudentsDAO;

public final class FabricDAO {
    
    public static StudentsDAO getStudents() {
        return new StudentsDAO();
    }
    
    public static StudentsCoursesDAO getStudentsCourses() {
        return new StudentsCoursesDAO();
    }
    
    public static GroupsDAO getGroups() {
        return new GroupsDAO();
    }
    
    public static CoursesDAO getCourses() {
        return new CoursesDAO();
    }
    
}