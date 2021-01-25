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

drop table tb_datatype;

create table tb_datatype (
-- 컬럼명  자료형  널여부 기본값
    a char(10) NOT NULL,
    b varchar(10)
);

desc tb_datatype;

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
값 : 1234.567 일경우  (내가 가지고 저장할 숫자의 길이가 중요함)
number              1234.567
number(7)           1235       (7,0)으로 인식하기 때문+ 자동반올림
number(7,1)         1234.6     자동 반올림 처리 되어서
number(7,-2)       1200       자동 반올림 처리 되어서
*/

create table tb_datatype_number(
    a number,
    b number(7,0),
    c number(7,1),
    d number(7,-2)
);

select * from tb_datatype_number;

insert into tb_datatype_number
values(1234.567, 1234567.567, 12345678.5678, 1234.567);
--                                       8+1=9        1200       

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
VALUES (sysdate, systimestamp);  -- 현재시간 넣는 함수 두개, format을 정해주지않으면 기본형이 다르다

--날짜형 - 날짜형 = 숫자 (1 = 하루)
--to_char 는 날짜를 문자열로 표시해주는 함수(파라미터가 두개인경우)
select 
        a,
        to_char(a,'yyyy/mm/dd hh24:mi:ss'),
        to_char(a+1,'yyyy/mm/dd hh24:mi:ss'),
        b,
        to_char(b,'yyyy/mm/dd hh24:mi:ss'),
        to_char(b+1,'yyyy/mm/dd hh24:mi:ss')
from tb_datatype_date;

--날짜형 +- 숫자(1=하루) = 날짜형 
select sysdate-a
from tb_datatype_date;

--to_date 는 문자열을 날짜형으로 변환해주는 함수(파라미터가 한개인경우)
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
select  *
from employee
where dept_code = 'D9' --데이터는 대소문자 구분
order by emp_name asc; --오름차순 정렬 (내림차순 = desc)

--1. job테이블에서 job_name컬럼정보만 출력
select job_name
from job;

--2.department테이블에서 모든컬럼을 출력
select department.*
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



--==========================================
--SELECT
--==========================================
--table의 존재하는 컬럼
--가상컬럼(산술연산)
--임의의 literal (어떤 값)
--각 컬럼은 별칭(alias)를 가질 수 있다.
--별칭에 공백, 특수문자가 있거나 숫자로 시작하는경우 쌍따옴표 필수

select emp_name as "사원명", --별칭사용(쌍따옴표)
        phone "전화번호", --별칭사용시 as생략가능
        salary 급여, --별칭사용시 쌍따옴표도 생략가능
        salary*12 "연  봉", --산술연산, 별칭에 공백이 포함된 경우 반드시 쌍따옴표
        123, --literal
        '안녕' --literal
from employee;

--실급여 : salary + (salary*bonus)
select emp_name, 
         salary,
         bonus,
         salary + (salary*bonus) 실급여
from employee;

--null값과는 산술연산을 할 수 없다. 그 결과는 무조건 null이다.
--null % 1(X), 나머지연산자는 사용불가
select null+1,
         null-1,
         null*1,
         null/1
from dual; --1행

--nvl(col, null일때 값)   null처리 함수
--col의 값이 null이 아니면 (col)의 값 리턴
--col의 값이 null이면 , (null일때 값)을 리턴
--3항 연산자처럼 생각하면 쉬움
select emp_name, 
         salary,
         bonus,
         salary + (salary * nvl(bonus, 0)) 실급여   --nvl 이용해서 null처리해줌
from employee;


--distinct 중복제거용 키워드
--select절에 단 한번 사용가능하다
select distinct job_code
from employee;


--직급 코드를 중복없이 출력
select distinct job_code, dept_code
from employee;

--여러 컬럼 사용시 컬럼을 묶어서 고유한 값으로 취급한다
select distinct job_code, dept_code
from employee;

--문자 연결연산자 ||
-- +는 산술연산만 가능하다.
select '안녕'+'하세요' --에러뜸, +연산은 숫자만 가능하기 때문
from dual;

select '안녕'||'하세요'||123  --예시
from dual;

