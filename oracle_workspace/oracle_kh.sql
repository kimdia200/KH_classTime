--=========================
--kh 계정
--=========================
show user;

--table sample 생성
create table sample(
    id number
);

--현재 계정의 소유 테이블 목록 조회
select * from tab;

--sample 테이블 조회
select * from sample;
--사원테이블
select * from employee;
--부서테이블
select * from department;
--직급 테이블
select * from job;
--지역테이블
select * from location;
--국가테이블
select * from nation;
--급여등급 테이블
select * from sal_grade;

/*
표 = table entity relation 데이터를 보관하는 객체
열 = column field attribute 세로, 데이터가 담길 형식
행 = row record tuple 가로, 실제 데이터
도메인 = domain 하나의 컬럼에 취할수 있는 값의 그룹(범위)
*/

--테이블 명세
--컬럼명   널여부    자료형    (널 여부에서 NOT NULL = 반드시 넣어줘라, NULL = 선택)
desc employee;
--describe 라고해도됨
describe employee;


--==========================================
--DATA TYPE(자료형)
--==========================================
/*
컬럼에 지정해서 값을 제한적으로 허용.
1. 문자형 varchar2 | char
2. 숫자형 number
3. 날짜/시간형 date
*/

--==========================================
--문자형
--==========================================
/*
1. 고정형 char(byte) : 최대2000byte
ex) char(10) 'korea' 
영소문자는 글자당1byte 이므로 실제 데이터의 크기는 5byte.
하지만 실제로는 고정형이기때문에 10byte로 저장됨.
ex) char(10) '안녕'
한글은 글자당 3byte(11g Xe)이므로 실제크기 6byte, 
하지만 실제로는 고정형이기 때무네 10byte로 저장됨.

2. 가변형 varchar2(byte) :  최대4000byte
ex) varchar(10) 'korea' 
영소문자는 글자당 1byte이므로 실제 크기는 5byte로. 가변형 5byte로 저장됨
ex) varchar(10) '안녕'
가변형 6byte로 저장됨

3.가변형 long : 최대 2GB
LOB타입(Large Object) 중의 CLOB(Character LOB)는 단일컬럼 최대 4GB까지 지원


고정형, 가변형 모두 지정한 크기 이상의 값은 추가할 수 없다.
*/

create table tb_datatype (
-- 컬럼명  자료형 널여부 기본값
    a char(10),
    b varchar(10)
);

--테이블 조회
select * -- *는 모든 컬럼
from tb_datatype; --테이블명

--데이터 추가 : 한행을 추가
insert into tb_datatype
values('hello', 'hello');

insert into tb_datatype
values('안녕', '안녕');

insert INTO tb_datatype
VALUES('1234','12345');

--에러발생 ORA-12899: value too large for column "KH"."TB_DATATYPE"."A" (actual: 15, maximum: 10)
--지정한 사이즈보다 큰 사이즈의 값을 넣어줘서
insert into tb_datatype
values('에브리바디', '안녕');

--데이터가 변경 (insert, update, delete)되는 경우, 메모리상에서 먼저처리된다
--commit 을 통해 실제 database에 적용해야한다
commit;

--lengthb(컬럼명):number - 저장된 데이터의 실제크기를 리턴
select a, lengthb(a), b, lengthb(b)
from tb_datatype;


--==========================================
--숫자형
--==========================================
/*
1. 정수, 실수를 구분치 않고 모두 number를 사용한다
number(p,s)로 사용한다
p=표현가능한 전체 자리수 (전체 자리수 라는게 핵심!!!)
s = p 중 소수점 이하자리수를 가르킨다 (p중에서 라는게 핵심!!!)
---------------------------------------------------------
값 : 1234.567 일경우
number              1234.567
number(7)           1235       (7,0)으로 인식하기 때문+ 자동반올림
number(7,1)         1234.6     자동 반올림 처리 되어서
number(7,-2)       1200       자동 반올림 처리 되어서
*/

create table tb_datatype_number(
    a number,
    b number(7),
    c number(7,1),
    d number(7,-2)
);

select * from tb_datatype_number;

insert into tb_datatype_number
values(1234.567, 1234.567, 1234.567, 1234.567);

--지정한 크기보다 큰 숫자는 ORA-01438: value larger than specified precision allowed for this column 발생
insert into tb_datatype_number
values(1234567890, 1234567.567, 123456.1, 1234.567);
--전체자리가 8자리수 이상이면 안됨
--

commit;
--마지막 commit 시점 이후 변경사항은 취소된다
rollback;


--==========================================
--날짜시간형
--==========================================
/*
1. date 타입 = 년월일시분초

2.timestamp 타입 = 년월일시분초 + 밀리초 + 지역대 까지 저장가능
*/

create table tb_datatype_date (
    a date,
    b timestamp
);

select * from tb_datatype_date;

insert into tb_datatype_date
VALUES (sysdate, systimestamp);

--날짜형 - 날짜형 = 숫자 (1 = 하루)
--to_char 는 날짜를 문자열로 표시해주는 함수
select to_char(a,'yyyy/mm/dd hh24:mi:ss'),
         to_char(a+1,'yyyy/mm/dd hh24:mi:ss'),
         b
from tb_datatype_date;

--날짜형 +- 숫자(1=하루) = 날짜형 
select sysdate-a
from tb_datatype_date;

--to_date 는 문자열을 날짜형으로 변환해주는 함수
select to_date('2021/01/23')-a
from tb_datatype_date;

--dual 가상테이블
select (sysdate +1)-sysdate 
from dual;


--==========================================
--DQL (data query language)
--데이터 조회(검색)을 위한 언어
--Select문
--쿼리 조회 결과를 ResultSet(결과집합)라고 하며, 0행 이상을 포함한다
--from 절의 조회하고자 하는 테이블을 명시
--where절에 의해 특정행을 filtering가능.
--select절에 의해 컬럼을 filtering 또는 추가도 가능하다(가공할수 있다)
--order by절에 의해서 행을 정렬할 수 있다.
--==========================================
/*
구조    (괄호 = 처리순서)

select 컬럼명 (5) 필수
from 테이블명 (1) 필수
where 조건절 (2) 선택 
group by 그룹기준컬럼 (3) 선택
having 그룹조건절 (4) 선택
order by 정렬조건 (6) 선택

*/
select *
from employee
where dept_code = 'D9' --데이터는 대소문자 구분
order by emp_name asc; --오름차순 정렬 (내림차순 = desc)

--1. job테이블에서 job_name컬럼정보만 출력
select job_name
from job;

--2.department테이블에서 모든컬럼을 출력
select *
from department;

--3.employee테이블에서 이름, 이메일, 전화번호, 입사일을 출력
select emp_name, email, phone, hire_date
from employee;

--4.employee테이블에서 급여가 2,500,000 이상인 사람의 이름과 급여를 출력
select emp_name, salary
from employee
where salary >= 2500000;

--5.employee테이블에서 급여가 3,500,000 이상이면서, 직급 코드가 'J3'인 사원을 조회
--(&& || 이 아닌 and or만 사용가능)
select *
from employee
where salary >= 3500000 and job_code = 'J3';

--6.employee테이블에서 현재 근무중인 사원의 이름 오름차순으로 정렬해서 출력
select *
from employee
where quit_yn = 'N'
order by emp_name asc;
