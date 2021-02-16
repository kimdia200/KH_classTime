--===============================
-- 관리자 계정 에서 실행
--===============================
--student 계정 생성 및 권한부여
create user student
identified by student
default tablespace users;

grant connect, resource to student;


--===============================
-- Student 계정 에서 실행
--===============================
--drop table member;
create table member(
    member_id varchar2(20),
    password varchar2(20) not null,
    member_name varchar2(100) not null,
    gender char(1), 
    age number,
    email varchar2(200),
    phone char(11) not null,
    address varchar2(1000),
    hobby varchar2(100), --농구, 음악감상, 영화.... 나열한 문자열을 관리
    enroll_date date default sysdate,
    constraint pk_member_id primary key(member_id),
    constraint ck_member_gender check(gender in ('M','F'))
);

insert into member
values(
    'honggd', '1234', '홍길동', 'M', 33, 'honggd@naver.com', '01012341234',
    '서울시 강남구 테헤란로', '등산, 그림, 요리', default);
    
insert into member
values(
    'sinsa', '1234', '신사임당', 'F', 48, 'sinsa@naver.com', '01011111111',
    '서울시 강동구', '음악감상', default);
    
insert into member
values(
    'sejong', '1234', '세종', 'M', 76, 'sejong@naver.com', '01022222222',
    '서울 중구', '육식', default);   

insert into member
values(
    'leess', '1234', '이순신', 'M', 45, 'leess@naver.com', '01033333333',
    '전남 여수', '목공예', default);
    
insert into member
values(
    'ygsgs', '1234', '유관순', 'F', null, null, '01044444444',
    null,null, default);

select * from member;

commit;
rollback;

desc member;

--update member
--set password = '1111', member_name = '김종수', gender = 'F', age = 25, email = 'kimdai2000', phone = '01011111111', address = '서울시 강남구', hobby = 'ㅇㅇㅇㅇㅇㅇㅇㅇ'
--where member_id = 'kimdia200';

select * from member
where member_name like '%김%';





