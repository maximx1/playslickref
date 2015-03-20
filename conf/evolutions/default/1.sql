# --- !Ups
create sequence JOBS_ID_SEQ;
create table JOBS(
	ID integer not null default nextval('JOBS_ID_SEQ'),
	TITLE text not null,
	LOWER_PAY_LEVEL numeric,
	UPPER_PAY_LEVEL numeric,
	IS_OPEN bool not null default false,
	primary key(ID)
);

create sequence EMPLOYEES_ID_SEQ;
create table employees(
	ID integer not null default nextval('EMPLOYEES_ID_SEQ'),
	FIRST_NAME text not null,
	LAST_NAME text not null,
	EMAIL text,
	JOB_ID integer,
	primary key(ID),
	foreign key(JOB_ID) references jobs(ID)
);

insert into jobs(ID, TITLE, LOWER_PAY_LEVEL, UPPER_PAY_LEVEL, IS_OPEN) values(DEFAULT, 'System Engineer I', 55000, 70000, true);
insert into jobs(ID, TITLE, LOWER_PAY_LEVEL, UPPER_PAY_LEVEL, IS_OPEN) values(DEFAULT, 'System Engineer II', 68000, 80000, true);
insert into jobs(ID, TITLE, LOWER_PAY_LEVEL, UPPER_PAY_LEVEL, IS_OPEN) values(DEFAULT, 'System Engineer III', 78000, 120000, true);
insert into jobs(ID, TITLE, LOWER_PAY_LEVEL, UPPER_PAY_LEVEL, IS_OPEN) values(DEFAULT, 'System Engineer IV', 100000, 160000, false);

insert into employees(ID, FIRST_NAME, LAST_NAME, EMAIL, JOB_ID) values(DEFAULT, 'Justin', 'Walrath', 'walrathjaw@gmail.com', 2);

# --- !Downs
drop table if exists EMPLOYEES;
drop table if exists JOBS;
drop sequence if exists EMPLOYEES_ID_SEQ;
drop sequence if exists JOBS_ID_SEQ;