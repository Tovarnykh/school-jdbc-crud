# Console Application

This project it is a part of **foxminded** education course.

It consists of 6 tasks.
* Plain JDBC Console Application
* Spring Boot JDBC Api
* Service layer
* Console menu
* Hibernate
* Spring Data JPA

## Task 2.1 Plain JDBC Console Application
### Assignment

1. Create SQL files with data:

    a. create a user and database. Assign all privileges on the database to the user. (DB and the user should be created before application runs)

    b. create a file with tables creation

2. Create a java application

    a. On startup, it should run SQL script with table creation from previously created files. If tables already exist - drop them.

    b. Generate the test data:

* 10 groups with randomly generated names. The name should contain 2 characters, hyphen, 2 numbers

* Create 10 courses (math, biology, etc)

* 200 students. Take 20 first names and 20 last names and randomly combine them to generate students.

* Randomly assign students to groups. Each group could contain from 10 to 30 students. It is possible that some groups will be without students or students without groups

* Create the MANY-TO-MANY relation  between STUDENTS and COURSES tables. Randomly assign from 1 to 3 courses for each student

3. Write SQL Queries, it should be available from the console menu:

    a. Find all groups with less or equal students’ number

    b. Find all students related to the course with the given name

    c. Add a new student

    d. Delete a student by the STUDENT_ID

    e. Add a student to the course (from a list)

    f. Remove the student from one of their courses.

**Solution**:



**Compiling**:


To launch the program **Java9+** requires.