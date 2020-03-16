DELETE from employee;
DELETE from department;
INSERT INTO department(id,department_name, date_of_create) VALUES (1,'MMO','1990-01-01 10:10:10'),(2,'DTP','1999-11-11 11:11:11');
INSERT INTO employee(employee_id,first_name, last_name, job_name, gender, date_of_birth, department_id) VALUES (1,'Alex','Ivanov','TESTER','MALE','2015-02-02 17:23:23',1),(2,'Alice','Ivanova','HR','FEMALE','1993-03-03 16:23:23',1);