package ua.foxminded.javaspring.tovarnykh.school_console_app.dao.commands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Stream;

import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.aspects.ConnectionAspect;

/**
* @author Victor Tovarnykh
* @version 0.15.0
* @since 0.1.0
*/
public class GenerateData implements Command {

    private Random random = new Random();
    private List<String> groups = new ArrayList<>();

    private static final int STUDENTS_TO_GENERATE = 200;
    private static final String GROUPS_SCRIPT = "INSERT INTO groups (group_name) VALUES (?)";
    private static final String COURSES_SCRIPT = "INSERT INTO courses (course_name) VALUES (?)";
    private static final String STUDENTS_SCRIPT = "INSERT INTO students (group_id, first_name, last_name) VALUES (?, ?, ?)";
    private static final String STUDENTS_COURSES_SCRIPT = "INSERT INTO students_courses (student_id, course_id) VALUES (?, ?)";

    private static final List<String> COURSES = List.of("Mathematics", "Science", "Language Arts", "Health",
            "Handwriting", "Physical Education", "Art", "Music", "Instrumental Music", "Dance");
    private static final List<String> FIRST_NAMES = List.of("Liam", "Olivia", "Noah", "Emma", "Oliver", "Charlotte",
            "Elijah", "Amelia", "James", "Ava", "William", "Sophia", "Benjamin", "Isabella", "Lucas", "Mia", "Henry",
            "Evelyn", "Theodore", "Harper");
    private static final List<String> LAST_NAMES = List.of("Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia",
            "Miller", "Davis", "Rodriguez", "Martinez", "Hernandez", "Lopez", "Gonzalez", "Wilson", "Anderson",
            "Thomas", "Taylor", "Moore", "Jackson", "Martin");
    
    @Override
    public String execute() throws SQLException {
        insertGroups();
        insertCourses();
        insertStudents();
        insertStudentsCourses();
        return "true";
    }
    
    private void insertGroups() throws SQLException {
        generateGroups();
        try (Connection connection = ConnectionAspect.getConnection();
                PreparedStatement statement = connection.prepareStatement(GROUPS_SCRIPT);) {
            for (String group : groups) {
                statement.setString(1, group);
                statement.executeUpdate();
            }
        }

    }

    private void generateGroups() {
        StringBuilder groupName;
        int numberOfGourps = 10;
        int numberOfDigits = 9;
        int alphabetSize = 26;

        for (int i = 0; i < numberOfGourps; i++) {
            groupName = new StringBuilder();

            groupName.append((char) (random.nextInt(alphabetSize) + 'a'));
            groupName.append((char) (random.nextInt(alphabetSize) + 'a'));
            groupName.append("-");
            groupName.append(random.nextInt(numberOfDigits));
            groupName.append(random.nextInt(numberOfDigits));
            groups.add(groupName.toString());
        }
    }

    private void insertCourses() throws SQLException {
        try (Connection connection = ConnectionAspect.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(COURSES_SCRIPT);
                Statement statement = connection.createStatement();) {
            StringBuilder query = new StringBuilder();

            for (String course : COURSES) {
                preparedStatement.setString(1, course);
                query.append(preparedStatement.toString() + ";\n");
                preparedStatement.clearParameters();
            }
            statement.executeUpdate(query.toString());
        }

    }

    private void insertStudents() throws SQLException {
        try (Connection connection = ConnectionAspect.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(STUDENTS_SCRIPT);
                Statement statement = connection.createStatement()) {
            StringBuilder query = new StringBuilder();
            String firstName;
            String lastName;
            int groupId;

            for (int i = 0; i < STUDENTS_TO_GENERATE; i++) {
                groupId = random.nextInt(1, groups.size());
                firstName = FIRST_NAMES.stream().skip(random.nextInt(FIRST_NAMES.size())).findFirst().get();
                lastName = LAST_NAMES.stream().skip(random.nextInt(FIRST_NAMES.size())).findFirst().get();

                preparedStatement.setInt(1, groupId);
                preparedStatement.setString(2, firstName);
                preparedStatement.setString(3, lastName);
                query.append(preparedStatement.toString() + ";\n");
                preparedStatement.clearParameters();
            }
            statement.executeUpdate(query.toString());
        }
    }

    private void insertStudentsCourses() throws SQLException {
        try (Connection connection = ConnectionAspect.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(STUDENTS_COURSES_SCRIPT);
                Statement statement = connection.createStatement();) {
            StringBuilder query = new StringBuilder();
            StringBuilder uniqueQuery = new StringBuilder();
            Set<String> uniqueSet = new HashSet<>();
            int coursesToAsign=4;

            Stream.iterate(1, n -> n + 1).limit(STUDENTS_TO_GENERATE).forEachOrdered(
                    studentId -> Stream.iterate(1, n -> n + 1).limit(random.nextInt(0, coursesToAsign)).forEach(courseId -> {
                        try {
                            preparedStatement.setInt(1, studentId);
                            preparedStatement.setInt(2, random.nextInt(1, COURSES.size()+1));
                            query.append(preparedStatement.toString() + ";\n");
                            preparedStatement.clearParameters();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }));
            
            Arrays.stream(query.toString().split("\n")).forEach(uniqueSet::add);
            uniqueSet.forEach(uniqueQuery::append);
            statement.executeUpdate(uniqueQuery.toString());
        }
    }

}