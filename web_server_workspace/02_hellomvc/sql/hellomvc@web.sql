--============================================
--관리자 계정
--============================================
--web계정 생성
create user web
identified by web
default tablespace users;


--권한 부여
grant connect, resource to web;



--============================================
--WEB 계정 으로 작업
--============================================
show user; --현재 작업 중인 계정이 web인것을 확인함

--회원테이블 생성
--drop table MEMBER
create table member(
    member_id varchar2(15),
    password varchar2(300) not null,
    member_name varchar2(50) not null,
    member_role char(1) default 'U' not null,
    gender char(1),
    birthday date,
    email varchar2(100),
    phone char(11) not null,
    address varchar2(200),
    hobby varchar2(200),
    enroll_date date default sysdate,
    constraint pk_member_id primary key(member_id),
    constraint ck_gender check(gender in ('M','F')),                   -- M남자, F여자 
    constraint ck_member_role check(member_role in ('U','A')) -- U=일반 사용자, A = 관리자
);

select * from member;
select * from tab;
    
rollback;

commit;
 
update member
set password = '1234', member_name = '김종수', member_role = 'A', gender='F', birthday = '94/02/06', email = 'kimdia2000@gmail.com', phone = '01011111111', address = '서울시 신림동', hobby = '운동 ,등산,독서', enroll_date = '21/02/06'
where member_id = 'kimdia200';

    

INSERT INTO member 
VALUES ('kimdia200','dbstn','김윤수','U','M',sysdate,'kimdia200@naver.com','01063534583','경기도 부천시','게임',sysdate);
    
    
    
--회원 추가
	insert into member
	values (
		'honggd', '1234', '홍길동', 'U', 'M', to_date('20000909','yyyymmdd'),
		'honggd@naver.com', '01012341234', '서울시 강남구', '운동,등산,독서', default
	);
	insert into member
	values (
		'qwerty', '1234', '쿠어티', 'U', 'F', to_date('19900215','yyyymmdd'),
		'qwerty@naver.com', '01012341234', '서울시 송파구', '운동,등산', default
	);
	insert into member
	values (
		'admin', '1234', '관리자', 'A', 'M', to_date('19801010','yyyymmdd'),
		'admin@naver.com', '01056785678', '서울시 관악구', '게임,독서', default
	);
    
    delete member where member_id='honggd';
    
    
    
    
    
    
    
    
    
    
    