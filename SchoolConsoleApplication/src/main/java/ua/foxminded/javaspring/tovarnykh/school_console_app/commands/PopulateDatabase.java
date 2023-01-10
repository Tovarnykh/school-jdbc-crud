package ua.foxminded.javaspring.tovarnykh.school_console_app.commands;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.CoursesDao;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.GroupsDao;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.StudentsCoursesDao;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.StudentsDao;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.fabric.FabricDao;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.pojo.Student;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.pojo.StudentsCourses;

/**
 * @author Victor Tovarnykh
 * @version 0.15.0
 * @since 0.1.0
 */
public class PopulateDatabase implements ControllerCommand {

    public static final List<String> COURSES = List.of("Mathematics", "Science", "Language Arts", "Health",
            "Handwriting", "Physical Education", "Art", "Music", "Instrumental Music", "Dance");
    public static List<String> groups = new ArrayList<>();

    private final int STUDENTS_TO_GENERATE = 200;
    private final int MIN_GROUP_SIZE = 10;
    private final int MAX_GROUP_SIZE = 30;

    private final List<String> FIRST_NAMES = List.of("Liam", "Olivia", "Noah", "Emma", "Oliver", "Charlotte", "Elijah",
            "Amelia", "James", "Ava", "William", "Sophia", "Benjamin", "Isabella", "Lucas", "Mia", "Henry", "Evelyn",
            "Theodore", "Harper");
    private final List<String> LAST_NAMES = List.of("Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia",
            "Miller", "Davis", "Rodriguez", "Martinez", "Hernandez", "Lopez", "Gonzalez", "Wilson", "Anderson",
            "Thomas", "Taylor", "Moore", "Jackson", "Martin");

    private Random random = new Random();

    @Override
    public void execute() throws SQLException {
        StudentsDao studentDAO = FabricDao.getStudentsDao();
        CoursesDao coursesDAO = FabricDao.getCoursesDao();
        GroupsDao groupsDAO = FabricDao.getGroupsDao();
        StudentsCoursesDao studentsCoursesDAO = FabricDao.getStudentsCoursesDao();

        generateGroups();
        groupsDAO.insertMultiply(groups);
        studentDAO.insertMultiply(generateStudents());
        coursesDAO.insertMultiplyCourses(COURSES);
        studentsCoursesDAO.insertMultiply(generateStudentsCourses());
    }

    private void generateGroups() {
        StringBuilder groupName;
        int numberOfGroups = 10;
        int numberOfDigits = 9;
        int alphabetSize = 26;

        for (int i = 0; i < numberOfGroups; i++) {
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
        AtomicInteger groupIndex = new AtomicInteger(1);
        int[] studentsInGroup = generateGroupsSize();
        int enrolledStudents = Arrays.stream(studentsInGroup).sum();

        if (enrolledStudents < STUDENTS_TO_GENERATE) {
            IntStream.range(enrolledStudents, STUDENTS_TO_GENERATE).forEach(studentId -> {
                String firstName = FIRST_NAMES
                        .stream()
                        .skip(random.nextInt(FIRST_NAMES.size()))
                        .findFirst()
                        .get();
                String secondName = LAST_NAMES
                        .stream()
                        .skip(random.nextInt(FIRST_NAMES.size()))
                        .findFirst()
                        .get();

                Student student = new Student();
                student.setGroupId(11);
                student.setFirstName(firstName);
                student.setLastName(secondName);

                students.add(student);
            });
        }

        Arrays.stream(studentsInGroup).forEach(groupSize -> {
            for (int i = 0; i < groupSize; i++) {
                int groupId = groupIndex.get();
                String firstName = FIRST_NAMES
                        .stream()
                        .skip(random.nextInt(FIRST_NAMES.size()))
                        .findFirst()
                        .get();
                String secondName = LAST_NAMES
                        .stream()
                        .skip(random.nextInt(FIRST_NAMES.size()))
                        .findFirst()
                        .get();

                Student student = new Student();
                student.setGroupId(groupId);
                student.setFirstName(firstName);
                student.setLastName(secondName);

                students.add(student);
            }
            groupIndex.getAndIncrement();
        });

        return students;
    }

    private int[] generateGroupsSize() {
        int[] numberStudentInGroups = new int[10];
        int studentsToEnroll = STUDENTS_TO_GENERATE;

        List<Integer> variantSizes = Stream.iterate(0, n -> n + 1)
                .limit(MAX_GROUP_SIZE)
                .filter(n -> n == 0 || n > MIN_GROUP_SIZE)
                .collect(Collectors.toList());

        for (int i = 0; i < numberStudentInGroups.length; i++) {
            if (studentsToEnroll > 10) {
                numberStudentInGroups[i] = variantSizes
                        .get(random.nextInt(variantSizes.size()));
            } else {
                numberStudentInGroups[i] = 0;
            }
            studentsToEnroll -= numberStudentInGroups[i];
        }

        if (Arrays.stream(numberStudentInGroups)
                .anyMatch(groupSize -> groupSize == 0)) {
            numberStudentInGroups = generateGroupsSize();
        }

        return numberStudentInGroups;
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