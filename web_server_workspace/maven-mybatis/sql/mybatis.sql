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

select * from job where job_code in ('J1','J2');

select * from (select e.*, decode(substr(emp_no, 8, 1), '1', '남', '3', '남', '여') gender, d.dept_title, j.job_name
				from emp e 
				left join dept d on e.dept_code = d.dept_id
				left join job j on e.job_code = j.job_code);
                
select dept_id "deptId", dept_title "deptTitle" from dept order by dept_id asc;

select e.*, decode(substr(emp_no, 8, 1), '1', '남', '3', '남', '여') gender, d.dept_title, j.job_name, nvl(dept_code,'D0') "dept_codee" from emp e left join dept d on e.dept_code = d.dept_id left join job j on e.job_code = j.job_code;
select * from ( 
                        select e.*, decode(substr(emp_no, 8, 1), '1', '남', '3', '남', '여') gender, d.dept_title, j.job_name, nvl(dept_code,'D0') dept_codee 
                        from emp e left join dept d on e.dept_code = d.dept_id left join job j on e.job_code = j.job_code 
                    ) e where dept_codee is null;
                    
                    
                    select * from emp where emp_id=214;