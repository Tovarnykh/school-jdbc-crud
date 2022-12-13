DROP TABLE IF EXISTS courses;
CREATE TABLE courses(
course_id SERIAL PRIMARY KEY,
course_name character varying(20) NOT NULL,
course_description TEXT);