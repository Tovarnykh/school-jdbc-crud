package ua.foxminded.javaspring.tovarnykh.school_console_app.commands;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import ua.foxminded.javaspring.tovarnykh.school_console_app.commands.pojo.Student;
import ua.foxminded.javaspring.tovarnykh.school_console_app.commands.pojo.StudentsCourses;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.CoursesDAO;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.GroupsDAO;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.StudentsCoursesDAO;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.StudentsDAO;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.fabric.FabricDAO;

/**
 * @author Victor Tovarnykh
 * @version 0.15.0
 * @since 0.1.0
 */
public class PopulateDatabase implements ControllerCommand {
    
    public static List<String> groups = new ArrayList<>();

    private final int STUDENTS_TO_GENERATE = 200;

    private final List<String> COURSES = List.of("Mathematics", "Science", "Language Arts", "Health", "Handwriting",
            "Physical Education", "Art", "Music", "Instrumental Music", "Dance");
    private final List<String> FIRST_NAMES = List.of("Liam", "Olivia", "Noah", "Emma", "Oliver", "Charlotte", "Elijah",
            "Amelia", "James", "Ava", "William", "Sophia", "Benjamin", "Isabella", "Lucas", "Mia", "Henry", "Evelyn",
            "Theodore", "Harper");
    private final List<String> LAST_NAMES = List.of("Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia",
            "Miller", "Davis", "Rodriguez", "Martinez", "Hernandez", "Lopez", "Gonzalez", "Wilson", "Anderson",
            "Thomas", "Taylor", "Moore", "Jackson", "Martin");

    private Random random = new Random();

    @Override
    public void execute() throws SQLException {
        StudentsDAO studentDAO = FabricDAO.getStudents();
        CoursesDAO coursesDAO = FabricDAO.getCourses();
        GroupsDAO groupsDAO = FabricDAO.getGroups();
        StudentsCoursesDAO studentsCoursesDAO = new StudentsCoursesDAO();

        generateGroups();
        groupsDAO.insertMultiply(groups);
        studentDAO.insertMultiply(generateStudents());
        coursesDAO.insertMultiplyCourses(COURSES);
        studentsCoursesDAO.insertMultiply(generateStudentsCourses());
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

    private List<Student> generateStudents() {
        List<Student> students = new ArrayList<>();
        String firstName;
        String secondName;
        int groupId;

        for (int i = 0; i < STUDENTS_TO_GENERATE; i++) {
            groupId = random.nextInt(1, groups.size() + 2);
            firstName = FIRST_NAMES.stream().skip(random.nextInt(FIRST_NAMES.size())).findFirst().get();
            secondName = LAST_NAMES.stream().skip(random.nextInt(FIRST_NAMES.size())).findFirst().get();

            Student student = new Student();
            student.setGroupId(groupId);
            student.setFirstName(firstName);
            student.setSecondName(secondName);
            
            students.add(student);
        }
        return students;
    }

    private List<StudentsCourses> generateStudentsCourses() {
        int coursesToAsign = 4;
        AtomicInteger iterator = new AtomicInteger(0);
        List<StudentsCourses> studentsCourses = new ArrayList<>();

        Stream.iterate(1, n -> n + 1)
        .limit(STUDENTS_TO_GENERATE)
        .forEachOrdered(studentId -> {
            List<Integer> numbers = new ArrayList<>();
            iterator.set(0);

            Stream.iterate(1, n -> n + 1)
            .limit(random.nextInt(1, coursesToAsign))
            .forEach(courseId -> {
                int generatedCourse = random.nextInt(1, COURSES.size() + 1);

                if (!(numbers.contains(generatedCourse))) {
                    numbers.add(generatedCourse);
                    
                    StudentsCourses record = new StudentsCourses();
                    record.setStudentId(studentId);
                    record.setCourseID(numbers.get(iterator.getAndIncrement()));
                    studentsCourses.add(record);
                }
            });
        });
        return studentsCourses;
    }

}