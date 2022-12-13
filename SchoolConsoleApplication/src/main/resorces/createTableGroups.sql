DROP TABLE IF EXISTS groups CASCADE;
CREATE TABLE groups(
group_id SERIAL PRIMARY KEY,
group_name character varying(5) NOT NULL);