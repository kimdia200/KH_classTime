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

select * from student;