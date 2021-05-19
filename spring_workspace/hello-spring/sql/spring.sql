--===================================================
-- 관리자 계정
--===================================================
create user spring
identified by spring
default tablespace users;

grant connect, resource to spring;



--===================================================
-- spring 계정
--===================================================
create table dev(
    no number,
    name varchar2(50) not null,
    career number not null,
    email varchar2(200) not null,
    gender char(1),
    lang varchar2(500) not null,
    CONSTRAINT pk_dev_no primary key(no),
    CONSTRAINT ck_dev_gender check(gender in ('M', 'F'))
);

create SEQUENCE seq_dev_no;


select * from dev;
