--semi
drop table 모임채팅;
drop table 블랙리스트;
drop table 자주묻는질문;
drop table 인증;
drop table 공지사항;
drop table 댓글;
drop table 자유게시판;
drop table 참가자;
drop table 첨부파일;
drop table 모임;
drop table 회원;
drop table 카테고리;
drop table 지역;


--지역 테이블
create table 지역(
    지역코드 varchar2(10),
    지역명 varchar2(30),
    constraint pk_location_code primary key(지역코드)
);

insert into 지역 values('L1', '서울');
insert into 지역 values('L2', '경기');
insert into 지역 values('L3', '인천');
insert into 지역 values('L4', '대전·충청');
insert into 지역 values('L5', '대구·경북');
insert into 지역 values('L6', '부산·경남');
insert into 지역 values('L7', '울산');
insert into 지역 values('L8', '광주');

--카테고리 테이블
create table 카테고리(
    카테고리코드 varchar2(10),
    카테고리명 varchar2(20),
    constraint pk_category_code primary key(카테고리코드)
);

insert into 카테고리 values('C1', '음악');
insert into 카테고리 values('C2', '게임');
insert into 카테고리 values('C3', '운동');
insert into 카테고리 values('C4', '요리');
insert into 카테고리 values('C5', '독서');
insert into 카테고리 values('C6', '재테크');
insert into 카테고리 values('C7', '자동차');

--회원 테이블
create table 회원(
    아이디 varchar2(50),
    비밀번호 varchar2(100) not null,
    이름 varchar2(100) not null,
    이메일 varchar2(100) not null,
    연락처 varchar2(11) not null,
    이벤트동의1 varchar2(2),
    이벤트동의2 varchar2(2),
    가입일자 date default sysdate,
    회원권한 varchar2(2) default 'U' not null,
    constraint pk_member_id primary key(아이디),
    constraint ck_member_event1 check(이벤트동의1 in ('T','F')),
    constraint ck_member_event2 check(이벤트동의2 in ('T','F')),
    constraint ck_member_role check(회원권한 in ('U','A'))
);


--미팅 테이블
create table 모임(
    모임번호 number,
    제목 varchar2(500) not null,
    작성자 varchar2(50),
    내용 clob not null,
    --첨부파일도
    등록일자 date default sysdate,
    장소 varchar2(100),
    시간 date,
    최대인원 number,
    비용 number,
    카테고리코드 varchar2(10),
    지역코드 varchar2(10),
    constraint pk_모임번호 primary key(모임번호),
    constraint fk_meeting_작성자 foreign key(작성자) references 회원(아이디) on delete cascade,
    constraint fk_meeting_category foreign key(카테고리코드) references 카테고리(카테고리코드),
    constraint fk_meeting_location foreign key(지역코드) references 지역(지역코드)
    
);


--첨부파일테이블 생성
create table 첨부파일 (
    첨부파일번호 number primary key,
    모임번호 number not null,
    원본명 varchar2(255) not null,
    새이름명 varchar2(255) not null,
    상태 char(1) default 'Y',
    constraint fk_attach_board_no foreign key(모임번호) references 모임(모임번호) on delete cascade,
	constraint ck_status check(상태 in ('Y','N'))
);


--미팅 참가자 테이블
create table 참가자(
    참가자번호 number primary key,
    모임번호 number,
    참가자아이디 varchar2(50),
    등록일자 date default sysdate,
    상태 char(1) default 'Y',
    constraint fk_partici_모임번호 foreign key(모임번호) references 모임(모임번호) on delete cascade,
    constraint fk_partici_id foreign key(참가자아이디) references 회원(아이디) on delete cascade,
    constraint ck_partici_status check(상태 in ('Y','N'))
);


--자유게시판 테이블
create table 자유게시판(
    게시판번호 number, 
    제목 varchar2(50), 
    작성자 varchar2(15),
    내용 clob, 
    등록일자 date default sysdate, 
    조회수 number default 0,
    --vo에서는 Comment_count추가할것
    constraint pk_user_board_no primary key(게시판번호),
    constraint fk_작성자 foreign key(작성자) references 회원(아이디) on delete set null
);

--댓글 테이블
create table 댓글(
  댓글번호 number,
  댓글레벨 number default 1,
  작성자 varchar2(15), --member참조
  내용 varchar2(2000),
  게시판번호 number, --board 참조
  참조댓글 number, --현재 테이블의 no참조
  등록일자 date default sysdate,
  constraint pk_board_comment_no primary key(댓글번호),
  constraint fk_board_comment_작성자 foreign key(작성자) REFERENCES 회원(아이디) on delete set null,
  constraint fk_board_comment_board_no foreign key(게시판번호) REFERENCES 자유게시판(게시판번호) on delete set null,
  constraint fk_board_comment_ref foreign key(참조댓글) REFERENCES 댓글(댓글번호) on delete set null
);

--공지사항 게시판 테이블
create table 공지사항(
    공지번호 number, 
    제목 varchar2(50), 
    작성자 varchar2(15),
    내용 clob, 
    등록일자 date default sysdate,
    조회수 number default 0,
    constraint pk_admin_board_no primary key(공지번호),
    constraint fk_admin_작성자 foreign key(작성자) references 회원(아이디) on delete set null
);

--비밀번호변경 인증값 테이블
create table 인증(
  번호 number,
  아이디 varchar2(15),
  인증코드 varchar2(100),
  constraint pk_pwd_certification_no primary key(번호),
  constraint fk_pwd_certification_id foreign key(아이디) references 회원(아이디) on delete cascade
);

--FAQ
create table 자주묻는질문(
    번호 number,
    제목 varchar2(200),
    내용 varchar2(4000),
    constraint pk_faq_no primary key(번호)
);

--블랙리스트 테이블
create table 블랙리스트(
    번호 number,
    이메일 varchar2(100)
);

--meeting테이블에 추가적으로 달아줄 실시간채팅테이블
create table 모임채팅(
    번호 number,
    모임번호 number,
    작성자 varchar2(100),
    내용 varchar2(2000),
    등록일자 date default sysdate,
    constraint pk_meeting_chat_no primary key(번호),
    constraint fk_meeting_chat_mNo foreign key(모임번호) references 모임(모임번호) on delete cascade,
    constraint fk_meeting_chat_작성자 foreign key(작성자) references 회원(아이디) on delete cascade
);
commit;