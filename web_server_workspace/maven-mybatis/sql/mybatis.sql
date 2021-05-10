--======================================
--관리자(system) 계정
--======================================

create user mybatis
identified by mybatis
default tablespace users;

grant connect, resource to mybatis;

--======================================
--mybatis계정
--======================================
create table student(
    no number,
    name varchar2(100) not null,
    tel char(11) not null,
    reg_date date default sysdate,
    constraint pk_student_no primary key(no)
);
create sequence seq_student_no;


--oracle synonym객체
--동의어객체. 별칭객체

--mybatis계정에서 kh계정의 table접근
select * from kh.employee; -- emp
select * from kh.department; -- dept
select * from kh.job; -- job

-- 동의어 생성
-- resource롤에 create synonym은 포함되어 있지 않다.
create synonym emp for kh.employee;
create synonym dept for kh.department;
create synonym job for kh.job;

select * from emp;
select * from dept;
select * from job;

--============================
-- 관리자계정
--============================
grant all on kh.employee to mybatis;
grant select on kh.department to mybatis;
grant select on kh.job to mybatis;

grant create synonym to mybatis;
--============================

select * from tab;

commit;

emp_id, emp_name, emp_no, email, phone, dept_code, job_code, sal_level, salary, bonus, manager_id, hire_date, quit_date, quit_yn;