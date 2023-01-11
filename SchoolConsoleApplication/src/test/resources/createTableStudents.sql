DROP TABLE IF EXISTS students CASCADE;
CREATE TABLE students
(
    student_id SERIAL PRIMARY KEY,
    group_id integer,
    first_name character varying(20) NOT NULL,
    last_name character varying(25) NOT NULL,
    FOREIGN KEY (group_id) REFERENCES groups(group_id)
);