select emp_name || ' (' || phone || ')'  "개인정보"
from employee;


--==========================================
--WHERE
--==========================================
--테이블의 모든 행 중 결과집합에 포함될 행을 필터링한다.
--특정행에 대해 true(포함) | false(제외) 결과를 리턴한다
/*
WHERE 절에서의 연산자
같다                                = 
다르다                            <> != ^=
between A and B             A이상 B이하 범위연산
and
or
not
like, not like                    문자 패턴연산
is null, is not null ,            null여부 검사
in, not in                        값목록에 포함여부
*/

--부서코드가 D6인 사원조회

select *
from employee
where dept_code = 'D6';
--where dept_code <> 'D6';
--where not dept_code= 'D6';

--급여가 2,000,000보다 많은 사원 조회
select *
from employee
where salary > 2000000;

--부서코드가 D6 이거나 D9인 사원조회
select emp_name, dept_code
from employee
where dept_code ='D6' or dept_code='D9';

--날짜형도 크기 비교 가능
--과거<미래
select emp_name, hire_date
from employee
where hire_date < '2000/01/01'; 
--날짜 형식의 문자열은 자동으로 날짜형으로 형변환됨, 안되는경우 명시적 형변환
--hire_date가 2000/01/01보다 이전인 사람

--20년 이상 근무한 사원조회, 퇴사 X인사람
select *
from employee
where quit_yn='N' and to_date('2021/01/22')-hire_date >=365*20;  --명시적 형변환 사용
--where quit_yn='N' and sysdate-hire_date >=365*20; 


--범위 연산
--급여가 200만원이상 400만원 이하인 사원 조회
select emp_name, salary
from employee
where salary BETWEEN 2000000 AND 4000000; --이상 이하 인경우에만 사용 가능
--where salary >= 2000000 and salary <= 4000000; --좀더 세부적 설정가능

--1990/01/01~ 2001/01/01 인 사원 조회(사원명, 입사일)
select emp_name, hire_date
from employee
where hire_date >= '1990/01/01' and hire_date <= '2001/01/01';
--where hire_date between '1990/01/01' and '2001/01/01';
--where hire_date between to_date('1990/01/01') and to_date('2001/01/01');

--like, not like
--문자열 패턴 비교 연산
--wildcard : 의미를 가지는 특수문자, like, not like에서만 작동
--  _(언더스코어) = 아무문자 1개, 글자수제한
--  %    (퍼센트)   = 아무문자 0개 이상

select emp_name
from employee
where emp_name like '전%'; --전으로 시작, 그뒤로는 0개이상의 문자가 존재하는가?
--전, 전차, 전진, 전형돈, 등등 무궁무진
--파전(X)

select emp_name
from employee
where emp_name like '전__'; --전으로 시작하고 연달아 두개의 문자가 존재하는가?
--전형동, 전전전.... 전으로 시작하는 세글자 가능
--전, 전진, 파전, 전당포아저씨 (XXXXXXX) 불가능


--문제!) 이름에 가운데 글자가 '옹'인 사원 조회. 단, 이름은 3글자이다.
select emp_name
from employee
where emp_name like '_옹_';

--문제!) 이름에 '이'가 들어가는 사원 조회;
select emp_name
from employee
where emp_name like '%이%';

--email컬럼 값의 '_'이전 글자가 3글자인 이메일 조회
select email
from employee
--where email like '____%'; --4글자 이후 0개이상의 문자열이 뒤따르는가?? (결론적으로 잘못되었음)
where email like '___\_%' escape '\'; --escaping 문자 등록 
--escape문자는 custom해줄수있다는것이 자바와 차이, escaping문자는 데이터에 존재하지 않는 문자로 해줄것!

-- in, not in     값목록에 포함여부
--부서코드에 따른 값 출력       총24명
select emp_name, dept_code
from employee
order by dept_code;


--부서코드가 D6 또는 D8인 사원 조회      총6명
select emp_name, dept_code
from employee
where dept_code in ('D6', 'D8'); --갯수 제한 없이 값 나열 해주면된다
--where dept_code = 'D6' or dept_code = 'D8';

