package ua.foxminded.javaspring.tovarnykh.school_console_app.dao.pojo;

public class Student {

    private int groupId;
    private String firstName;
    private String secondName;

    public Student() {

    }

    public Student(int group_id, String first_name, String second_name) {
        this.groupId = group_id;
        this.firstName = first_name;
        this.secondName = second_name;
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

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String second_name) {
        this.secondName = second_name;
    }

}
