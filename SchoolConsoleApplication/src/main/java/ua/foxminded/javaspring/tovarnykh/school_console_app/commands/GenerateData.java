package ua.foxminded.javaspring.tovarnykh.school_console_app.commands;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.statements.InsertMultiplyCourses;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.statements.InsertMultiplyGroups;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.statements.InsertMultiplyStudent;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.statements.InsertMultiplyStudentsCourses;

/**
 * @author Victor Tovarnykh
 * @version 0.15.0
 * @since 0.1.0
 */
public class GenerateData implements Command {

    private Random random = new Random();
    private List<String> groups = new ArrayList<>();

    private static final int STUDENTS_TO_GENERATE = 200;
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
        InsertMultiplyGroups.insert(groups);
        InsertMultiplyStudent.insert(generateStudents());
        InsertMultiplyCourses.insert(COURSES);
        InsertMultiplyStudentsCourses.insert(generateStudentsCourses());
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

    private List<String> generateStudents() {
        List<String> students = new ArrayList<>();
        String firstName;
        String lastName;
        int groupId;

        for (int i = 0; i < STUDENTS_TO_GENERATE; i++) {
            StringBuilder student = new StringBuilder();
            groupId = random.nextInt(1, groups.size()+2);
            firstName = FIRST_NAMES.stream()
                    .skip(random.nextInt(FIRST_NAMES.size()))
                    .findFirst().get();
            lastName = LAST_NAMES.stream()
                    .skip(random.nextInt(FIRST_NAMES.size()))
                    .findFirst().get();

            student.append(groupId);
            student.append(" ");
            student.append(firstName);
            student.append(" ");
            student.append(lastName);
            students.add(student.toString());
        }
        return students;
    }

    private List<String> generateStudentsCourses() {
        int coursesToAsign = 4;
        AtomicInteger iterator = new AtomicInteger(0);
        List<String> records = new ArrayList<>();

        Stream.iterate(1, n -> n + 1)
        .limit(STUDENTS_TO_GENERATE)
        .forEachOrdered(studentId -> {
            List<Integer> numbers = new ArrayList<>();
            iterator.set(0);

            Stream.iterate(1, n -> n + 1)
            .limit(random.nextInt(1, coursesToAsign))
            .forEach(courseId -> {
                StringBuilder simpleRecord = new StringBuilder();
                int generatedCourse = random.nextInt(1, COURSES.size() + 1);

                if (!(numbers.contains(generatedCourse))) {
                    numbers.add(generatedCourse);

                    simpleRecord.append(studentId);
                    simpleRecord.append(" ");
                    simpleRecord.append(numbers.get(iterator.getAndAdd(1)));

                    records.add(simpleRecord.toString());
                }
            });
        });
        return records;
    }

}