--부서코드 D6또는 D8이 아닌 사원 조회    총16명(왜?? 2명은 널이기때문에) null은 계산 안됨!!
select emp_name, dept_code
from employee
--where dept_code not in ('D6', 'D8'); 
--where not dept_code = 'D6' and not dept_code = 'D8';
where dept_code != 'D6' and dept_code != 'D8';


--is null, is not null : null비교연산
--인턴 사원 조회(인턴사원은 부서코드가 null이라고 가정)
--null값은 산술연산, 비교연산 모두 불가능 하다
select emp_name, dept_code
from employee
where dept_code is null;
--where dept_code is not null;
--where dept_code = null   이런식으로는 사용불가능함

--D6, D8부서원이 아닌 사원을 조회 하는데 인턴사원도 포함해봐라
select emp_name, dept_code
from employee
where dept_code is null or dept_code not in('D6', 'D8');

--nvl버전
select emp_name, dept_code
from employee
where nvl(dept_code, 'D0' ) not in('D6','D8'); --nvl을 이용해서 NULL일 경우 임시값을 주고 검색
--어떤 튜플을 출력해줄기 검사만 해준거기 때문에 원본값은 바뀌지 않는다!


--==========================================
--ORDER BY
--==========================================
--select구문 중 가장 마지막에 처리.
--지정한 컬럼 기준으로 결과집합을 정렬해서 보여준다.

--number       ex)0 < 10
--string          ex)ㄱ< ㅎ, a < z  사전적 순서
--date           ex) 과거 < 현재 < 미래 시간이 흐를수록 쌓여져가는 개념
--null값 위치를 결정가능 : nulls first | nulls last

select *
from employee
order by emp_id asc;    --oracle official 정렬기준을 설정하지 않은경우 출력 순서를 보장하지 않는다(될수는 있으나 보장은 x)
--오름차순 asc(생략가능), 내림차순 desc
--복수개의 컬럼 차례로 정렬가능
select emp_id, emp_name, dept_code, job_code, hire_date
from employee
order by dept_code desc nulls last, job_code asc; 
--기준1 : dept_code로 내림차순(널값은 마지막에)
--기준2 : job_code로 오름차순

--alias사용가능
select emp_id 사번,
        emp_name 사원명
from employee
order by 사원명;

--1부터 시작하는 컬럼순서 사용가능
select *
from employee
order by 9 desc; --사실 이사용 방법은 비추(컬럼 추가, 삭제시 영향을 받기 때문)


--==========================================
--BUILT-IN FUNCTION
--==========================================
--일련의 실행 코드 작성해두고 호출해서 사용함
--반드시 하나의 리턴값을 가짐.

--1. 단일행 함수 : 각 행마다 반복호출되어서 호출된 수 만큼 결과를 리턴함
--      a. 문자 처리 함수
--      b. 숫자 처리 함수
--      c. 날짜 처리 함수
--      d. 형변환 함수
--      e. 기타 함수
--2. 그룹함수 : 여러행을 그룹핑 한 후, 그룹당 하나의 결과를 리턴

------------------------------------------------------------------------
--단일행 함수
------------------------------------------------------------------------

--******************************************************************************************
-- a. 문자 처리 함수
--******************************************************************************************

--length(col): number
--문자열의 길이를 리턴
select emp_name, length(emp_name)
from employee;
--where절에서도 사용가능하다
select emp_name, email
from employee
where length(email) < 15;

--lengthb(col)
--값의 byte수 리턴
select emp_name, lengthb(emp_name)
from employee;

