--유저생성
create user web
identified by web
default tablespace users;
grant connect, resource to web;
--강종성
create user cloud1
identified by Semi_Project_1
default tablespace users;
grant connect, resource to cloud1;
--김윤수
create user cloud2
identified by Semi_Project_2
default tablespace users;
grant connect, resource to cloud2;
--박요한
create user cloud3
identified by Semi_Project_3
default tablespace users;
grant connect, resource to cloud3;
--이승우
create user cloud4
identified by Semi_Project_4
default tablespace users;
grant connect, resource to cloud4;
--천호현
create user cloud5
identified by Semi_Project_5
default tablespace users;
grant connect, resource to cloud5;
--최한성
create user cloud6
identified by Semi_Project_6
default tablespace users;
grant connect, resource to cloud6;

commit;

select *
from dba_sys_privs
where grantee in ('CONNECT', 'RESOURCE');


GRANT UNLIMITED TABLESPACE TO cloud1;
GRANT UNLIMITED TABLESPACE TO cloud2;
GRANT UNLIMITED TABLESPACE TO cloud3;
GRANT UNLIMITED TABLESPACE TO cloud4;
GRANT UNLIMITED TABLESPACE TO cloud5;
GRANT UNLIMITED TABLESPACE TO cloud6;

update member 
set member_role = 'A'
where member_id = 'admin';

select * from member;

--semi

--장소 테이블
create table location(
    lcode varchar2(10),
    lname varchar2(10),
    constraint pk_location_code primary key(lcode)
);

--카테고리 테이블
create table category(
    ccode varchar2(10),
    cname varchar2(20),
    constraint pk_category_code primary key(ccode)
);

--회원 테이블
create table member(
    member_id varchar2(50),
    password varchar2(100) not null,
    name varchar2(100) not null,
    email varchar2(100) not null,
    phone varchar2(11) not null,
    event1 varchar2(2),
    event2 varchar2(2),
    enroll_date date default sysdate,
    member_role varchar2(2) default 'U' not null,
    constraint pk_member_id primary key(member_id),
    constraint ck_member_event1 check(event1 in ('T','F')),
    constraint ck_member_event2 check(event2 in ('T','F')),
    constraint ck_member_role check(member_role in ('U','A'))
);

--미팅 테이블
drop table meeting;
create table meeting(
    meeting_no number,
    title varchar2(500) not null,
    writer varchar2(50),
    content clob not null,
    --첨부파일도
    reg_date date default sysdate,
    place varchar2(100),
    time date,
    max_people number,
    cost number,
    category_code varchar2(10),
    location_code varchar2(10),
    constraint pk_meeting_no primary key(meeting_no),
    constraint fk_meeting_writer foreign key(writer) references member(member_id) on delete cascade,
    constraint fk_meeting_category foreign key(category_code) references category(ccode),
    constraint fk_meeting_location foreign key(location_code) references location(lcode)
    
);
--미팅 시퀀스
create sequence seq_meeting;

--첨부파일테이블 생성
drop table attachment;
create table attachment (
    attach_no number primary key,
    meeting_no number not null,
    original_filename varchar2(255) not null,
    renamed_filename varchar2(255) not null,
    status char(1) default 'Y',
    constraint fk_attach_board_no foreign key(meeting_no) references meeting(meeting_no) on delete cascade,
	constraint ck_status check(status in ('Y','N'))
);
--첨부파일 시퀀스
create sequence seq_attachment;


--미팅 참가자 테이블
create table participation(
    partici_no number primary key,
    meeting_no number,
    partici_id varchar2(50),
    reg_date date default sysdate,
    status char(1) default 'Y',
    constraint fk_partici_meeting_no foreign key(meeting_no) references meeting(meeting_no) on delete cascade,
    constraint fk_partici_id foreign key(partici_id) references member(member_id) on delete cascade,
    constraint ck_partici_status check(status in ('Y','N'))
);

--참가자 테이블 시퀀스
create sequence seq_participation;

--자유게시판 테이블
create table user_board(
    board_no number, 
    title varchar2(50), 
    writer varchar2(15),
    content clob, 
    reg_date date default sysdate, 
    --vo에서는 Comment_count추가할것
    constraint pk_user_board_no primary key(board_no),
    constraint fk_writer foreign key(writer) references member(member_id) on delete set null
);
--자유게시판 시퀀스
create sequence seq_user_board;

--댓글 테이블
create table user_board_comment(
  comment_no number,
  comment_level number default 1,
  writer varchar2(15), --member참조
  content varchar2(2000),
  board_no number, --board 참조
  comment_ref number, --현재 테이블의 no참조
  reg_date date default sysdate,
  constraint pk_board_comment_no primary key(comment_no),
  constraint fk_board_comment_writer foreign key(writer) REFERENCES member(member_id) on delete set null,
  constraint fk_board_comment_board_no foreign key(board_no) REFERENCES user_board(board_no) on delete set null,
  constraint fk_board_comment_ref foreign key(comment_ref) REFERENCES user_board_comment(comment_no) on delete set null
);
--댓글 시퀀스
create sequence seq_user_board_comment;

--공지사항 게시판 테이블
create table admin_board(
    board_no number, 
    title varchar2(50), 
    writer varchar2(15),
    content clob, 
    reg_date date default sysdate, 
    constraint pk_admin_board_no primary key(board_no),
    constraint fk_admin_writer foreign key(writer) references member(member_id) on delete set null
);
--공지사항 시퀀스
create sequence seq_admin_board;

commit;






















