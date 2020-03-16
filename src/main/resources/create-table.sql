create table if not exists department
(
	id serial not null
		constraint department_pkey
			primary key,
	department_name varchar(255) not null,
	date_of_create timestamp not null
);

create type positions as enum ('PROGRAMMER', 'HR', 'TESTER', 'TEAM_LEAD');

create table if not exists employee
(
	employee_id serial not null
		constraint employee_pkey
			primary key,
	first_name varchar(255) not null,
	last_name varchar(255) not null,
	job_name positions,
	gender varchar(255) not null,
	date_of_birth timestamp not null,
	department_id integer
		constraint "REFER_BY_DEPARTMENT_ID"
			references department
);