--instr(string, search[ , position[ , occurence ] ] )
--string에서 search가 위치한 index를 반환
--oracle에서는 1-based index. 인덱스가 1부터 시작한다
--JAVA의 String.indexOf와 비슷
select instr('kh정보교육원 국가정보원', '정보'), --몇번째 인덱스에 위치하는지 알수있음
         instr('kh정보교육원 국가정보원', '안녕'), --값이 없다면 0을 리턴
         instr('kh정보교육원 국가정보원', '정보', 5), --5번째 이후에 처음 표시된곳 리턴(리턴시에는 처음부터인덱스값으로 리턴)
         instr('kh정보교육원 국가정보원 정보문화사', '정보', 1 ,3), --1번지부터 검색하되 3번째 나온친구의 인덱스값을 알려줘~
         instr('kh정보교육원 국가정보원 정보문화사', '정보', -1), --뒤에서부터 찾아줘~
         instr('kh정보교육원 국가정보원 정보문화사', '정보', -1, 3)   --뒤에서부터 값을 찾는데 세번째 나온친구의 인덱스값을 알려줘~
from dual;

--email컬럼값중 '@'의 위치는? (이메일, 인덱스)
select email, instr(email, '@') "@인덱스"
from employee;


--substr(string, startindex[, length] )
--string에서 startIndex부터 length개수만큼 잘라내어 리턴
--length생략 시에는 문자열 끝까지 반환

select substr('show me the money', 6, 2), --me
         substr('show me the money', 6), --length부분은 생략가능, me the money
         substr('show me the money', -5, 3) --뒤에서 5번째 인덱스부터 3개 출력
from dual;


--@실습문제 : 사원명에서 성(1글자로가정)만 중복없이 사전순으로 출력
select DISTINCT substr(emp_name, 1, 1) "성"
from employee
order by "성";
--order by 1;

--lpad | rpad(string, byte[, padding_char])
-- byte수의 공간에 string을 대입하고 남은공간은 padding_char로 채워라
--왼쪽에 채우는건 lpad, 오른쪽에 채우는건 rpad
-- padding char는 생략시 공백문자

select lpad(email, 20, '#'),
         rpad(email, 20, '#'),
         '[' || lpad(email, 20, '#') || ']',
         '[' || rpad(email, 20, '#') || ']',
         '[' || lpad(email, 20) || ']',
         '[' || rpad(email, 20) || ']'
from employee;


--@실습문제 : 남자사원만 사번, 사원명, 주민번호, 연봉조회
--주민번호 뒤 6자리는 ****** 숨김처리 할 것;

select emp_id 사번, emp_name 이름 , rpad(substr(emp_no,1,8), 14, '*') 주민번호,salary*(1+nvl(bonus,0))*12 연봉
from employee
where substr(emp_no, 8,1) in ('1', '3');

select bonus
from employee;


--******************************************************************************************
-- b. 숫자 처리 함수
--******************************************************************************************

--mod(피젯수, 젯수)
--나머지 함수(자바에서의 %같은 나머지 연산자가 없어서 mod함수 이용)
select mod(10,2),
        mod(10,3)
from dual;

--입사년도가 짝수인 사원 조회
select emp_name,
        extract(year from hire_date) year --날짜함수 : 년도 추출
from employee
where mod(extract(year from hire_date), 2) = 0
order by year;


--ceil(number)
--소수점 기준으로 올림.
--부동소수점 방식 이기때문에 따로 몇번째에서 올림처리해주는 파라미터없음
select ceil(123.456),
        ceil(123.456*100)/100 --부동소수점 방식이라서
from dual;

--floor(number)
--소수점 기준으로 버림
--똑같은 부동소수점 방식
select floor(456.789),
        floor(456.789*10)/10
        from dual;
        
--round(number[, position])
--올림, 버림 과는 다르게 두번째 파라미터 설정 가능(선택)
--position값을 입력하지 않으면 소수점 첫째자리에서 반올림
--position기준(정수쪽은 음수,기본값0, 소수점기준)으로 반올림 처리
select round(234.567),
        round(234.567,2),
        round(234.567,-1)
from dual;

--trunc(number[, position])
--버림
select trunc(123.567),
        trunc(123.567, 2)
from dual;

--******************************************************************************************
-- c. 날짜 처리 함수
--******************************************************************************************

--add_months(date, number)
--date기준으로 몇달(number)전후의 날짜형을 리턴
--1월30일 +1달 = 2월 30일 이 되는데, 2월은28일 까지라서 2/28로 나옴(말일 언저리 조심)
select sysdate,
        add_months(sysdate,1),
        add_months(sysdate,-1),
        add_months(sysdate + 5,1)
