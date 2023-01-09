package ua.foxminded.javaspring.tovarnykh.school_console_app.dao.pojo;

public class Student {

    private int groupId;
    private String firstName;
    private String lastName;

    public Student() {

    }
    
    public Student(String first_name, String second_name) {
        this.firstName = first_name;
        this.lastName = second_name;
    }

    public Student(int group_id, String first_name, String second_name) {
        this.groupId = group_id;
        this.firstName = first_name;
        this.lastName = second_name;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int group_id) {
        this.groupId = group_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String first_name) {
        this.firstName = first_name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String second_name) {
        this.lastName = second_name;
    }

}
