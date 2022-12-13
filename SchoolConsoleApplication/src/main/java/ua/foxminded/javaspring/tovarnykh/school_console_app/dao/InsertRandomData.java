package ua.foxminded.javaspring.tovarnykh.school_console_app.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.aspects.ConnectionAspect;

public class InsertRandomData implements Command {

    private Random random = new Random();
    private List<String> groups = new ArrayList<>();

    private static final int ALPHABET_SIZE = 26;
    private static final int NAMES_AMOUNT = 20;
    private static final String GROUPS_SCRIPT = "INSERT INTO groups (group_name) VALUES (?)";
    private static final String COURSES_SCRIPT = "INSERT INTO courses (course_name) VALUES (?)";
    private static final String STUDENTS_SCRIPT = "INSERT INTO students (group_id, first_name, last_name) VALUES (?, ?, ?)";

    private static final List<String> COURSES = List.of("Mathematics", "Science", "Language Arts", "Health",
            "Handwriting", "Physical Education", "Art", "Music", "Instrumental Music", "Dance");
    private static final List<String> FIRST_NAMES = List.of("Liam", "Olivia", "Noah", "Emma", "Oliver", "Charlotte",
            "Elijah", "Amelia", "James", "Ava", "William", "Sophia", "Benjamin", "Isabella", "Lucas", "Mia", "Henry",
            "Evelyn", "Theodore", "Harper");
    private static final List<String> LAST_NAMES = List.of("Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia",
            "Miller", "Davis", "Rodriguez", "Martinez", "Hernandez", "Lopez", "Gonzalez", "Wilson", "Anderson",
            "Thomas", "Taylor", "Moore", "Jackson", "Martin");

    @Override
    public void execute() throws SQLException {
        generateGroups();

        insertGroups();
        insertCourses();
        insertStudents();
    }

    private void insertGroups() {
        groups.forEach(groupName -> {
            try (Connection connection = ConnectionAspect.getConnection();
                    PreparedStatement statement = connection.prepareStatement(GROUPS_SCRIPT);) {
                statement.setString(1, groupName);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    private List<String> generateGroups() {
        StringBuilder groupName;
        
        for (int i = 0; i < 10; i++) {
            groupName = new StringBuilder();

            groupName.append((char) (random.nextInt(ALPHABET_SIZE) + 'a'));
            groupName.append((char) (random.nextInt(ALPHABET_SIZE) + 'a'));
            groupName.append("-");
            groupName.append(random.nextInt(9));
            groupName.append(random.nextInt(9));
            groups.add(groupName.toString());
        }
        return groups;
    }

    private void insertCourses() {
        COURSES.forEach(course -> {
            try (Connection connection = ConnectionAspect.getConnection();
                    PreparedStatement statement = connection.prepareStatement(COURSES_SCRIPT);) {
                statement.setString(1, course);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
    
    private void insertStudents() {
        for(int i =0; i<200; i++) {
            try (Connection connection = ConnectionAspect.getConnection();
                    PreparedStatement statement = connection.prepareStatement(STUDENTS_SCRIPT);) {
                statement.setInt(1, random.nextInt(groups.size())+1);
                statement.setString(2, FIRST_NAMES.stream()
                        .skip(random.nextInt(NAMES_AMOUNT))
                        .findFirst()
                        .get());
                statement.setString(3, LAST_NAMES.stream()
                        .skip(random.nextInt(NAMES_AMOUNT))
                        .findFirst()
                        .get());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