from dual;

--months_between(미래, 과거)
--두 날짜형의 개월수 차이를 리턴한다.

select sysdate,
        to_date('2021/07/08'),
        trunc(months_between(to_date('2021/07/08'), sysdate),1) diff
from dual;

--이름, 입사일, 근무개월수(n개월), 근무개월수(n년 m개월)
select emp_name, hire_date, 
        trunc(months_between(sysdate,hire_date))||'개월' "근무 개월수(n개월)",
        trunc(months_between(sysdate,hire_date)/12)||'년 ' || trunc(mod(months_between(sysdate,hire_date),12))||'개월' "근무 개월수(n년 m개월)"
from employee;

--extract(year | month | day | hour | minute | second from date) : number
--날짜형 데이터에서 특정필드만 숫자형으로 리턴.
select extract(year from sysdate) yyyy,
        extract(month from sysdate) mm, --1~12월
        extract(day from sysdate) dd,
        extract(hour from cast (sysdate as timestamp)) hh,
        extract(second from cast (sysdate as timestamp)) mm,
        to_char(sysdate,'mm'),
        to_number(to_char(sysdate,'mm')) --이거 물어보기
from dual;

--trunc(date)
--시분초 정보를 제외한 년월일 정보만 리턴
select to_char(sysdate, 'yyyy/mm/dd hh24:mi:ss') date1,
        to_char(trunc(sysdate), 'yyyy/mm/dd hh24:mi:ss') date2
from dual;


--******************************************************************************************
-- d. 형변환 함수
--******************************************************************************************
/*      to_char            to_date
        ------>           ------->
    number          string          date
        <------           <-------
        to_number          to_char
*/

--to_char 리턴타입 = string
--1.date를 string으로
select to_char(sysdate, 'fmyyyy/mm/dd hh24:mi:ss am') "now", --fm을 형식 맨앞에 붙여주면 불필요한 0을 표시 안해준다
        to_char(sysdate, 'yyyy/mm/dd(day) hh12:mi:ss am') "now",  --hh,hh12 = 12시간제, hh24=24시간제
        to_char(sysdate, 'yyyy"년" mm"월" dd"일"') "now"  --한글이 필요하면 쌍따옴표
from dual;

