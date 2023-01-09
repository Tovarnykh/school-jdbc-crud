package ua.foxminded.javaspring.tovarnykh.school_console_app.dao.pojo;

public class Group {
    
    private String groupName;
    private int inscribedStudents;
    
    public Group(String groupName, int inscribedStudents) {
        this.groupName = groupName;
        this.inscribedStudents = inscribedStudents;
    }

    public String getGroupName() {
        return groupName;
    }
    
    public int getInscribedStudents() {
        return inscribedStudents;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setInscribedStudents(int inscribedStudents) {
        this.inscribedStudents = inscribedStudents;
    }

}