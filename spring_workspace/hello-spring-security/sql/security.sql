--------------------------------------------
-- spring-security.sql
--------------------------------------------

--member
select  * from member;
desc member;

--authority 테이블 생성
create table authority(
    id varchar2(20) not null,  -- 회원아이디
    authority varchar2(20) not null, --권한
    constraint pk_authority primary key (id, authority), --중요
    constraint fk_authority_member_id foreign key(id) references member(id)
);

--honggd, ROLE_USER
--honggd, ROLE_ADMIN  두개의 값이 한쌍으로 pk가 됨

insert into authority values('qwerty', 'ROLE_USER');
insert into authority values('honggd', 'ROLE_USER');
insert into authority values('admin', 'ROLE_USER');
insert into authority values('admin', 'ROLE_ADMIN');

commit;
select * from authority;


--회원정보 조회
select * from member where id = 'admin';

--회원권한 조회
select * from authority where id = 'admin';

--security의 remember-me 사용을 위한 persistent_logins 테이블 생성
--반드시 아래와 같이 구조를 잡아야 security에서 인식함
create table persistent_logins(
    username varchar2(64) not null,
    series varchar2(64) primary key,
    token varchar2(64) not null, --username, password, expiry time에 대한 hashing값
    last_used timestamp not null
);

select * from persistent_logins;