--2.number를 string으로
--숫자포멧으로는 9(#느낌)또는 0(0느낌)을 사용함
--fm=공백, 불필요한0 제거
--9는 fm으로 불필요한 0을 제거할수 있지만 0을 사용할 경우 제거가 불가능하다
select to_char(1234567, 'fmL9,999,999,999,999,999') won,  -- L은 지역화폐
        to_char(1234567, 'fmL9,999') won, --자릿수가 모자라 오류가남(#으로 표시됨)
        to_char(123.4, '999.99'), --소수점 이상의 빈자리는 공란, 소수점이하의 빈자리는 0처리
        to_char(123.4, '0000.00'),
        to_char(123.4, 'fm0000.99'),
        to_char(123.4, 'fm9999.00'),
        to_char(123.4, '0000.00')
from dual;

--이름, 급여(3자리 콤마), 입사일(1990-9-3(화))를 조회
select emp_name,
        to_char(salary, 'fmL999,999,999,999') "급여",
        to_char(hire_date, 'fmyyyy"-"mm"-"dd(dy)') "입사"  --dy=일,월,화,수,목,금,  day = 일요일,월요일,화요일.....
from employee;

--to_number(string, format) 리턴타입 = number
--밑에 두예제는 ''안에 ,라든지 ￦라는 추가적 요소가 있어서 자동형변환이 되지 않음
select to_number('1,234,567', '9,999,999') +100 ,  --문자를 숫자형으로 바꿔줬기때문에 산술연산이 가능하다
        --'1,234,567' +100 은 문자열에 산술연산을 하는것이므로 불가능하다(에러)
        to_number('￦3,000','L9,999,999')+100   --특수문자 ㄹ한자
from dual;

--자동형변환 지원
select '1000' + 100,
        '99'+'1',
        '99'||'1' -- 문자열연산자를 쓰면 당연히 문자로 인식
from dual;

--to_date(string, format) 리턴타입 = date
select to_date('2020/09/09', 'yyyy/mm/dd') +1
from dual;


--@실습문제
--1시간 : 1/24
--1분 : 1/(24*60)
--1초 : 1/(24*60*60)
select 
        --'2021/07/08 21:50:00'을 2시간후의 날짜 정보를 yyyy/mm/dd hh24:mi:ss 형식으로 출력
        to_char(to_date('2021/07/08 21:50:00','yyyy/mm/dd hh24:mi:ss')+2/24, 'yyyy/mm/dd hh24:mi:ss') 내답안1,
        --현재시각 기준 1일 2시간 3분 4초후의 날짜 정보를 yyyy/mm/dd hh24:mi:ss 형식으로 출력
        to_char(sysdate+(1 + 2/24 + 3/60/24 + 4/60/60/24), 'yyyy/mm/dd hh24:mi:ss') 내답안2,
        
        to_char(
            to_date('2021/07/08 21:50:00','yyyy/mm/dd hh24:mi:ss')+2/24, 
            'yyyy/mm/dd hh24:mi:ss'
        ) 강사님답안1,
       to_char( sysdate + 1 + (2/24) + (3/(24*60)) + (4/(24*60*60)), 'yyyy/mm/dd hh24:mi:ss') 강사님답안2 ,
        sysdate + 1 + (2/24) + (3/(24*60)) + (4/(24*60*60))
from dual;


--기간타임
--interval year to month : 년월 기간
--interval date to second : 일시분초 기간

--기존 사용방식
select to_char(add_months(sysdate,14) , 'yyyy/mm/dd hh24:mi:ss'), --14개월후
        --1년 2개월 3일 4시간 5분 6초후 조회
        to_char(add_months(sysdate,14)+3+(4/24)+(5/24/60)+(6/24/60/60), 'yyyy/mm/dd hh24:mi:ss' )
from dual;
--interval 사용방식
select to_char(sysdate + to_yminterval('+01-02'), 'yyyy/mm/dd hh24:mi:ss') "14개월 후", -- (+-중 +가 기본값으로 생략도 가능하다)
        to_char(sysdate + to_dsinterval('3 04:05:06'), 'yyyy/mm/dd hh24:mi:ss') "3일 4시간5분 6초후",--ds = daySeconds
        to_char(sysdate + to_yminterval('+01-02') + to_dsinterval('3 04:05:06'), 'yyyy/mm/dd hh24:mi:ss') "종합"
from dual;
    
--numToDsInterval(diff, unit)
--numToYmInterval(diff, unit)
--diff : 날짜차이
--unit : year | month | day | hour | second
select extract(day from
        numtodsinterval(
            to_date('20210708', 'yyyymmdd') - sysdate,
            'day' --unit
        )) diff,
        numtodsinterval(
            to_date('20210708', 'yyyymmdd') - sysdate,
            'day'
        ) ddddd
from dual;


--******************************************************************************************
-- e. 기타함수
--******************************************************************************************

--null처리 함수
--nvl(col, nullvalue)
--nvl2(col, notnullvalue, nullvalue)
--col값이 null이 아니면 두번재 인자를 리턴, null이면 세번째 인자를 리턴

select emp_name,
        bonus,
        nvl(bonus,0) nvl1,
        nvl2(bonus,'있음', '없음') nvl2
from employee;

--선택함수1
--decode(expr표현값, 값1, 결과값1, 값2, 결과값2, ............마지막에는 기본값)

select emp_name, emp_no,
        decode(substr(emp_no,8,1), '1', '남', '2', '여', '3', '남', '4', '여') 성별, --기본값 사용하지 않음
        decode(substr(emp_no,8,1), '1', '남', '2', '여', '3', '남', '여') 성별 --기본값 = '여'
from employee;

--직급코드에 따라서 J1 = 대표, J2,J3 = 임원 , 나머지 = 평사원
select job_code,
        decode(job_code, 'J1', '대표', 'J2', '임원', 'J3', '임원', '평사원') 직급
from employee;

--where절에도 사용가능
--여사원만 조회
select emp_name, emp_no
from employee
where decode(substr(emp_no,8,1), '1', '남', '2', '여', '3', '남', '4', '여') = '여';


--선택함수2
--case
/*
type1 (decode와 유사)

case 표현식
    when 값1 then 결과1
    when 값2 then 결과2
    ......
    [else 기본값]
    end


type2 

case
    when 조건식1 then 결과1
    when 조건식2 then 결과2
    ......
    [else 기본값]
    end

*/


select emp_no,
        --case type1
        case substr(emp_no,8,1)
            when '1' then '남'
            when '2' then '여'
            when '3' then '남'
            else '여'
            end 성별,
        case
            when substr(emp_no,8,1) = '1' then '남'
            when substr(emp_no,8,1) = '2' then '여'
            when substr(emp_no,8,1) = '3' then '남'
            else '여'
            end 성별,
        case
            when substr(emp_no,8,1) in ('1','3') then '남'
            else '여'
            end 성별,
        job_code,
        case
            when job_code = 'J1' then '대표'
            when job_code in ('J2', 'J3') then '임원'
            else case
                        when substr(emp_no,8,1) in ('1','3') then '남자 평사원'
                        else '여자 평사원'
                        end
            end 직급
from employee;


------------------------------------------------------------------------
-- GROUP FUNCTION
------------------------------------------------------------------------
--모든행을 그룹핑하고, 그룹당 하나의 결과를 리턴하는 함수
--모든 행을 하나의 그룹, 또는 group by를 통해서 세부그룹 지정이 가능하다.

--sum(col)
 select sum(salary),
         sum(bonus), --null인 컬럼은 제외하고 누계처리 nvl을 사용해주지 않아도됨 (count 숫자가 중요한 avg같은경우는 다름)
         sum( salary + ( salary * nvl(bonus, 0))) -- 가공된 컬럼도 그룹함수가능
 from employee;
 --select emp_name, sum(salary) from employee
 --!!!!!!!!!!!!!그룹함수의 결과와 일반컬럼을 동시에 조회할 수 없다.
 --ORA-00937: not a single-group group function
 
 --다른 그룹 함수들과는 동시에 사용가능하다.
 
 --avg(col)
 --평균
 select to_char(round(avg(salary),1), 'fmL999,999,999,999.9') avg
 from employee;
 
 --부서코드가 D5인 부서원의 평균급여 조회
 select to_char(round(avg(salary),1), 'fmL999,999,999,999.9') avg
 from employee
 where dept_code = 'D5';

--남자사원의 평균 급여조회
 select to_char(round(avg(salary),1), 'fmL999,999,999,999.9') avg
 from employee
 where substr(emp_no, 8, 1 ) in ('1','3');

--count(col)
--null이 아닌 컬럼의 개수
-- * 모든 컬럼, 즉 하나의 행을 의미
select count(*),
        count(emp_no),
        count(emp_name),
        count(salary),
        count(bonus) "count(bonus)",-- bonus 컬럼은 null이 존재하는 컬럼
        count(nvl(bonus,'0')) "count(nvl(bonus))"-- nvl을 써주면 이런식으로도 표현가능
from employee;

--보너스를 받는 사원수 조회
select count(bonus)
from employee
where bonus is not null;


--오 오 ~~~~~~~~where 절 사용하지 않고 보너스 받는사람 숫자 구하기
select 
        sum(
            case
                when bonus is not null then 1
                when bonus is null then 0
                end
        ) sum,
        sum(
            case nvl2(bonus, 1, 0)
                when 1 then 1
                when 0 then 0
                end
        ) sum,
        sum(
            decode(nvl2(bonus,1,0), 1, 1, 0)
        ) sum
from employee;

--사원이 속한 부서 총수(중복없음)
select count(distinct dept_code)
from employee;

--max(col) | min(col)
--숫자, 날짜(과거->미래), 문자(ㄱ->ㅎ, a->z)
select max(salary), min(salary),
        max(hire_date), min(hire_date),
        max(emp_name), min(emp_name)
from employee;

















