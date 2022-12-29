package ua.foxminded.javaspring.tovarnykh.school_console_app.commands.Entities;

public class StudentsCourses {

    public int studentId;
    public int courseID;

    public StudentsCourses() {

    }

    public StudentsCourses(int studentId, int courseID) {
        super();
        this.studentId = studentId;
        this.courseID = courseID;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
    
    
    
}
