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