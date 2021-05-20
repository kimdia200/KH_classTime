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

create table member(
		id varchar2(15),
		password varchar2(300) not null,
		name varchar2(256) not null,
		gender char(1),
		birthday date,
		email varchar2(256),
		phone char(11) not null,
		address varchar2(512),
		hobby varchar2(256),
		enroll_date date default sysdate, 
		enabled number default 1,               --활성화여부 1(활성화), 0(비활성화)
		constraint pk_member_id primary key(id),
		constraint ck_member_gender check(gender in ('M', 'F')),
		constraint ck_member_enabled check(enabled in (1,0))
	);
	insert into spring.member values ('abcde','1234','아무개','M',to_date('88-01-25','rr-mm-dd'),'abcde@naver.com','01012345678','서울시 강남구','운동,등산,독서',default,default);
	insert into spring.member values ('qwerty','1234','김말년','F',to_date('78-02-25','rr-mm-dd'),'qwerty@naver.com','01098765432','서울시 관악구','운동,등산',default,default);
	insert into spring.member values ('admin','1234','관리자','F',to_date('90-12-25','rr-mm-dd'),'admin@naver.com','01012345678','서울시 강남구','독서',default,default);
	commit;
    
	select
		*
	from
		member;
        
    
        
        
        
        
        