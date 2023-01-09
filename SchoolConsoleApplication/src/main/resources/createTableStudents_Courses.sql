DROP TABLE IF EXISTS students_courses CASCADE;
CREATE TABLE students_courses
(
    student_id INTEGER NOT NULL,
    course_id INTEGER NOT NULL,
    FOREIGN KEY(student_id) REFERENCES students(student_id) ON DELETE CASCADE,
    FOREIGN KEY(course_id) REFERENCES courses(course_id),
    PRIMARY KEY(student_id, course_id)
);