DROP TABLE IF EXISTS students;
CREATE TABLE students(
student_id SERIAL PRIMARY KEY,
group_id integer REFERENCES groups,
first_name character varying(20) NOT NULL,
last_name character varying(25) NOT NULL);