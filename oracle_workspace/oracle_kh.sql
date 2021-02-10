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
        extract(minute from cast (sysdate as timestamp)) mi,
        extract(second from cast(sysdate as timestamp)) ss,
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

--나이 추출시 주의점
--현재년도-탄생년도 +1 => 한국식 나이
select emp_name,
        emp_no,
        substr(emp_no,1,2),
        extract(year from to_date(substr(emp_no,1,2),'yy')) "yy표현",
        extract(year from sysdate) - extract(year from to_date(substr(emp_no, 1,2), 'yy')) +1 "yy로 구했을때 문제",
        extract(year from sysdate) - decode(substr(emp_no, 8, 1), '1',1900,'3',1900,2000)+substr(emp_no,1, 2)+1 "제대로"
from employee;

--yy는 현재년도 2021 기준으로 현재세기(2000 ~ 2099)범위에서 추측한다.
--rr는 현재년도 2021 기준으로 (1950 ~ 2049) 범위에서 추측한다.


--========================================================
--DQL2
--========================================================

---------------------------------------------------------------------
--Group By
---------------------------------------------------------------------
--지정 컬럼 기준으로 세부적인 그룹핑이 가능하다.
--group by구문 없이는 전체를 하나의 그룹으로 취급한다.
--group by 절에 명시한 컬럼만 select 절에 사용가능하다.

select sum(salary)
from employee;
--group by ();  --아무 조건 없음

select dept_code,
--        emp_name, --ORA-00979: not a GROUP BY expression
        sum(salary)
from employee
group by dept_code; --부서별 급여의 합계, 일반컬럼 | 가공컬럼이 올수있음

select emp_name, dept_code, salary
from employee; --확인용...

select job_code,
        trunc(avg(salary),1)
from employee
group by job_code
order by job_code;

--부서코드별 사원수 조회
select nvl(dept_code,'인턴'),
        count(*)
from employee
group by dept_code
order by dept_code;

--부서코드별 사원수, 급여평균, 급여 합계 조회
select nvl(dept_code,'인턴') 부서코드,
        count(*) 부서인원,
        to_char(trunc(avg(salary),1),'fml999,999,999,999,999') 부서급여평균,
        to_char(sum(salary),'fml999,999,999,999,999') 부서급여합계
from employee
group by dept_code
order by dept_code;

--성별 인원수, 평균 급여 조회
select decode(substr(emp_no, 8, 1), '1', '남', '3', '남', '여') gender,
            count(*) count,
            to_char(trunc(avg(salary), 1), 'fml9,999,999,999.0') avg
from employee
group by decode(substr(emp_no, 8, 1), '1', '남', '3', '남', '여');


select case when substr(emp_no,8,1) in ('1','3') then '남' else '여' end gender,
        count(*) count,
        to_char(trunc(avg(salary), 1), 'fml9,999,999,999.0') avg
from employee
group by case when substr(emp_no,8,1) in ('1','3') then '남' else '여' end;

--직급코드 J1을 제외하고, 입사년도별 인원수를 조회
select extract(year from hire_date) "입사년도",
        count(*) count
from employee
where job_code != 'J1'
group by extract(year from hire_date)
order by 입사년도;

--두개 이상의 컬럼으로 그룹핑 가능
select dept_code,
        job_code,
        count(*)
from employee
group by dept_code, job_code
order by 1,2;

--부서별 성별 인원수
select dept_code "부서",
        decode(substr(emp_no, 8, 1), '1', '남', '3', '남', '여') "성별",
        count(*) "인원수"
from employee
group by dept_code, decode(substr(emp_no, 8, 1), '1', '남', '3', '남', '여')
order by 1,2;

---------------------------------------------------------------------
--HAVING
---------------------------------------------------------------------
--group by 이후 조건절

--부서별 평균 급여가 3,000,000원 이상인 부서만 조회
select dept_code,
        trunc(avg(salary)) avg
from employee
--where avg(salary) >= 3000000 그룹함수는 조건절에 쓸수없음
group by dept_code
having avg(salary) >= 3000000
order by 1;

--직급별 인원수가 3명이상인 직급과 인원수 조회
select job_code 직급, count(*) 인원수
from employee
group by job_code
having count(*) >= 3
order by job_code;

--관리하는 사원이 2명이상인 manager의 아이디와 관리하는 사원수 조회
select manager_id, count(*)
from employee
where manager_id is not null --여기에 적어줘도 되고 having count부분에 and manager_id is not null해줘도됨
group by manager_id
having count(*)>=2
--having count(manager_id)>=2 --이렇게하면 null은 자동으로 배제하고 카운트 되기도함
order by manager_id;

--rollup | cube(col1, col2.....)
--group by 절에 사용하는 함수
--그룹핑 결과에 대해 소계를 제공

--rollup 지정컬럼에 대해 단방향 소계 제공
--cube 지정컬럼에 대해 양방향 소계 제공
--지정컬럼이 하나인 경우, rollup/cube의 결과는 같다.

select dept_code,
        count(*)
from employee
group by rollup(dept_code); --소계를 해주는 행이 하나

select dept_code,
        count(*)
from employee
group by cube(dept_code); --소계를 해주는 행이 하나

--grouping()
--실제데이터(0) | 집계데이터(1) 컬럼을 구분하는 함수

select dept_code, --nvl(dept_code, '인턴') 은 실제 null값과 집계데이터 null을 구분하지 못함
        grouping(dept_code),
        decode(grouping(dept_code),'0',nvl(dept_code,'인턴'),'1','합계'), --최종 사용법
        count(*)
from employee
group by rollup(dept_code)
order by 1;

--두개 이상의 컬럼을 rollup | cube에 전달하는 경우
select decode(grouping(dept_code),0,nvl(dept_code,'인턴'),'합계') dept_code, 
        decode(grouping(job_code),0, job_code, '소계') job_code,
        count(*)
from employee
group by rollup(dept_code, job_code)
order by 1,2;

select dept_code,
        job_code,
        count(*)
from employee
group by cube(dept_code, job_code)
order by 1,2;

select decode(grouping(dept_code), 0, nvl(dept_code, 'INTERN'), '소계') dept_code,
        decode(grouping(job_code), 0, job_code, '소계') job_code,
        count(*)
from employee
group by cube(dept_code, job_code)
order by 1, 2;


/*

select (5)
from (1)
where (2)
group by (3)
having (4)
order by (6)

*/

--relation 만들기
--가로방향(옆)으로 합치기 = JOIN   행 + 행
--세로방향(밑)으로 합치기 = UNION 열 + 열
--비교적 UNION이 훨씬 쉽다 밑으로 불일때는 타입, 컬럼갯수 모두 같을때만 가능하기때문에


--==================================================
--JOIN
--==================================================
--두개 이상의 테이블을 연결해서 하나의 가상테이블(Relation)을 생성

--송종기 사원의 부서명을 조회
--과정1 송종기 사원 부서코드 확인 D9
select dept_code
from employee
where emp_name = '송종기';
--과정2 부서코드에 해당하는 부서명 확인
select dept_title
from department
where dept_id = 'D9';

--join
--조인으로 테이블 합쳐서 한번에 처리
select e.emp_name, d.dept_title
from employee e inner join department d on e.dept_code = d.dept_id
where e.emp_name = '송종기';

--join의 종류
--1. EQUI-JOIN 동등 비교조건(=) 에 의한 조인 (앞으로 사용할 대부분의 방식)
--2. NON-EQUI JOIN 동등 비교조건이 아닐때 

--join 문법
--1. ANSI 표준문법 : 모든 DBMS 공통문법
--2. Vendor별 문법 : DBMS별로 지원하는 문법. 오라클전용문법

--테이블 별칭
select e.emp_name,
        e.job_code,   --ORA-00918: column ambiguously defined( Job_code는 두개인데 어디의 것을 가져올지 명시안해주면)
        j.job_name
from employee e join job j
    on e.job_code = j.job_code;
    
--기준컬럼명이 좌우테이블에서 동일하다면, on대신 using사용 가능
--using을 사용한 경우는 해당컬럼에 별칭을 사용할 수 없다.
select E.emp_name,
        job_code, --별칭을 사용 할 수 없다
        J.job_name
from employee E join job J
    using(job_code);
--using 을 사용할경우 맨앞으로 튀어나오면서 딱 한번만 포함된다



--equi Join 종류
/*
1.inner join  교집합, 두테이블의 겹쳐지는 부분만 추려내는 친구

2.outer join 합집합
        -   left outer join      ( 좌측테이블 기준 합집합 )
        -   right outer join    ( 우측테이블 기준 합집합 )
        -   full outer join      ( 양테이블 기준 합집합    )

3.cross join
    두테이블간의 조인 할 수 있는 최대경우의 수를 표현

4.self join
    같은 테이블의 조인

5.multiple join
    3개이상의 테이블을 조인
*/

-------------------------------------------------
--Inner Join
-------------------------------------------------
--A (inner) join B 
--어떤 조인인지 생략 해주면 inner조인이 기본값이다
--교집합 
--1. 기준 컬럼값이 null인 경우, 결과집합에서 제외된다.
--2. 기준 컬럼값이 상대테이블 존재하지 않는 경우, 결과집합에서 제외.

--1. employee에서 dept_code가 null인 행 제외 : 인턴사원 2행제외
--2. department에서 겹치지 않는부분 제외 : dept_id가 D3, D4, D7인 행은 제외 (dept_code에는 없어서)

--(ansi 문법)
select *
from employee E join department D
    on E.dept_code = D.dept_id;
    
    
--(oracle) 문법
select *
from employee E, department D --inner조인시 ,콤마 사용
where e.dept_code = d.dept_id; --추가 조건이 있다면 and로 연결하면됨


--(ansi문법)
select *
from employee E join job j
    on e.job_code = j.job_code;

--(oracle)문법
select *
from employee e, job j
where e.job_code = j.job_code;

-------------------------------------------------
--Outter Join
-------------------------------------------------
--1.left outer join
--좌측테이블 기준
--좌측테이블 모든 행이 포함, 우측 테이블에는 on조건절에 만족하는 행만 포함.
--24 = 22 + 2(left에 있는 null두개)
--오라클 문법시 --기준테이블의 반대편 컬럼에 (+)를 추가
--(ansi 표준문법)
select *
from employee E left join department D
    on E.dept_code = D.dept_id;

--(oracle문법)
--기준테이블의 반대편 컬럼에 (+)를 추가
select *
from employee e, department d
where e.dept_code = d.dept_id(+);

--2.right(outer) join
--우측테이블 기준
--우측테이블 모든 행이 포함, 좌측테이블에는 on 조건절에 만족하는 행만 포함.
--25 = 22 +3(right에 있는 null세개)
--(ansi 문법)
select *
from employee E right join department D
    on E.dept_code = D.dept_id;

--(oracle문법)
select *
from employee e, department d
where e.dept_code(+) = d.dept_id;

--3.full (outer) join
--완전 조인
--양쪽테이블 모든 행이 포함.
--27 = 22 + 2(left에 인턴2) + 3(D3,D4,D7)

--(ansi문법)
select * 
from employee E full join department D
    on E.dept_code = D.dept_id;

--(oracle)에서는 full outer join을 지원하지 않는다.

--사원명/부서명 조회시
--부서지정이 안된 사원은 제외 : inner join
--부서지정이 안된 사원도 포함 : left join
--사원배정이 안된 부서도 포함 : right join
--부서지정이 안된사원, 사원배정이 안된부서도 포함 : full join

--(ansi)문법
select *
from department D left join employee E
    on E.dept_code = D.dept_id;



--oracle_chun 11번 문제 확인


-------------------------------------------------
--cross Join
-------------------------------------------------
--상호조인
--on조건절 없이, 좌측테이블 행과 우측 테이블의 행이 연결될 수 있는 모든 경우의 수를 포함한 결과집합.
--Cartesian's product
--216 = 24 * 9;

select *
from employee; --24

select *
from department; --9

--ansi문법
select * 
from employee cross join department; --24*9 = 216
--일반 컬럼, 그룹함수결과를 함께 보고자 할때 사용됨.

--oracle 문법 where절을 안써주면 cross조인
select * 
from employee,department;


select trunc(avg(salary))
from employee;

select emp_name, salary, avg, salary - avg diff
from employee E cross join (select trunc(avg(salary)) avg
                                             from employee) A;


-------------------------------------------------
--Self Join
-------------------------------------------------
--조인시 같은 테이블을 좌/우측 테이블로 사용.
--self join에서 별칭은 무조건 필요하다
--self join은 대상이 자기자신인것 일뿐 여기서도 inner, outer, cross 가능하다

--사번, 사원명, 관리자사번, 관리자명을 조회하라!
--(ansi 문법)
select e1.emp_id 사번,
            e1.emp_name 사원명,
            e1.manager_id 관리자사번, --혹은 e2.emp_id를 사용해도됨
            e2.emp_name 관리자명
from employee e1 join employee e2 on e1.manager_id = e2.emp_id;

--(oracle 문법)
select E1.emp_id,
            E1.emp_name, 
            E1.manager_id,
            E2.emp_name
from employee E1, employee E2
where E1.manager_id = E2.emp_id;


-------------------------------------------------
--Multiple Join
-------------------------------------------------
--한번에 좌우 두 테이블씩 조인하여 3개이상의 테이블을 연결함.
--사원명, 부서명, 지역명

--(ansi문법)사용시 조인하는 순서를 잘 고려할것
--employee와 location은 공통된 컬럼이 없어서 이렇게 먼저 조인해주면 안된다.
select * from location;

select e.emp_name 사원명,
        d.dept_title,
        l.local_name,
        J.job_name
from employee e 
        join job J
            on E.job_code = j.job_code
        left join department d
            on e.dept_code = d.dept_id
        left join location L 
            on d.location_id = l.local_code;
--where E.emp_name = '송종기';


--oracle문법 사용시 조인순서가 하나도 중요하지 않지만(from 쪽), where 에서는 순서를 동일하게 해준다
--(oracle)
select *
from employee E, department D, location L, job J
where E.dept_code = D.dept_id(+) 
    and D.location_id = L.local_code(+)
    and E.job_code = J.job_code;
--and E.emp_name = '송종기';

--@실습문제
--직급이 대리, 과장이면서, Asia 지역에 근무하는 사원조회
--사번, 이름, 직급명, 부서명, 근무지역, 국가

--(ansi 표준방식)
select e.emp_id 사번,
           e.emp_name 이름,
           j.job_name 직급명,
           d.dept_title 부서명,
           to_char(e.salary,'fml999,999,999,999') 급여,
           l.local_name 근무지역,
           n.national_name 국가
from employee e
        left join job j on e.job_code = j.job_code
        left join department d on e.dept_code = d.dept_id
        left join location l on d.location_id = l.local_code
        left join nation n on l.national_code = n.national_code
where j.job_name in ('대리','과장') and l.local_name like 'ASIA%';

--(oracle 방식)
select e.emp_id 사번,
           e.emp_name 이름,
           j.job_name 직급명,
           d.dept_title 부서명,
           to_char(e.salary,'fml999,999,999,999') 급여,
           l.local_name 근무지역,
           n.national_name 국가
from employee e, job j, department d, location l, nation n
where e.job_code = j.job_code(+)
   and  e.dept_code = d.dept_id(+)
   and  d.location_id = l.local_code(+)
   and  l.national_code = n.national_code(+)
   and  j.job_name in ('대리','과장') and l.local_name like 'ASIA%';







-------------------------------------------------
--non_equi Join
-------------------------------------------------
-- (=)이 아닌 !=, between ~and ~, in, not in ..... 등등

--employee, sal_grade 테이블을 조인
--employee테이블의 sa_level컬럼이 없다고 가정.
--employee.salary컬럼과 sal_grade.min_sal | sal_grade.max_sal을 비교해서 join

select * from employee;
select * from sal_grade;

select *
from employee E
    join sal_grade S
        on E.salary between S.min_sal and S.max_sal;
--조건에 맞는 행이있다면 합쳐라(똑같이 한행에 여러개가 붙을수도있음)

select *
from employee E
    join department D
        on E.dept_code != D.dept_id
order by E.emp_no;


--==================================================
--SET OPERATOR
--==================================================
--집합 연산자, entity를 컬럼수가 동일하다는 조건 하에 상하로 연결 한것
--조건1. select절의 컬럼수가 동일
--조건2. 컬럼별 자료형이 상호호환 가능해야 한다. ->  문자형(char, varchar2)끼리OK, 날짜형 + 문자열 Error
--컬럼명이 다른 경우, 첫번째 entity의 컬럼명을 결과집합에 반영
--order by는 마지막 entity에서 딱 한번만 사용가능

--종류
--union 합집합
--union all 합집합
--intersect 교집합
--minus 차집합

/*

A={1,3,2,5}
B={2,4,6}

A union B => {1,2,3,4,5,6} 중복 제거, 첫번째 컬럼 기준 오름차순 정렬
A union all B => {1,3,2,5,2,4,6} 중복제거도 안하고 정렬도 안함
A intersect B => {2} 첫번째 컬럼 기준 오름차순 정렬
A minus B => {1,3,5} 첫번째 컬럼 기준 오름차순 정렬

*/

--------------------------------------
-- UNION 과 UNION ALL 비교
--------------------------------------
--A : D5부서원의 사번, 사원명, 부서코드, 급여(6행)
select emp_id, emp_name, dept_code, salary
from employee
where dept_code = 'D5';

--B : 급여가 300만원이 넘는 사원의 사번, 사원명, 부서코드, 급여(9행)
select emp_id, emp_name, dept_code, salary
from employee
where salary > 3000000;

--A Union B =  D5이거나 salary가 300만 초과인 사람 (6행+9행 -2행 = 13행) 
-- (-2행)은 겹쳐지는 항목, order by 를 따로 명시 안해주면 1번컬럼으로 정렬
select emp_id, emp_name, dept_code, salary
from employee
where dept_code = 'D5' --세미콜론 조심
--order by 절은 두번째 테이블에서만 사용 가능
union
select emp_id, emp_name, dept_code, salary
from employee
where salary > 3000000
order by dept_code;

--A Union All B = D5이거나 salary가 300만 초과인 사람 (6행+9행=15행)
--중복제거는 해주지 않고 정렬을 명시하지 않으면 정렬도 해주지 않는다.
select emp_id, emp_name, dept_code, salary
from employee
where dept_code = 'D5' --세미콜론 조심
--order by 절은 두번째 테이블에서만 사용 가능
union all
select emp_id, emp_name, dept_code, salary
from employee
where salary > 3000000;
--order by dept_code;

--A intersect B =  A와 B테이블에 공통적으로 있는것만 출력(2행)
--order by 를 따로 명시 안해주면 1번컬럼으로 정렬
select emp_id, emp_name, dept_code, salary
from employee
where dept_code = 'D5' --세미콜론 조심
--order by 절은 두번째 테이블에서만 사용 가능
intersect
select emp_id, emp_name, dept_code, salary
from employee
where salary > 3000000;
--order by dept_code;

--A Minus B =  A에서 A와 B의 겹치는 부분을 제외하고 출력(4행 = A에만 있는것)
--order by 를 따로 명시 안해주면 1번컬럼으로 정렬
select emp_id, emp_name, dept_code, salary
from employee
where dept_code = 'D5' --세미콜론 조심
--order by 절은 두번째 테이블에서만 사용 가능
minus
select emp_id, emp_name, dept_code, salary
from employee
where salary > 3000000;
--order by dept_code;

--union시 컬럼명이 동일하지 않아도 컬럼수가 같고 자료형이 같으면 union가능
desc employee;

select job_code
from employee

union
select sal_level
from employee;


--==========================================================
--SUB QUERY
--==========================================================
--하나의 sql문(main-query) 안에 종속된 또다른 sql문(sub-query)
--존재하지 않는 값, 조건에 근거한 검색 등을 실행 할 때.

--반드시 소괄호로 묶어서 처리할것
--sub-query 내에는 order by 문법이 적용되지 않는다!!!
--연산자 오른쪽에서 사용 할 것. where col = (서브쿼리)

--노옹철 사원의 관리자 이름을 조회

--1. 노옹철 사원행의 manager_id조회
--2. emp_id가 조회한 manager_id와 동일한 행의 emp_name을 조회
select manager_id
from employee
where emp_name = '노옹철';

select emp_name
from employee
where emp_id = '201';

--이렇게 두번 처리했던것을 join으로 처리하면
select 
        e.emp_id,
        e.emp_name,        
        e.manager_id,
        e2.emp_name
from employee e join employee e2 on e.manager_id = e2.emp_id
where e.emp_name = '노옹철';

--sub_query로 처리하면
select emp_name
from employee
where emp_id = (select manager_id
                            from employee
                            where emp_name = '노옹철');

/*

1. 단일행 단일컬럼 서브쿼리

2. 다중행 단일컬럼 서브쿼리

3. 다중열 서브쿼리(단일행/다중행)

4. 상관서브쿼리 (<=====> 일반서브쿼리 )
5. 스칼라 서브쿼리 (Select절에 사용된 서브쿼리)

6. inline-view (From 절에 사용된 서브쿼리)

*/
------------------------------------------------
-- 단일행 단일컬럼 서브쿼리
------------------------------------------------
--서브쿼리 조회 결과가 1행1열인 경우
-- 서브쿼리 결과를 하나의 데이터값으로 생각하면 된다

--전체 평균 급여보다 많은 급여를 받는 사원 조회
select emp_name,
            salary, 
            (select trunc(avg(salary))
                         from employee)  평균급여
from employee
where salary > (select avg(salary)
                         from employee);

--윤은해 사원과 같은 급여를 받는 사원 조회(사원, 이름, 급여)
select emp_id, emp_name, salary
from employee
where salary = (select salary
                         from employee
                         where emp_name = '윤은해')
          and emp_name != '윤은해';


--D1, D2 부서원 중에 D5부서의 평균 급여보다 많은 급여를 받는 사원 조회(부서코드, 사번, 사원명, 급여)

select dept_code, emp_id, emp_name, salary
from employee
where dept_code in ('D1', 'D2') and
          salary > (select  avg(salary)
                         from employee
                         where dept_code = 'D5');

------------------------------------------------
-- 다중행 단일컬럼 서브쿼리
------------------------------------------------
-- 서브쿼리 조회 결과가 n행1열인 경우
-- 서브쿼리 결과를 하나의 (데이터집합)으로 생각하면 된다
-- 연산자 in, not in, any, all, exists 들과 함께 사용가능한 서브쿼리

--송종기, 하이유 사원이 속한 부서원 조회
select emp_name, dept_code
from employee
where dept_code in (select dept_code
                                from employee
                                where emp_name in ('송종기','하이유'));

--차태연, 전지연 사원의 급여등급(sal_level)과 같은 사원 조회(사원명, 직급명, 급여등급 조회)
select e.emp_name 사원명, j.job_name 직급명, e.sal_level 급여등급
from employee e join job j on e.job_code = j.job_code
where sal_level in (select sal_level
                              from employee
                              where emp_name in ('차태연', '전지연'));

--직급이 대표, 부사장이 아닌 사원 조회(사번, 사원명, 직급코드)
select emp_id, emp_name, job_code
from employee
where job_code not in (select job_code
                                     from job
                                     where job_name in ('대표','부사장'));


--ASIA1 지역에 근무하는 사원 조회(사원명, 부서코드)                              
select emp_name, dept_code
from employee
where dept_code in(
                                select dept_id
                                from department
                                where location_id in(
                                                                select local_code
                                                                from location
                                                                where local_name = 'ASIA1'
                                                                )
                             );
 
 ------------------------------------------------
-- 다중열(단일컬럼, 다중컬럼) 서브쿼리
------------------------------------------------
 --서브쿼리의 리턴된 컬럼이 여러개인 경우.
 
 --퇴사한 사원과 같은 부서,같은 직급의 사원을 조회(사번, 부서코드, 직급코드)
--방법1. 나눠서 처리
select emp_id, dept_code, job_code
from employee
where dept_code = (
                                select dept_code
                                from employee
                                where quit_yn = 'Y')
            and job_code = (
                                    select job_code
                                    from employee
                                    where quit_yn = 'Y');
 
--방법2. 합쳐서 처리
select emp_id, dept_code, job_code
from employee
where (dept_code, job_code) =  (
                                                    select dept_code, job_code
                                                    from employee
                                                    where quit_yn='Y'
                                                );

--manager가 존재하지 않는 사원과 같은 부서코드, 직급코드를 가진 사원 조회
-- in 연산자는 다중행 다중컬럼 처리 가능
-- dept_code컬럼에는 null값도 있는데 null값은 비교 및 연산이 불가능 하기때문에 nvl로 예외처리
select emp_id, emp_name, manager_id, dept_code, job_code
from employee
where (nvl(dept_code,'부서없음'), job_code) in (
                                                    select nvl(dept_code,'부서없음'), job_code
                                                    from employee
                                                    where manager_id is null
                                                )
order by dept_code, job_code, manager_id nulls first;

--부서별 최대급여를 받는 사원 조회( 사원명, 부서코드, 급여)
select emp_name, dept_code, salary
from employee
where (nvl(dept_code,'부서없음'), salary) in (
                                                select nvl(dept_code,'부서없음'), max(salary)
                                                from employee
                                                group by dept_code
                                                )
order by dept_code nulls last;

------------------------------------------------
-- 상관(상호연관) 서브쿼리
------------------------------------------------
--메인쿼리의 값을 서브쿼리에 전달하고, 서브쿼리 수행 후 결과를 다시 메인쿼리에 반환.

--직급별 평균급여 보다 많은 급여를 받는 사원

--(기존에 풀던 방식)
select e.emp_id, e.emp_name,job_code,e.salary, trunc(temp.평균) "직급별 평균급여"
from employee e join (select job_code, avg(salary) 평균
                                    from employee
                                    group by job_code) temp 
                          using (job_code)
where e.salary> temp.평균;


--상관 서브쿼리로 처리
select *
from employee e
--where salary >(직급별 평균 급여);
--직급별 평균 급여보다 많이 받는 사람
--매 행 마다 where절을 실행시켜 부서에 해당하는 평균이 넘는지 검사하는 개념
where e.salary > (
                        select avg(salary)
                        from employee
                        where job_code = E.job_code --메인쿼리 테이블의 별칭이 반드시 필요
                        --where job_code = 'J1' --J1, J2.....J7까지 고정값으로 처리해줄순 없다...
                        );
--부서별 평균급여보다 적은 급여를 받는 사원 조회(인턴포함)
select emp_name, dept_code, salary
from employee e
where e.salary < (
                            select avg(salary)
                            from employee
                            where nvl(dept_code,'인턴') = nvl(e.dept_code,'인턴')
                            )
order by dept_code nulls first;


--exists 연산자
--exists(sub-query) sub-query에 행이 존재하면 참, 존재하지 않으면 거짓

select *
from employee
where 1=1; --무조건 true

select *
from employee
where 1=0; --무조건 false

select *
from employee
where exists(
                        select *
                        from employee
                        where 1=1
                    );
--행이 존재하지 않는 서브쿼리 : exists false
select *
from employee
where exists(
                        select *
                        from employee
                        where 1=0
                    );

--관리하는 직원이 한명이라도 존해하는 관리자 사원 조회
--내 emp_id값이 누군가의 manager_id로 사용된다면, 나는 관리자!
--내 emp_id값이 누군가의 manager_id로 사용되지 않는다면, 나는 관리자 아님!

--수동
select *
from employee
where manager_id = '200';--....201,204,207......(manager id검사)

select emp_id, emp_name
from employee e
where exists (
                    select * --값을 뭘 출력할지는 중요하지 않음 행이 존재하냐 존재 안하냐가 중요함
                    from employee
                    where manager_id = e.emp_id
                    );

--부서테이블에서 실제 사원이 존재하는 부서만 조회(부서코드, 부서명)
select dept_id, dept_title
from department d
where exists(
                    select *
                    from employee
                    where dept_code = d.dept_id
                    );

--부서테이블에서 실제 사원이 존재하지 않는 부서만 조회(부서코드, 부서명)
--not exists(sub-query) : sub-query의 결과행이 존재하지 않으면 true, 존재하면 false
select dept_id, dept_title
from department d
where not exists(
                    select *
                    from employee
                    where dept_code = d.dept_id
                    );


--최대/최소값 구하기 (not exists)
--가장 많은 급여를 받는 사원을 조회
--가장 많은 급여를 받는다 -> 본인보다 많은 급여를 받는 사원이 존재하지 않는다

select *
from employee e
where not exists (
                    select *
                    from employee
                    where salary > e.salary);


---------------------------------------------------------
--SCALA SUBQUERY
---------------------------------------------------------
--서브쿼리의 실행결과가 1(단일행 단일컬럼)인 select절에 사용된 상관서브쿼리
--관리자 이름 조회
select emp_name,
        nvl((
            select emp_name
            from employee
            where emp_id = E.manager_id
        ),'-') manager_name
from employee e;


--사원명, 부서명, 직급명 조회
select emp_name,
          (
            select dept_title
            from department
            where dept_id = e.dept_code
          ),
          (
            select job_name
            from job
            where job_code = e.job_code
          )
from employee e;

---------------------------------------------------------
--INLINE VIEW
---------------------------------------------------------
--from 절에 사용된 subquery, 가상테이블


--기존방식 where에 쓴걸 다시 select에 써줬어야함
select emp_id,
        emp_name,
        decode(substr(emp_no, 8,1),'1','남','3','남','여')
from employee
where decode(substr(emp_no, 8,1),'1','남','3','남','여') = '여';

--inline view 사용
select *
from (
            select emp_id,
                      emp_name,
                      decode(substr(emp_no, 8,1),'1','남','3','남','여') gender
            from employee
        ) 
where gender = '여';

select emp_no 아
from employee;

--30~50세 사이의 여사원 조회(사번, 이름, 부서명, 나이, 성별)
--inline-view나이,성별
select *
from (
            select 
                    emp_id,
                    emp_name, 
                    (
                        select dept_title
                        from department d
                        where e.dept_code = d.dept_id
                    ) 부서명,
                    extract(year from sysdate)-case
                        when substr(emp_no, 8 ,1 ) in('1', '2') then substr(emp_no,1,2)+1901
                        else substr(emp_no,1,2)+2001
                    end "나이",
                    decode(substr(emp_no, 8,1),'1','남','3','남','여') gender
            from employee e
        )
where gender = '여' and 나이 between 30 and 50 ;
                    

--=================================================================
--고급 쿼리
--=================================================================

-------------------------------------------------------------------
--TOP - N 분석
-------------------------------------------------------------------

--급여를 많이 받는 Top-5
--입사일이 가장 최근인 top-10 
--top n이 될수도있고 bottom n이 될수도있음
--줄을 세우고 잘라냄


--월급많이 받는순 정렬은 배웠음. 단, 잘라낼수는 없음
select emp_name, salary
from employee
order by salary desc;


--rownum | rowid
--rownum : 테이블에 레코드 추가시 1부터 1씩 증가하면서 부여된 일련번호. 부여된 번호는 변경불가
--rownum 은 from 절이 실행되면서부터 부여됨
--rowid : 테이블 특정 레코드에 접근 하기 위한 논리적 주소값.(해시코드개념)

select rownum,
          rowid,
          E.*
from employee e;

--이걸 활용해보자

select rownum, e.*
from (
            select
                        emp_name,
                        salary
            from employee
            order by salary desc
        ) e
where rownum between 1 and 5;


--입사일이 빠른 10명 조회

select e.*
from (
            select *
            from employee
            order by hire_date asc
        ) e
where rownum between 1 and 10;


--입사일이 빠른 순서로 6번째에서 10번째 사원 조회
--rownum은 where절이 시작하면서 부여되고, where 절이 끝나면 모든행에 대해 부여가 끝이난다
--lv1순서부여 , lv2 rownum(rnum)부여, lv3 최종

select E.*
from (
            select rownum rnum, e.*
            from (
                        select emp_name,
                                    hire_date
                        from employee
                        order by hire_date asc
                    )E
        )E
where rnum between 6 and 10;

--직급이 대리인 사원중에 연봉 top-3조회 (순위, 이름, 연봉)
select rownum, e.*
from (
            select emp_name, salary*(1+nvl(bonus,0))*12 연봉
            from employee
            where job_code = (
                                            select job_code
                                            from job
                                            where job_name = '대리'
                                            )
            order by 연봉 desc
        ) e
where rownum between 1 and 3;

--직급이 대리인 사원중에 연봉 top-4~6조회 (순위, 이름, 연봉)
select *
from (
            select rownum rnum, e.*
            from (
                       select emp_name, salary*(1+nvl(bonus,0))*12 연봉
                       from employee
                       where job_code = (
                                                        select job_code
                                                        from job
                                                        where job_name = '대리'
                                                    )
            order by 연봉 desc
                    ) e
        )e
where e.rnum between 4 and 6;


--부서별 평균급여 top-3조회(순위, 부서명, 평균급여)
select rownum, e.*
from (
            select
                      nvl(
                        (
                        select dept_title
                        from department
                        where dept_id = e.dept_code
                        ),'인턴') 부서명,
                      trunc(avg(salary)) 평균급여
            from employee e
            group by dept_code
            order by avg(salary) desc
        ) e
where rownum between 1 and 3;


--부서별 평균급여 top-4~6조회(순위, 부서명, 평균급여)
--계층별 테이블을 e라고 정의했지만 현재레벨의 하위단계의 테이블은 별칭으로도 접근 못함
select *
from (
        select rownum 순위, e.*
        from (
            select
                      nvl((
                        select d.dept_title
                        from department d
                        where dept_id = e.dept_code
                      ),'인턴') 부서명,
                      trunc(avg(salary)) 평균급여
            from employee e
            group by dept_code
            order by avg(salary) desc
        ) e)e
where e.순위 between 4 and 6;


--with 구문
--inlineview 서브쿼리에 별칭을 지정해서 재사용하게 함

with emp_hire_date_asc
as
(
select emp_name, 
           hire_date
from employee
order by hire_date asc
)
select E.*
from (
            select rownum rnum, E.*
            from emp_hire_date_asc e
            ) E        
where rnum between 6 and 10;


----------------------------------------
--window function
----------------------------------------
--행과 행간의 관계를 쉽게 정의하기 위한 표준 함수
--고급쿼리의 하위로보자
--1.순위함수
--2.집계함수
--3.분석함수

/*
window_function(args), over([partition by절][order by절][windowing절])

1. args 윈도우 함수 인자 0 ~ n개 지점
2. over
        partion by 절 : 그룹핑 기준 컬럼 (select의 group by) 
        order by 절 : 정렬기준 컬럼 제시 (select의 order by)
        windowing 절 : 처리 할 행의 범위 지정,

*/

--rank () over () : 순위를 지정
--동급이 있으면 공동순위로 치뤄주고 그다움부터는 다시 정상적으로 부여 (20위 참고, 두명임)
--20위가 두명, 그다음은 22위(21위없음)
--dense_rank() over() : 빠진 숫자 없이 순위를 지정
--20위가 두명, 그다음은 21위(24명이지만 24위가 없음)
select emp_name,
           salary,
           rank() over(order by salary desc),
           dense_rank() over(order by salary desc)
from employee;


--그룹핑에 따른 순위 지정

select e.*
from (  
            select emp_name,
                     dept_code,
                    salary,
                    rank() over(partition by dept_code order by salary desc) rank_by_dept--부서별로 급여순서에 따른 랭크 지정
            from employee
        ) e
where rank_by_dept between 1 and 3;

--sum() over()
--일반 컬럼과 같이 사용 할 수 있다.
select emp_name,
            salary,
            dept_code,
            (select sum(salary) from employee) sum,
            sum(salary) over() "전체 사원 급여 합계",
            sum(salary) over(partition by dept_code) "부서별 급여합계",
            sum(salary) over(partition by dept_code order by salary) "부서별 급여 누계"
from employee;


--avg() over()
select emp_name,
            dept_code,
            salary,
           trunc( avg(salary) over(partition by dept_code)) 부서별_평균_급여 --부서별 평균 급여
from employee;

--count() over()
select emp_name,
            dept_code,
            count(*) over(partition by dept_code) cnt_by_dept
from employee;



--===========================================================
-- DML
--===========================================================
-- Data Manipulation Language 데이터 조작어
-- CRUD Create Retrieve Update Delete
-- Insert 행추가
-- update 행수정
-- delete 행삭제
-- select (DQL)

-------------------------------------------------------------
-- Insert
-------------------------------------------------------------
--1. insert into 테이블 values(컬럼1값, 컬럼2값 ......);
--모든 컬럼을 빠짐없이 순서대로 작성해야함
--2. insert into 테이블(컬럼명1, 컬럼명2,........) values(컬럼1값, 컬럼2값,...)
--컬럼을 생략가능, 컬럼순서도 자유롭다
--(단, Not Null 컬럼이면서 기본값이 없다면 생략 불가능)

create table dml_sample(
    id number,
    nick_name varchar2(100) default '홍길동',
    name varchar2(100) not null,
    enroll_date date default sysdate not null
);

select * from dml_sample;

--타입1
insert into dml_sample
values (100,default, '신사임당', default);

insert into dml_sample
values (100,default, '신사임당'); --SQL 오류 : ORA-00947 : not enough values

insert into dml_sample
values (100, default, '신사임당', default, 'ㅋㅋ'); --SQL 오류 : ORA-00913 : too many values

--타입2
insert into dml_sample (id, nick_name, name, enroll_date)
values(200,'제임스','이황', sysdate);

insert into dml_sample (name, enroll_date)
values('세종', sysdate); --nullable한 컬럼은 생략가능하다. 기본값이 있다면, 기본값이 적용된다

--not null이면서 기본값이 지정안된 경우 생략 할 수 없다.
insert into dml_sample (id, enroll_date)
values(300,sysdate); --ORA-01400: cannot insert NULL into ("KH"."DML_SAMPLE"."NAME")

insert into dml_sample (name)
values('윤봉길');



--서브쿼리를 이용한 insert

create table emp_copy
as
select *
from employee
where 1=2; --false값이니까 내용은 복사되지 않고 테이블 구조만 복사됨

--확인 해봤음
select * from emp_copy;
desc emp_copy;

insert into emp_copy (
    select*
    from employee
); -- 테이블 값 복사
rollback;

insert into emp_copy(emp_id, emp_name, emp_no, job_code, sal_level)(
    select emp_id, emp_name, emp_no, job_code, sal_level
    from employee
);
select *
from emp_copy;


--emp_copy 데이터 추가
select * from emp_copy;

select *
from user_tab_cols
where table_name = 'EMP_COPY';

--기본값 추가
alter table emp_copy
modify quit_yn default 'N'
modify hire_date default sysdate;

desc emp_copy;

--not null만 입력해보기
INSERT INTO emp_copy(emp_id, emp_name, emp_no, job_code, sal_level)
values (111,'김윤수', '940206-1111111', 'J4','S1');

--조회시 not null값과 default값만 잘나옴
select *
from emp_copy;

--insert all을 이용한 여러테이블에 동시에 데이터 추가
--서브쿼리를 이용해서 2개이상 테이블에 데이터를 추가, 조건부 추가도 가능
--입사일 관리 테이블
create table emp_hire_date
as
select emp_id, emp_name, hire_date
from employee
where 1=2; -- 구조만 복사

desc emp_hire_date;
select * from emp_hire_date;

--매니저 관리 테이블
create table emp_manager
as
select emp_id, emp_name, manager_id, emp_name manager_name --별칭지정시 컬럼명됨
from employee
where 1=2;

desc emp_manager;
select * from emp_manager;


select E.*, 
    (select emp_name from employee where emp_id = E.manager_id) manager_name
from employee E; --이 데이터를 각각 emp_hire_date와 emp_manager로도 보내줄것

--manager_name컬럼값 not null조건을 null로 변경
alter table emp_manager
modify manager_name null;

--from 테이블과 to 테이블의 컬럼명이 같아야한다
insert all
into emp_hire_date values(emp_id, emp_name, hire_date)
into emp_manager values(emp_id, emp_name, manager_id, manager_name)
select E.*,
    (select emp_name from employee where emp_id = E.manager_id) manager_name
from employee E;


--insert all 을 이용한 여러행 한번에 추가하기
--오라클에서는 다음문법을 지원하지 않는다
--INSERT INTO dml_sample
--values (1,'치킨','홍길동'), (2,'고구마','장발장'), (3,'베베','유관순'); 

INSERT ALL
INTO dml_sample values (1,'치킨','홍길동', default)
INTO dml_sample values (2,'고구마','장발장', default)
INTO dml_sample values (3,'베베','유관순', default)
select * from dual; --더미쿼리 --형식상...

--insert 타입1,타입2 반드시 공부,  서브쿼리는 이런게 있다정도


-------------------------------------------------------------
-- Update
-------------------------------------------------------------
-- update 실행후에는 행의 수에는 변화가 없다.
-- 0행, 1행이상을 동시에 수정한다
-- dml 처리된 행의 수를 반환
    
select * from emp_copy;


update emp_copy
set dept_code = 'D7', job_code = 'J3'
--where rownum = 1; --rownum으로 사용도 가능
where emp_id = '202'; --존재하지 않는 행이라면 0행이 업데이트 되었다고 뜸

commit; -- 메모리상 변경내역을 실제파일에 저장
rollback; --마지막 커밋시점으로 돌리기

drop table emp_copy;
create table emp_copy
as
select *
from employee;


--D5부서원들이 큰건을해서 급여를 올려주기로 했다~
update emp_copy
set salary = nvl(salary,0) +500000 -- += 복합대입연산자는 사용불가
where dept_code = 'D5';

select * from emp_copy;

--서브 쿼리를 이용한 
--방명수의 월급을 유재식의 월급과 동일하게~
update emp_copy
set salary = (select salary from emp_copy where emp_name='유재식')
where emp_name = '방명수';


--임시환 사원의 직급을 과장, 부서를 해외영업3부로 수정하세요
update emp_copy
set job_code = (
                        select job_code
                        from job
                        where job_name = '과장'
                        ),
     dept_code = (
                        select dept_id
                        from department
                        where dept_title = '해외영업3부'
                        )
where emp_name = '임시환'; --d2 j4 -> d7 j5 로 변경됨

commit;
rollback;

--where 절을 안써줄경우 끔찍한일 발생...
update emp_copy
set emp_name = '홍길동';

select * from emp_copy;

-------------------------------------------------------------
-- Delete
-------------------------------------------------------------
--정말 주의해야한다!!!!

select * from emp_copy;

--이것도 역시나 where절을 안쓰면 훅가니까 조심
delete from emp_copy
where emp_id = '211';

rollback;

-------------------------------------------------------------
-- TRUNCATE
-------------------------------------------------------------
--테이블의 행을 자르는 명령어
--DDL명령어로서 실행즉시, 자동커밋되어 되돌릴수 없다.
--DML과는 다르게 before image생성 작업이 없으므로, 실행속도가 엄청빠름

truncate table emp_copy;

select * from emp_copy;

insert into emp_copy
(select * from employee);

--===========================================================
-- DDL
--===========================================================
--Data Definition Language 데이터 정의어
--데이터베이스 객체를 생성/수정/삭제 할 수 있는 명령어
--생성 : Create
--수정 : Alter
--삭제 : Drop
--Truncate

--객체 종류
--table, view, sequence, index, package, procedure, function, trigger, synonym, scheduler, user .....
--엄청 많음

--주석 comment
--테이블, 컬럼에 대한 주석을 달 수 있다. (필수)

select *
from user_tab_comments;

select *
from user_col_comments
where table_name = 'EMPLOYEE';

desc tbl_files;

--테이블 주석
comment on table tbl_files is '파일경로테이블';

select *
from user_tab_comments
where table_name = 'TBL_FILES';

--컬럼 주석
comment on column tbl_files.fileno is '파일 고유번호'; --컬럼작성시 테이블이름.컬럼이름
comment on column tbl_files.filepath is '파일 경로';

select *
from user_col_comments
where table_name = 'TBL_FILES';

--수정/삭제 명령은 없다.
--....is ''; 빈문자열 = null로 삭제됨

--===================================
-- 제약조건 CONSTRAINT
--===================================
--테이블 생성 수정시 컬럼값에 대한 제약조건 설정 할 수 있다.
--데이터에 대한 무결성을 보장하기 위한 것.
--무결성은 데이터를 정확하고, 일관 되게 유지하는 것.

/*
제약조건 
1. not null : null을 허용하지 않음. 필수값
2. unique : 중복값을 허용하지 않음.
3. primary key : not null + unique 레코드 식별자로서 테이블당 1개만 허용한다.
4. foreign key :  외래키, 데이터 참조무결성 보장, 부모테이블의 데이터만 허용한다.
5. check : 저장가능한 값의 범위/조건을 제한

일절 허용하지 않음.
*/

--제약 조건 확인
--user_constraints (컬럼명이 나오지 않음)
--user_cons_columns (컬럼명이있음)
--그래서 주로 두테이블을 조인해서 사용함
select *
from user_constraints
where table_name = 'EMPLOYEE';

--CONSTRAINT_TYPE
--C check | not null  => 둘중에 뭔지 모르기때문에 반드시 SEARCH_CONDITION부분을 확인해야한다.
--U unique
--P primary key
--R foreign key 제약조건

select *
from user_cons_columns
where table_name = 'EMPLOYEE';


--보통 그래서 조인해서 사용
select constraint_name,
            uc.table_name,
            ucc.column_name,
            uc.constraint_type,
            uc.search_condition
from user_constraints uc
    join user_cons_columns ucc
        using(constraint_name)
where uc.table_name = 'EMPLOYEE';

----------------------------------
--NOT NULL
----------------------------------
--필수입력 컬럼에 not null제약조건을 지정한다.
--default 값 다음에 컬럼레벨에 작성한다
--보통 제약조건명을 지정하지 않는다

create table tb_cons_nn(
    id varchar2(20) not null , --컬럼레벨
    name varchar2(100)
    --테이블레벨
);
insert into tb_cons_nn --ORA-01400: cannot insert NULL into ("KH"."TB_CONS_NN"."ID")
values (null,'홍길동');

insert into tb_cons_nn
values ('honggd','홍길동');

select * from tb_cons_nn;

update tb_cons_nn
set id=''
where id = 'honggd'; --ORA-01407: cannot update ("KH"."TB_CONS_NN"."ID") to NULL
--update시에도 id는 notnull제약조건이기때문에 null로 설정 불가능해서 오류뜸

----------------------------------
--UNIQUE
----------------------------------
--중복 허용하지 않음
--그 컬럼에 한하여 중복값은 절대 존재 할 수 없다.
--insert, update 모두 마찬가지
--이메일, 주민번호, 닉네임 등에 주로 사용되지만
--요즘 전화번호는 unique를 사용하지 않는 추세 (번호를 바꾸는게 보편화 되어있기 때문)

create table tb_cons_uq(
    no number not null,
    email varchar2(50),
    --테이블레벨로 작성
    constraint uq_email unique(email) --제약조건 별칭만 봐도 어떤컬럼에 관한건지 쉽게 알수있게 지어야함
);

insert into tb_cons_uq values(1, 'abc@naver.com');
insert into tb_cons_uq values(2, '가나다@naver.com');
insert into tb_cons_uq values(3, 'abc@naver.com'); --ORA-00001: unique constraint (KH.UQ_EMAIL) violated
insert into tb_cons_uq values(4, null);
insert into tb_cons_uq values(5, null); -- 단 null이 중복되는것은 허용한다
--null이 중복되는것도 방지하려면 unique와 not null제약조건 두개를 걸어야한다

select * from tb_cons_uq;


----------------------------------
--PRIMARY KEY
----------------------------------
--레코드(행) 식별자
--not null + unique 기능을 가지고 있으며, 테이블당 한개만 설정이 가능하다.

create table tb_cons_pk(
    id varchar2(50),
    name varchar2(100) not null,
    email varchar2(200),
    constraint pk_id primary key(id), 
    --constraint uq_email unique(email) --tb_cons_uq테이블에서 uq_email이란 제약조건이름이 이미 있어서 실행불가
    constraint uniq_email unique(email)
);

insert into tb_cons_pk
values ('kimdai200', '김윤수', 'kimdia200@naver.com');
--두번 실행시 ORA-00001: unique constraint (KH.PK_ID) violated

insert into tb_cons_pk
values (null, '김김김', 'kimdia2000@gmail.com');
--ORA-01400: cannot insert NULL into ("KH"."TB_CONS_PK"."ID") 


select * from tb_cons_pk;

--제약조건 검사
select constraint_name,
            uc.table_name,
            ucc.column_name,
            uc.constraint_type,
            uc.search_condition
from user_constraints uc
    join user_cons_columns ucc
        using(constraint_name)
where uc.table_name = 'TB_CONS_PK';


--복합 기본키(주키 | primary key | pk)
--여러칼럼을 조합해서 하나의 pk로 사용
--사용된 컬럼 하나라도 null이어서는 안된다.

create table tb_order_pk(
    user_id varchar2(50),
    order_date date,
    amount number default 1 not null,
    constraint pk_user_id_order_date primary key(user_id, order_date)
);

insert into tb_order_pk
values ('honggd', sysdate, 3);
--최소 밀리초가 다르기때문에 여러번실행해도 겹치지 않는것

insert into tb_order_pk
values (null, sysdate, 3); --ORA-01400: cannot insert NULL into ("KH"."TB_ORDER_PK"."USER_ID")
--pk에 사용된 컬럼은 null일수 없음

select * from tb_order_pk;

--제약조건 검사
select constraint_name,
            uc.table_name,
            ucc.column_name,
            uc.constraint_type,
            uc.search_condition
from user_constraints uc
    join user_cons_columns ucc
        using(constraint_name)
where uc.table_name = 'TB_ORDER_PK';



----------------------------------
-- FOREIGN KEY
----------------------------------
--참조 무결성을 유지하기 위한 조건
--참조하고 있는 부모테이블의 지정 컬럼값 중에서만 값을 취할 수 있게 하는것
--참조하고 있는 부모테이블의 지정컬럼은 PK, UQ제약조건이 걸려있어야 한다.
--DEPARTMENT.dept_id(부모테이블) <================== EMOLOYEE.dept_code(자식테이블)
--DEPARTMENT.dept_id는 department의 PK이다
--EMOLOYEE.dept_code는 DEPARTMENT.dept_id(PK)를 참조하는 employee의(FK)이다. 
--자바의 상속관계는 아니고 참조 관계이다


create table shop_member(
    member_id varchar2(20),
    member_name varchar2(30) not null,
    constraint pk_shop_member_id primary key (member_id)
);

--제약조건 검사
select constraint_name,
            uc.table_name,
            ucc.column_name,
            uc.constraint_type,
            uc.search_condition
from user_constraints uc
    join user_cons_columns ucc
        using(constraint_name)
where uc.table_name = 'SHOP_MEMBER';

insert into shop_member values('honggd', '홍길동');
insert into shop_member values('sinsa', '신사임당');
insert into shop_member values('saejong', '세종');

select * from shop_member;

--drop table shop_by
create table shop_by (
    buy_no number,
    member_id varchar2(20),
    product_id varchar2(50),
    buy_date date default sysdate,
    constraint pk_shop_by_buy_no primary key(buy_no),
    constraint fk_shop_by_member_id foreign key(member_id)
                                                         references shop_member(member_id)
                                                         --on delete restricted 기본값
                                                         --on delete set null
                                                         on delete cascade
    --외래키는 관계설정을 어떤테이블의 어떤컬럼이라고 명시를 해줘야되서 좀 길어짐
);
--제약조건 검사
select constraint_name,
            uc.table_name,
            ucc.column_name,
            uc.constraint_type,
            uc.search_condition
from user_constraints uc
    join user_cons_columns ucc
        using(constraint_name)
where uc.table_name = 'SHOP_BY';

insert into shop_by
values (1, 'honggd','물건1',sysdate);

insert into shop_by
values (2, 'sinsa','물건2',sysdate);

insert into shop_by
values (3, 'kimdia200','물건2',sysdate);
--ORA-02291: integrity constraint (KH.FK_SHOP_BY_MEMBER_ID) violated - parent key not found
--shop_member테이블의 member_id에는 kimdia200라는 회원이 없기때문에 오류가 나는것

select * from shop_by;


--fk 기준으로 join -> relation
--구매번호 회원아이디 회원이름 구매물품아이디 구매시각
select b.buy_no, m.member_id, m.member_name, b.member_id, b.buy_date
from shop_by b, shop_member m
where b.member_id = m.member_id;


--애초에 employee, department, job, location, nation, sal_grade ........
--테이블 하나로 했으면 되지 않았을까???
--왜 쪼개 놨을까???

--정규화 Normalization
--이상현상 방지(anormaly)

select *
from employee;

--삭제 옵션
--on delete restricted : 기본값, 참조하는 자식행이 있는경우, 부모행 삭제불가
--자식행을 먼저 지우고 부모행을 지워야 됩니당

--on delete set null : 부모행 삭제시 자식 컬럼은 null로 변경
--on delete cascade : 부모행 삭제시 자식행도 삭제

delete from shop_member
where member_id = 'honggd';
--on delete restricted 시
--ORA-02292: integrity constraint (KH.FK_SHOP_BY_MEMBER_ID) violated - child record found
--참조하는 자식행이 있는데 부모행을 지우다니요?! 안됩니다~! 하는것 --on delete restricted 일때
--delete from shop_by
--where member_id = 'honggd'; -- 이걸먼저 실행하고 위에껄 실행하면 잘 작동함

--on delete set null시
--참조하는 자식행이 있는 shop_by의 'honggd'값이 있는것은 null로 변경됨

--on delete cascade시
--참조하는 자식행이 있는 shop_by의 행도 삭제됨

select * from shop_member; --확인용
select * from shop_by;  --확인용

--식별관계 | 비식별관계
--비식별봔계 : 참조하고 있는 부모컬럼값을 PK로 사용하지 않는경우.
--여러행에서 사용이 가능함 김윤수=부서코드d1, 김윤윤=부서코드d1 ......
--식별관계 : 참조하고 있는 부모컬럼을 PK로 사용하는 경우.
--여러행에서 사용이 불가능함

create table shop_nickname(
    member_id varchar2(20),
    nickname varchar2(100),
    constraint fk_member_id foreign key(member_id) references shop_member(member_id),
    constraint pk__member_id primary key(member_id)
);

insert into shop_nickname values('sinsa', '신솨112');

select * from shop_nickname;


---------------------------------------------------
--CHECK
---------------------------------------------------
--해당 컬럼의 값의 범위를 지정

--해당 컬럼의 값의 범위를 지정.
--null 입력 가능

--drop table tb_cons_ck
create table tb_cons_ck(
    gender char(1),
    num number,
    constraints ck_gender check(gender in ('M', 'F')),
    constraints ck_num check(num between 0 and 100)
);

insert into tb_cons_ck
values('M', 50);
insert into tb_cons_ck
values('F', 100);
insert into tb_cons_ck
values('m', 50);--ORA-02290: check constraint (KH.CK_GENDER) violated
insert into tb_cons_ck
values('M', 1000);--ORA-02290: check constraint (KH.CK_NUM) violated


----------------------------------------------------
--CREATE
----------------------------------------------------
--subquery를 이요한 create는 not null제약조건을 제외한 모든 제약조건, 기본값등을 제거한다.

create table emp_bck
as
select *from employee; --값은 모두 복사 되지만, 제약조건은 복사되지 않았음

select * from emp_bck;

--제약조건 검사
select constraint_name,
            uc.table_name,
            ucc.column_name,
            uc.constraint_type,
            uc.search_condition
from user_constraints uc
    join user_cons_columns ucc
        using(constraint_name)
where uc.table_name = 'EMP_BCK';

--기본값 확인
select *
from user_tab_cols
where table_name = 'EMPLOYEE';

select *
from user_tab_cols
where table_name = 'EMP_BCK';



--------------------------------------
-- ALTER
--------------------------------------
--table 관련 alter문으로 컬럼, 제약조건에 대해 수정가능
/*
    서브명령어
    -add 컬럼과 제약조건을 추가 할 수 있다.
    -modify 컬럼(자료형, 기본값, ... 수정가능), 제약조건은 변경 불가능
    -rename 컬럼명, 제약조건명을 변경 할 수 있다.
    -drop 컬럼, 제약조건 삭제 할 수 있음.
    
*/

create table tb_alter(
    no number
);

--add 컬럼
--맨 마지막 컬럼으로 추가
alter table tb_alter add name varchar2(100) not null;

--테이블 명세
desc tb_alter;
describe tb_alter;

--add 제약조건
--not null 제약조건은 추가가 불가능 해서 수정(modify)으로 처리
--no컬럼을 PK로 제약조건 추가 하고싶다!
alter table tb_alter
add constraint pk_tb_alter_no primary key (no);

--제약조건 검사
select constraint_name,
            uc.table_name,
            ucc.column_name,
            uc.constraint_type,
            uc.search_condition
from user_constraints uc
    join user_cons_columns ucc
        using(constraint_name)
where uc.table_name = 'TB_ALTER';


--modify 컬럼
--자료형, 기본값, null여부 변경가능
--문자열에서 호환가능 타입으로는 변경가능(char-> varchar2, varchar2 -> char)

--name컬럼을 자료형을 varchar2(100) -> varchar2(500),
--default값 추가, not null->null
alter table tb_alter
modify name varchar2(500) default '홍길동' null;

--자료형, null여부 확인
desc tb_alter;
--디폴트값 확인
select *
from user_tab_cols
where table_name = 'TB_ALTER';

--행이 있다면, 변경하는데 제한이 있다.
--존재하는 값보다는 작은 크기로 변경불가.
--null값이 있는 컬럼을 not null로 변경불가.
-- modify문 자체가 실행이 되지 않음

--modify 제약조건자체는 변경 불가능
--제약조건의 이름은 변경가능하다.
--제약조건을 변경하려면 삭제후 재생성 할 것.

--rename 제약조건
--기존 제약조건 확인
select constraint_name,
            uc.table_name,
            ucc.column_name,
            uc.constraint_type,
            uc.search_condition
from user_constraints uc
    join user_cons_columns ucc
        using(constraint_name)
where uc.table_name = 'TB_ALTER';

--제약조건 이름 변경
alter table tb_alter
rename constraint pk_tb_alter_no to pk_tb_alter_num;

--변경후 제약조건 확인
select constraint_name,
            uc.table_name,
            ucc.column_name,
            uc.constraint_type,
            uc.search_condition
from user_constraints uc
    join user_cons_columns ucc
        using(constraint_name)
where uc.table_name = 'TB_ALTER';

commit;
--drop 컬럼

--변경전 테이블 명세 확인
desc tb_alter;

alter table tb_alter
drop column name;

--변경후 테이블 명세 확인
desc tb_alter;


--drop 제약조건
alter table tb_alter
drop constraint pk_tb_alter_num;

--변경후 제약조건 확인
select constraint_name,
            uc.table_name,
            ucc.column_name,
            uc.constraint_type,
            uc.search_condition
from user_constraints uc
    join user_cons_columns ucc
        using(constraint_name)
where uc.table_name = 'TB_ALTER';


--테이블 이름 변경
alter table tb_alter
rename to tb_alter_new;

rename tb_alter_new to tb_alter;


--------------------------------------
--DROP
--------------------------------------
--ALTER의 서브명령어 drop과는 별도

--데이터베이스 객체 (table, user, view 등등.... ) 삭제
drop table tb_alter_all_new;


--=======================================
-- DCL
--=======================================
-- Data Control Language
-- 권한 부여/회수 관련 명령어 : grant / revoke
-- TCL Transaction Control Language를 포함한다.
-- TCL( commit / rollback / savepoint )

-- system 관리자 계정으로 진행
--qwerty 계정 생성
create user qwerty
identified by qwerty
default tablespace users;
--접속권한 부여
-- create session권한 또는 connect를 부여
grant connect to qwerty;
grant resource to qwerty;
--두개를 한번에 부여 할 수 있음
grant connect, resource to qwerty;
--두개의 권한을 한번에 회수함
revoke connect, resource from kh;

--qwerty계정으로 실행
create table tb_abc(
    id number
);

--권한, 롤을 조회
select *
from user_sys_privs;  --권한

select * 
from user_role_privs; --롤

select * 
from role_sys_privs; --부여받은 롤에 포함된 권한조회

--(다시 kh계정으로 돌아옴)
create table tb_coffee(
    cname varchar2(100),
    price number,
    brand varchar2(100),
    constraint pk_tb_coffee_cname primary key (cname)
);

insert into tb_coffee
values ('maxim', 2000, '동서식품');
insert into tb_coffee
values ('kanu', 3000, '동서식품');
insert into tb_coffee
values ('nescafe', 2500, '네슬레');

select * from tb_coffee;

commit;

--qwerty계정으로 전환해서
--kh 계정이 소유한 tb_coffee를 조회
select *
from tb_coffee; --qwerty계정에는 tb_coffee 테이블이 없어서

select *
from kh.tb_coffee; --이렇게 하면 접근이 가능하지만 kh계정으로 부터 권한을 받아야한다

insert into kh.tb_coffee
values ('프렌치까페', 5000,'몰라'); --추가권한 없음


--kh계정으로 돌아와서
grant select on tb_coffee to qwerty; --조회권한 부여
grant insert, update, delete on tb_coffee to qwerty; --추가, 수정, 삭제 권한 부여


--qwerty계정으로 다시 접속하고
select *
from kh.tb_coffee; --조회가 가능한 것을 확인 할 수 있다.
--반드시 테이블앞에 소유 계정을 명시해줘야한다
--현재 접속한 계정내의 테이블일 경우는 보통 생략되어있는것이다.

--qwerty에서 kh테이블의 데이터 추가
insert into kh.tb_coffee
values ('프렌치까페', 5000,'몰라');

--이상태에서는 kh계정에서 tb_coffee를 조회할시
--프렌치 까페가 나오지 않는다 왜일까?
--qwerty계정에서 commit을 아직 해주지 않아서 !

commit;

-- KH계정으로 다시 올아와서
select *
from tb_coffee;
--조회시 프렌치까페가 추가된걸 볼수 있다.

--qwerty계정에서 kh계정의 tb_coffee로의 권한을 회수 하려면
revoke insert,update,delete on tb_coffee from qwerty;
revoke select on tb_coffee from qwerty;

--총정리
--SQL
--1. DML = INSERT, UPDATE, DELETE, DQL(SELECT절)
--Auto Commit 이 안되므로 반드시 TCL을 사용해서 관리

--2. DDL = CREATE, ALTER, DROP, TRUNCATE
--Auto Commit 됨

--3. DCL = GRANT, REVOKE, TCL(COMMIT, ROLLBACK)
--Auto Commit 됨

--(주의!!!!. DML작업을 하다가 DDL작업을 하면 자동으로 커밋되니 주의)


--==================================================
-- DATABASE OBJECT 1
--==================================================
-- DB를 효율적으로 관리하고, 작동하게 하는 단위
select *
from all_objects; -- 현재 계정에서 접근 할 수 있는 객체

select distinct object_type
from all_objects; -- 현재 계정에서 접근 할 수 있는 객체의 종류

-------------------------------------------
-- DATA DICTIONARY
-------------------------------------------
--일반사용자 관리자로부터 열람권한을 얻어 사용하는 정보조회테이블
--읽기전용.
--객체 관련 작업을 하면 자동으로 그 내용이 반영.

--1. user_xxx : 사용자가 소유한 객체에 대한 정보
--2. all_xxx : user_xxx를 포함. 다른 사용자로부터 사용권한을 부여받은 객체에 대한 정보
--3. dba_xxx : 관리자전용. 모든 사용자의 모든 객체에 대한 정보


--이용가능한 모든 dd 조회
select * from dict; --dictionary

--*****************************************
-- user_xxx
--*****************************************
--xxx는 객체이름 복수형을 사용한다.

--user_tables

select * from user_tables;
select * from tabs; -- 동의어(synonym) 

--user_sys_privs : 권한
--user_role_privs : 롤(권한묶음)
--role_sys_privs : 사용자가 가진 롤에 포함된 모든 권한
select * from user_sys_privs;
select * from user_role_privs;
select * from role_sys_privs;

--user_sequences
select * from user_sequences;
--user_views
select * from user_views;
--user_indexes
select * from user_indexes;


--*****************************************
-- all_xxx
--*****************************************
--현재 계정이 소유하거나 사용권한을 부여받은 객체 조회

--all_tables
select * from all_tables;

--all_indexes
select * from all_indexes;

--*****************************************
-- dba_xxx
--*****************************************
--dba로 시작하는 객체는 관리자 계정에서 실행해야함

--일반사용자가 실행시,
--관리자 권한으로 변경해서 밑에 작업할것
select * from dba_tables; --ORA-00942: table or view does not exist

--특정사용자의 테이블 조회
select * 
from dba_tables
where owner in ('KH', 'QWERTY');

--특정사용자의 권한 조회
select * 
from dba_sys_privs
where grantee = 'KH';

select *
from dba_role_privs
where grantee = 'KH';

--테이블 관련 권한 확인
select * 
from dba_tab_privs
where owner = 'KH';

--관리자가 kh.tb_coffee 읽기 권한을 qwerty에게 부여
grant select, insert, update, delete on kh.tb_coffee to qwerty;
revoke select, insert, update, delete on kh.tb_coffee from qwerty;
--(다시 KH계정으로 돌아옴)


-------------------------------------------------------------
-- STORED VIEW
-------------------------------------------------------------
--저장뷰.
--inlineView가 일회성인 반면, storedView는 이를 객체로 저장해서 재사용이 가능하다
--가상테이블처럼 사용하지만, 실제로 데이터를 가지고 있는 것은 아니다.
--실제 테이블과 링크 개념이라고 보면 된다.

--뷰객체를 이용해서 제한적인 데이터만 다른 사용자에게 제공하는것이 가능하다.
create view view_emp
as 
select * from employee;
--ORA-01031: insufficient privileges
--01031. 00000 -  "insufficient privileges"
--create view 권한이 없기 때문

--create View권한은 resource 롤에 포함되어 있지 않기때문
--그렇기 때문에 따로 부여를 받아야한다

--(여기서 sys 계정에서 create view 권한 부여받고왔음)

create view view_emp
as
select emp_id,
            emp_name,
            substr(emp_no,1,8) ||'******' emp_no,
            email,
            phone
from employee;

select *
from view_emp; -- 테이블 처럼 사용가능

select *
from (
        select emp_id,
                    emp_name,
                    substr(emp_no,1,8) ||'******' emp_no,
                    email,
                    phone
        from employee;
        ); --실행시 이렇게 인라인 뷰처럼 작동하는것
--따라서 view는 inline view 를 가지고 있는것
--그래서 view는 데이터가 없다고 하는것이다


select * from user_views; --dd에서 조회

--타사용자에게 선별적인 데이터를 제공
grant select on kh.view_emp to qwerty;

--(계정을 qwerty로 변경)
select * from kh.employee; --전체 데이터는 접근 불가
select * from kh.view_emp; --일부 데이터만 접근 가능
--(계정을 다시 kh로 전환)

--view 특징
--1. 실제 컬럼뿐 아니라 가공된 컬럼 사용가능
--2. join을 사용하는 view가능
--3. or replace 옵션 사용가능
--4. with read only 옵션

create or replace view view_emp
as
select emp_id,
            emp_name,
            substr(emp_no,1,8) ||'******' emp_no,
            email,
            phone,
            nvl(dept_title, '인턴') dept_title
from employee E left join department D
    on E.dept_code = D.dept_id
with read only;

select * from view_emp;
--qwerty에서도 확인가능
select * from kh.view_emp;
--따로 권한을 주지 않고 or replace 해줘서 아까 받은 권한으로 사용한것
--갱신의 용이성

--성별, 나이 등 복잡한 연산이 필요한 컬럼을 미리 view로 지정해두면 편리하다.
create or replace view view_employee_all
as
select E.*,
            decode(substr(emp_no,8,1), '1','남','3','남','여') gender
from employee E;

select *
from view_employee_all
where gender = '여';

-----------------------------------------------------
--SEQUENCE
-----------------------------------------------------
--정수값을 순차적으로 자동생성하는 객체, 채번기
/*
필수
create sequence 시퀀스명

옵션
start with 시작값  ----------- 기본값 1

incremnet by 증가값 --------- 기본값 1

maxvalue 최대값 | nomaxvalue 
기본값 = nomaxvalue, 최대값에 도달하면 다시 시작값(cycle일때) 혹은 에러(no cycle일때)

minvalue 최소값 | nominvalue
기본값 = nominvalue, 최소값에 도달하면 다시 시작값(cycle) 혹은 에러(no cycle)

cycle | nocycle
순환여부를 나타내고 기본값은 nocycle

cache 캐싱계수 | nocache
기본값 cache 20, 시퀀스객체로 부터 20개씩 가져와서 메모리에서 채번
매번 시퀀스에 접근하지 않고 20번마다 접근하도록 하는것 = 효율 상승
오류가 발생하여, 숫자를 건너 뛸수도 있다...
*/

create table tb_names(
    no number,
    name varchar2(100) not null,
    constraint pk_tb_names_no primary key (no)
);

create sequence seq_names_no
    start with 1000
    increment by 1
    nomaxvalue
    nominvalue
    nocycle
    cache 20;


insert into tb_names
values (seq_names_no.nextval, '홍길동'); 
--여러번 실행

select *
from tb_names;
--결과값 확인


select seq_names_no.currval
from dual; --여러번실행해도 현재 번호 출력 

--dd에서 조회
select * from user_sequences;

--복합문자열에 시퀀스 사용하기
--주문번호 kh-20210205-1001
create table tb_order(
    order_id varchar2(50),
    cnt number,
    constraint pk_tb_order_id primary key(order_id)
);

create sequence seq_order_id;



insert into tb_order
values('kh-' || to_char(sysdate,'yyyymmdd') || '-' || to_char(seq_order_id.nextval,'fm0000'), 100);
--여러번 실행

select * from tb_order;

--alter, drop도 sequence 사용가능
--alter문을 통해 시작값, start with값은 절대 변경 할 수 없다. 
--start with값을 재지정 해주고 싶다면 시퀀스 객체 삭제후 재생성 할 것.
alter sequence seq_order_id
increment by 10;
--이런건 가능 ㅎ

--dd에서 조회
select * from user_sequences;



----------------------------------------------
-- INDEX
----------------------------------------------
-- 우리말로 하면 색인.
--sql문 처리 속도 향상을 위해 컬럼에 대해 생성하는 객체
--key: 컬럼값, value: 레코드논리적주소값 rowid
--저장하는 데이터에 대한 별도의 공간이 필요함.

--장점 : 
--검색속도가 빨라지고, 시스템 부하를 줄여서 성능향상

--단점:
--인덱스를 위한 추가저장공간이 필요.
--인덱스를 생성/수정하는 데 별도의 시간이 소요됨.
--데이터를 추가/삭제 할때는 오히러 부하를 증가시킴

--단순 조회 업무보다 변경작업(insert/ update / delete)가 많다면 
--인덱스 생성을 주의해야한다.

--인덱스로 사용하면 좋은 컬럼
-- 1. 선택도(selectivity)가 좋은 컬럼 = 중복데이터가 적은 컬럼
-- id | 주민번호 | email | 전화번호 > 이름 > 부서코드 >>>>> 성별
-- pk | uq 제약조건이 사용된 컬럼은 자동으로 인덱스를 생성함 -- 삭제하려면 제약조건을 삭제해야함.
-- 2. where 절에 자주 사용되어 지는경우, 조인 기준 컬럼인 경우
-- 3. 입력된 데이터의 변경이 적은 컬럼.

select *
from user_indexes
where table_name = 'EMPLOYEE';

-- 경우1. job_code 인덱스가 없는 컬럼
select *
from employee
where job_code = 'J5'; --table full scan

-- 경우2. emp_id 인덱스가 있는 컬럼
select *
from employee
where emp_id = '201';

--경우1 = scan방식 full, cost 3 (f10키로 확인)
--경우2 = scan방식 index-uniqueScan, cost 1 


--emp_name컬럼으로 인덱스를 생성해보자
--1. emp_name조회
select *
from employee
where emp_name = '송종기'; -- Scan 방식 = full, cost =3
--2. emp_name에 인덱스 생성
create index idx_employee_emp_name
on employee(emp_name);
--3. emp_name조회
select *
from employee
where emp_name = '송종기'; -- Scan 방식 = Range Scan(unique는 아님) = cost = 2

--결과 . 검색 cost가 3->2로 줄면서 검색 성능이 향상되었다.



--================================================================
-- PL / SQL
--================================================================
-- Procedural Language / SQL 
-- SQL의 한계를 보완해서 SQL문 안에서 변수정의 / 저건처리 / 반복처리 등의 문법을 지원


-- 유형
-- 1. 익명블럭(Anonymous Block) : PL / SQL 실행가능한 1회용블럭.
-- 2. Procedure : 특정 구문을 모아둔 서브프로그램. DB서버에 저장하고, 클라이언트에서 호출 실행.
-- 3. Function : 반드시 하나의 값을 리턴하는 서브프로그램, DB서버에 저장하고, 클라이언트에 의해 호출/실행.

-- 4. Trigger 
-- 5. Scheduler

/*

declare         --1.변수선언부(선택)

begin            --2.실행부(필수)

exception     --3.예외처리부(선택)

end;              --4.블럭종료선언(필수) 
/    --슬래시를 써줘야 끝남, 이부분에 라인주석 달면안된다!

*/


--세션별로 설정
--서버콘솔 출력모드 지정 on
set serveroutput on;

begin
    --dbms_output패키지의 put_line프로시져 : 출력문
    dbms_output.put_line('Hello PL/SQL');
end;
/

select *
from employee;
-- Sample
--사원조회
declare
    v_id number;
begin   
    select emp_id
    into v_id
    from employee
    where emp_name = '&사원명';
    /*
    &사원명 = 사용자로부터 사원명이라는 값을 입력받아
    select 
    from
    where절이 실행되어 결과값이
    into로 v_id에 저장이됨
    */
    
    dbms_output.put_line('사번 = ' || v_id);
exception
    when no_data_found then dbms_output.put_line('해당 이름을 가진 사원이 없습니다.');
    
end;
/


-------------------------------------
--  변수선언 / 대입
-------------------------------------
-- 변수명  [constant] 자료형 [not null] [ := 초기값];

declare
    num constant number := 100;
    name varchar2(100) not null := '홍길동'; --not null은 초기값 지정 필수
    result number;
begin
    dbms_output.put_line('num = ' || num);
--    num := 200; --값변경 불가
--    dbms_output.put_line('num = ' || num);
    name := '&이름';
    dbms_output.put_line('이름 : ' || name);
end;
/

--PL/SQL 자료형
--1. 기본자료형
--      문자형 : varchar2, char, clob
--      숫자형 : number
--      날짜형 : date
--      논리형 : boolean (true | false | null)
--2. 복합자료형
--      레코드
--      커서
--      컬렉션

--참조형은 다른 테이블의 자료형을 차용해서 쓸 수 있다.
--1. %type
--2. %rowtype
--3. record

declare
--      자료형 직접 지정
--    v_emp_name varchar2(100);
--    v_emp_no varchar2(100);

--      테이블의 값의 타입을 그대로 가져옴
        v_emp_name employee.emp_name%type;
        v_emp_no employee.emp_no%type;
    
begin
    select emp_name, emp_no
    into v_emp_name, v_emp_no
    from employee
    where emp_id = '&사번';
    
    dbms_output.put_line('이름 : ' || v_emp_name);
    dbms_output.put_line('주민번호 : ' || v_emp_no);

end;
/


--매번 컬럼별 변수를 설정해야하나 너무 귀찮다 싶을때  %rowtype
declare
    v_emp employee%rowtype; --테이브르이 한 행 전체를 타입으로 지정함

begin
    select *
    into v_emp -- 한줄을 통째로 때려박음
    from employee
    where emp_id = '&사번';
    
    dbms_output.put_line('사원명 : ' || v_emp.emp_name); --v_emp.컬럼명으로 사용함
    dbms_output.put_line('부서코드 : ' || v_emp.dept_code);
end;
/


--record
-- 사원명, 부서명을 할때 부서명은 employee에 없는데??
-- 존재하지않는 컬럼 조합을 record로 선언해버림

declare
    type my_emp_rec is record(
        emp_id employee.emp_id%type, 
        emp_name employee.emp_name%type,
        dept_title department.dept_title%type
    );
    
    my_row my_emp_rec;
begin

    select E.emp_id,
                E.emp_name,
                D.dept_title
    into my_row
    from employee E
        left join department D
            on E.dept_code = D.dept_id
    where emp_id = '&사번';
    
    --출력
    dbms_output.put_line('사번 : ' || my_row.emp_id);
    dbms_output.put_line('사원명 : ' || my_row.emp_name);
    dbms_output.put_line('부서명 : ' || my_row.dept_title);
    
end;
/


--사원명을 입력받고, 사번, 사원명, 직급명, 부서명을 참조형 변수를 통해 출력하세요.
declare
    type my_rec_type is record(
        emp_id employee.emp_id%type, 
        emp_name employee.emp_name%type,
        job_name job.job_name%type,
        dept_title department.dept_title%type
    );
    
    my_row my_rec_type;
    v_emp_name employee.emp_name%type;
begin
        v_emp_name := '&사원명';
        
        select e.emp_id, e.emp_name, d.dept_title, j.job_name
        into my_row
        from employee e, department d, job j
        where e.dept_code = d.dept_id(+)
            and e.job_code = j.job_code(+)
            and e.emp_name = v_emp_name;
            
        --출력
        dbms_output.put_line('사번 : ' || my_row.emp_id);
        dbms_output.put_line('사원명 : ' || my_row.emp_name);
        dbms_output.put_line('직급명 : ' || my_row.job_name);
        dbms_output.put_line('부서명 : ' || my_row.dept_title);
end;
/

-----------------------------------------------
-- PL/SQL 안의 DML
-----------------------------------------------
--이 안에서 commit / rollback / 트랜잭션(더 쪼갤수 없는 작업단위) 처리까지 해줄것.
--drop table member;
create table member(
    id varchar2(30),
    pwd varchar2(50) not null,
    name varchar2(100) not null,
    constraint member_id_pk primary key(id)
);

desc member;


begin
--    insert into member
--    values('honggd','1234','홍길동');
    
    update member set pwd = 'abcd'
    where id='honggd';
    
    --트랜잭션처리
    commit;
end;
/
select * from member;

--사용자 입력값을 받아서 id,pwd,name을 새로운 행으로 추가하는 익명블럭을 작성하세요
--내방법
begin
    insert into member
    values ('&아이디', '&비밀번호','&이름');
    --트랜잭션처리
    commit;
end;
/

select * from emp_copy;
desc emp_copy
-- emp_copy에 사번 마지막번호에 +1 처리한 사번으로 
-- 이름 주민번호 전화번호 직급코드 급여등급을 등록하는 PL/SQL 익명블럭 작성
begin
    insert into emp_copy (emp_id, emp_name, emp_no, phone, job_code, sal_level)
    values (
                    (
                        select max(emp_id)
                        from emp_copy;
                    )+1,  '&이름','&주민번호','&전화번호','&직급코드','&급여등급'
                );
    commit;
end;
/
--강사님 방법
-- 어차피 여러가지 명령어를 쓸수있으므로
--변수를 먼저 구하고 그 변수를 사용하셨음
declare
    last_num number;
begin
    --1. 사번 마지막 번호 구하기
    select max(emp_id)
    into last_num
    from emp_copy;
    dbms_output.put_line('last_num = ' || last_num);
    
    --2. 사용자입력값으로 insert문 실행
    insert into emp_copy (emp_id, emp_name, emp_no, phone, job_code, sal_level)
    values(last_num + 1, '&emp_name', '&emp_no', '&phone', '&job_code', '&sal_level');

    --3. transaction처리
    commit;
end;
/

-------------------------------
-- 조건문
-------------------------------
--1. if 조건식 then... end if;
--2. if 조건식 then ... else ... end if;
--3. if 조건식1 then ... elsif 조건식2 then ... end if;
-- else if 문에 'e'가 없는게 맞는거니까 조심해야한다

declare
    name varchar2(100) := '&이름';
begin
    if name = '홍길동' then
        dbms_output.put_line('반갑습니다. 홍길동님');
    else
        dbms_output.put_line('누구냐 넌?');
    end if;
    
    dbms_output.put_line('------------끝----------');
end;
/

declare
    num number := &숫자;
begin
    dbms_output.put_line('입력값 : ' || num);
    if mod(num, 3) = 0 
        then dbms_output.put_line('3의 배수를 입력하셨습니다.');
    elsif mod(num, 3) = 1
        then dbms_output.put_line('3으로 나눈 나머지가 1입니다.');
    elsif mod(num, 3) = 2
        then dbms_output.put_line('3으로 나눈 나머지가 2입니다.');
    end if;
    
end;
/


--사번을 입력받고, 해당사원 직급이 J1라면 '대표' 출력
--J2라면 '임원'
--그 외는 평사원 이라고 출력하세요

declare
    jobCode employee.job_code%type;

begin
    select job_code
    into jobCode
    from employee
    where emp_id =  '&사번';

    if jobCode = 'J1' 
        then dbms_output.put_line('대표');
    elsif jobCode = 'J2'
        then dbms_output.put_line('임원');
    else
        dbms_output.put_line('평사원');
    end if;

end;
/


-------------------------------
-- 반복문
-------------------------------
--1. 기본 loop - 무한반복(탈출조건 반드시 필요)
-- loop ~ end loop;

--2. while loop - 조건에 따른 반복
--3. for loop - 지정횟수만큼 반복실행, 탈출도알아서, 가장세련된방법

declare
    n number := 1; --증감변수

begin
    loop
        dbms_output.put_line(n);
        n := n+1;
        
        --exit구문필수 (java의 break같은 기능)
        --방법 2가지
        
--        if n>100
--            then exit;
--        end if;

        exit when n>100;
    end loop;

end;
/

--난수 출력
declare
    num number := 1;
    rnd number;
begin
    --반복문 10번 실행
    loop
        --start 이상, end 미만의 실수형 난수 생성
        rnd := trunc(dbms_random.value(1,11)); --1~10.99999까지의 난수
        dbms_output.put_line(num || ' : ' || rnd);
        num := num+1;
        exit when num>10;
    end loop;
    
end;
/

--while loop
--while 조건 loop ~~~ loop end;
declare
    n number := 0;

begin
    while n<10 loop
        --짝수만 출력
        if mod(n,2)=0
            then dbms_output.put_line(n);
        end if;
        
        n:= n+1;
    end loop;
end;
/
-- 사용자로 부터 단수(2~9단)을 입력받아 해당단수의 구구단을 출력하기
declare
    dan number := &단수;
    n number:=1;
begin
        if dan between 2 and 9 then
            dbms_output.put_line(dan || '단');
            while n<10 loop
                dbms_output.put_line(dan || ' * ' || n || ' = ' ||(dan*n));
                n := n+1;
            end loop;
        else
            dbms_output.put_line('잘못 입력하셨습니다.');
        end if;
end;
/

--for loop 가장 많이 사용 하니까 잘 알아둘것
--for ... loop
--증감변수를 별도로 선언 하지 않아도 됨
--심지어 증감변수 자동 증가처리됨
--Default값으로 1씩 증가
--Reverse 사용시 1씩 감소

begin
    --증감변수 n을 declare에 선언하지 않고 바로 사용가능
    --시작값..종료값 (시작값이 종료값보다 작아야함)
    for n in 1..5 loop --between 1 and 5
--    for n in reverse 1..5 loop  --5 4 3 2 1 로 출력하고 싶은경우
        dbms_output.put_line(n);
    end loop;
end;
/

--210 ~ 220번 사이의 사원을 조회 (사번, 이름, 전화번호)
declare
    e employee%rowtype;

begin
    for n in 210..220 loop
        select *
        into e
        from employee
        where emp_id = n;
        dbms_output.put_line('사번 : ' || e.emp_id);
        dbms_output.put_line('이름 : ' || e.emp_name);
        dbms_output.put_line('전화번호 : ' || e.phone);
        dbms_output.put_line('');
    end loop;

end;
/

--@실습 문제 : tb_number테이블에 난수(0~999) 100개를 저장하는 익명블럭을 생성하세요
--실행시마다 생성된 모든 난수의 합을 콘솔에 출력할 것.

drop sequence seq_tb_number_no;
drop table tb_number;
--채번할 시퀀스 생성
create sequence seq_tb_number_no
    start with 1
    increment by 1
    nomaxvalue
    nominvalue
    nocycle
    cache 100;

create table tb_number(
    id number, --pk sequence객체로 부터 채번
    num number, --난수
    reg_date date default sysdate,
    constraint pk_tb_number_id primary key(id)
);

declare
    rnd number;

begin
    for n in 1..100 loop
        rnd := trunc(dbms_random.value(0,1000));
        insert into tb_number (id, num)
        values (seq_tb_number_no.nextval, rnd);
        
    end loop;

end;
/

select *
from tb_number;


--============================================
-- DATABASE OBJECT2
--============================================
--PL/SQL문법을 사용하는 객체

----------------------------------------------
-- FUNCTION
----------------------------------------------
--문자열 앞뒤에 d...b헤드폰 씌우기 함수
--파라미터, 리턴 선언시 자료형 크기지정하지 말것

create or replace Function db_func (p_str varchar2) --파라미터 크기설정은 x
return varchar2 --리턴타입 설정
is --declare대신 함수에서는 is사용
    --사용할 지역변수 선언
    result varchar2(32767);

begin 
    --실행로직
    result := 'd' || p_str || 'b';
    return result;

--exception --예외처리는 역시 선택

end;
/

--실행
--1. 일반 sql문
select db_func(emp_name)
from employee;

--2. 익명블럭 / 다른 pl / sql 객체에서 호출가능
set serveroutput on;
begin
    dbms_output.put_line(db_func('&이름'));
    
end;
/

--3. exec | execute 프로시져/함수를 호출하는 명령어
var text varchar2; --변수선언
exec :text := db_func('신사임당'); --변수에 값대입시 변수명앞에 콜론필수
print text; --출력하기


--Data Dictionary에서 확인
--select * from user_functions; --이런건 없다
--Function은 프로시져의 하위 개념

select * 
from user_procedures
where object_type = 'FUNCTION';

--성별구하기 함수
create or replace function fn_get_gender(
    p_emp_no employee.emp_no%type
)
return varchar2
is
    gender varchar2(3);
begin 

--    if substr(p_emp_no,8,1) in('1','3') then
--        gender := '남';
--    else
--        gender := '여';
--    end if;

    --CASE type1
--    case
--        when substr(p_emp_no,8,1) in ('1','3')
--            then gender := '남';
--        else
--            gender := '여';
--    end case; --PL/SQL안에서는 end case로 끝남
                    
    --CASE type2
    case substr(p_emp_no,8,1)
        when '1' then gender:='남';
        when '3' then gender:='남';
        else gender := '여';
    end case;
    
     --추가적으로 DECODE문을 쓸수없음

    return gender;
end;
/

--확인해보기
select emp_name, fn_get_gender(emp_no)
from employee;


--주민번호를 입력받아 나이를 리턴하는 함수 fn_get_age를 작성하고
--사번, 사원명, 주민번호, 성별, 나이 조회(일반 SQL문)

create or replace function fn_get_age(
    e_emp_no employee.emp_no%type
)
return number
is
    age number;
begin
    case
        when substr(e_emp_no, 8, 1) in ('1','2')
            then age := extract(year from sysdate)-(1900+substr(e_emp_no,1,2))+1;
        else 
            age := extract(year from sysdate)-(2000+substr(e_emp_no,1,2))+1;
    end case;
    return age;
end;
/

select emp_id, emp_name,emp_no,
            fn_get_gender(emp_no) 성별,
            fn_get_age(emp_no) 나이
from employee;


------------------------------------
-- ProCedure
------------------------------------
-- 일련의 작업절차를 작성해 객체로 저장해둔것.
-- 함수와 다르게 리턴값이 없다.

--1.매개변수 없는 프로시져
select * from member;

create or replace procedure proc_del_member
is
    --지역변수 선언
begin
    --실행구문
    delete from member;
    commit;
end;
/

--a. 익명블럭 | 타 프로시져객체에서 호출 가능
begin
    proc_del_member; --익명블럭에서는 함수호출하듯 그냥 쓰면됨
end;
/

--b. execute 명령
exec proc_del_member;

--DD에서 확인
select *
from user_procedures
where object_type = 'PROCEDURE';

select *
from user_source
where name = 'PROC_DEL_MEMBER'; --프로시져 라인단위 확인가능


--2. 매개변수 있는 프로시져
--매개변수 mode 기본값 in

--매개변수의 사번을 가진 사원 삭제
create or replace procedure proc_del_emp_by_id(
    p_emp_id in emp_copy.emp_id%type --기본 mode 값 in
)
is

begin
    delete from emp_copy
    where emp_id = p_emp_id;
    commit;
    dbms_output.put_line(p_emp_id || '번 사원을 삭제했스니다.');

end;
/

--실행해보기
select * from emp_copy;

begin
    proc_del_emp_by_id('&삭제할_사번');
end;
/


-- out 매개변수 사용하기
-- 사번을 전달해서 사원명, 전화번호를 리턴(out매개변수)받을 수 있는 프로시져
--in out 동시에 사용가능
create or replace procedure proc_select_emp_by_id(
    p_emp_id in emp_copy.emp_id%type,
--    p_emp_id in out emp_copy.emp_id%type,
    p_emp_name out emp_copy.emp_name%type,
    p_emp_phone out emp_copy.phone%type
)
is

begin
    select emp_name, phone
    into p_emp_name, p_emp_phone --out매개변수에 담는다
    from emp_copy
    where emp_id = p_emp_id;

end;
/

--익명블럭 호출(client)
declare 
    --out변수로 사용할 녀석들만 선언해놨음
    v_emp_name emp_copy.emp_name%type;
    v_phone emp_copy.phone%type;

begin
    --out매개변수용 변수는 공간을 전달한다는 개념으로 보면 좋을것같다
    proc_select_emp_by_id('&사번', v_emp_name, v_phone);
    dbms_output.put_line('v_emp_name : ' || v_emp_name);
    dbms_output.put_line('v_phone : ' || v_phone);

end;
/

--upsert 예제 : insert + update
create table job_copy
as
select * from job; --not null제약조건을 제외한 나머지 제약조건은 날아감

select * from job_copy;

--pk제약조건 추가
alter table job_copy
add constraint pk_job_copy primary key(job_code)
modify job_name not null;

--제약조건 확인
select constraint_name,
            uc.table_name,
            ucc.column_name,
            uc.constraint_type,
            uc.search_condition
from user_constraints uc
    join user_cons_columns ucc
        using(constraint_name)
where uc.table_name = 'JOB_COPY';

--직급정보를 추가하는 프로시져
create or replace procedure proc_man_job_copy(
    p_job_code in job_copy.job_code%type,
    p_job_name in job_copy.job_name%type
)
is

begin
    insert into job_copy
    values(p_job_code, p_job_name);
    commit;
end;
/

--익명블럭에서 호출
begin
    proc_man_job_copy('J8', '주임');
end;
/

select * from job_copy;

--위에 설정한 프로시져로는 기존값이 존재하지 않으면 생성해주지만
--기존값이 존재한다면 에러가 난다.
--우리는 위의 설정한 프로시져를 존재하지 않는값이면 생성, 존재하는값이면 수정으로 변경해줄것


create or replace procedure proc_man_job_copy(
    p_job_code in job_copy.job_code%type,
    p_job_name in job_copy.job_name%type
)
is
    cnt number;
begin
    --1. 존재여부 확인
    select count(*)
    into cnt
    from job_copy
    where job_code = p_job_code;
    
    --2. 분기처리
    if cnt>0 then
        --존재하면 update
        update job_copy
        set job_name = p_job_name
        where job_code = p_job_code;
    else
        --존재하지 않으면 insert
        insert into job_copy
        values(p_job_code, p_job_name);
    end if;
    
    --트랜잭션처리
    commit;
end;
/
--익명블럭에서 호출
begin
    proc_man_job_copy('J9', '주임2');
end;
/

select * from job_copy;



----------------------------------------
-- CURSOR
----------------------------------------
-- SQL문의 처리결과 ResultSet을 가리키고 있는 포인터객체
-- 하나이상의 row에 순차적으로 접근할 수 있다

--1. 암묵적 커서 : 모든 SQL실행시 암묵적 커서가 만들어져 처리됨.
--2. 명시적 커서 : 변수로 선언후, open~fetch~close 과정에 따라 행에 접근 할 수 있다.

--employee에서 한행씩 정보를 모두 출력해보자~
declare
    v_emp emp_copy%rowtype;
    cursor my_cursor
    is
    select * from emp_copy
    order by emp_id; --이쿼리의 실행결과에 커서가 접근할수있다 정도

begin
    open my_cursor;
    loop
        fetch my_cursor into v_emp;
        exit when my_cursor%notfound;
        dbms_output.put_line('사번 : ' || v_emp.emp_id);
        dbms_output.put_line('사원명 : ' || v_emp.emp_name);
    end loop;
    close my_cursor;
    
end;
/



--파라미터가 있는 커서
declare
    v_emp emp_copy%rowtype;
    cursor my_cursor(p_dept_code emp_copy.dept_code%type) --이부분추가
    is
    select * from emp_copy
    where dept_code = p_dept_code --where 절 추가
    order by emp_id; 

begin
    open my_cursor('&부서코드'); --이부분에서 파라미터 전달해줘야함
    loop
        fetch my_cursor into v_emp;
        exit when my_cursor%notfound;
        dbms_output.put_line('사번 : ' || v_emp.emp_id);
        dbms_output.put_line('사원명 : ' || v_emp.emp_name);
        dbms_output.put_line('부서코드 : ' || v_emp.dept_code);--추가
        dbms_output.put_line('');
    end loop;
    close my_cursor;
    
end;
/


--for..in 문을 통해 처리
--1. open-fetch-close작업 자동
--2. 행변수는 자동으로 선언

declare
    cursor my_cursor
    is
    select * from employee;
begin 
    --my_row는 변수를 써준것 선언해주지 않아도됨
    --my_cursor에서 한행씩 받아옴
    --아주 유용하고 편하고 유연함
    for my_row in my_cursor loop 
        dbms_output.put_line(my_row.emp_id || ' : ' || my_row.emp_name);
    end loop;
end;
/


---------------------------------------
-- TRIGGER
---------------------------------------
--방아쇠, 연쇄반응
--특정이벤트(DDL, DML, LOGON)가 발생했을때,
--실행될 코드를 모아둔 데이터베이스 객체.

--종류
--1. DDL Trigger
--2. DML Trigger
--3. LOGON/LOGOFF Trigger


--게시판 테이블의 게시물 삭제
--1. 삭제여부컬럼 : del_flag 'N' -> 'Y'
--2. 삭제테이블 : 삭제된 행 데이터를 삭제테이블에 insert

/*
create or replace trigger 트리거명
    befor | after --원 DML문 실행전 | 실행 후에 trigger실행
    insert | update | delete on 테이블명
    [for each row] -- 행 level 트리거, 생략하면 문장 level 트리거가됨
    
begin
    --실행코드
end;
/
*/

/*
- 행 레벨 트리거 : 원 DML문(10행이라면)이 처리되는 행마다(10번) trigger 실행

- 문장 레벨 트리거 : 원 DML문이 실행시 trigger 한번 실행

의사 pseudo 레코드 (행레벨 트리거에서만 유효)
- :old 원 DML문 실행전 데이터
- :new 원 DML문 실행후 데이터

**트리거 내부에서는 transaction처리 하지 않는다.
원 DML문의 트랜잭션에 자동포함된다.

*/



create or replace trigger trig_emp_salary
    before --4341~4343실행전에 실행해라
    insert or update on emp_copy --이게 실행될때, 여러개일때 or로 연결
    for each row --한행씩
begin
    dbms_output.put_line('변경전 salary : ' || :old.salary);
    dbms_output.put_line('변경후 salary : ' || :new.salary);
    dbms_output.put_line('');
end;
/

update emp_copy
set salary = salary + 100000
where dept_code = 'D5';

--emp_copy에 PK가 없어서 추가해줬음
alter table emp_copy
add constraint pk_emp_copy_emp_id primary key (emp_id);

--급여 변경내역 로그 테이블
create table emp_copy_salary_log(
    emp_id varchar2(3),
    before_salary number,
    after_salary number,
    log_date date default sysdate,
    constraint fk_salary_log_emp_id foreign key(emp_id) references emp_copy(emp_id)
);

select * from emp_copy;
select * from emp_copy_salary_log;

create or replace trigger trig_emp_salary_log
    before --4341~4343실행전에 실행해라
    insert or update on emp_copy --이게 실행될때, 여러개일때 or로 연결
    for each row --한행씩
begin
    dbms_output.put_line('변경전 salary : ' || :old.salary);
    dbms_output.put_line('변경후 salary : ' || :new.salary);
    dbms_output.put_line('');
    
    insert into emp_copy_salary_log(emp_id, before_salary, after_salary)
    values (:new.emp_id, :old.salary, :new.salary);
    --emp_id를 왜 new에서 가져오냐??
    --insert에서는 아직 값이 들어가지 있지 않기 때문에
    --commit과 같은 트랜잭션 처리를 하지 않는다.
    --원문 DML과 같이 트랜잭션 되기때문에 여기서하면 오류난다
end;
/
--실행
update emp_copy
set salary = salary + 100000
where dept_code = 'D5';

rollback; --트리거에서 실행된 dml문도 함께 rollback된다

select * from emp_copy_salary_log;


--@실습문제 : 
--emp_copy에서 사원을 삭제할 경우, emp_copy_del 테이블로 데이터를 이전시키는 trigger를 생성
--quit_date에 현재날짜를 기록할 것
create table emp_copy_del
as
select E.*
from emp_copy E
where 1=2; --모양,형식만 따감

--drop trigger emp_copy_del_log;
--트리거 생성
create or replace trigger emp_copy_del_log
    before
    delete on emp_copy
    for each row
    
begin
    insert into emp_copy_del
    values (:old.emp_id, :old.emp_name, :old.emp_no, :old.email, :old.phone, :old.dept_code, :old.job_code,:old.sal_level,:old.salary,
    :old.bonus, :old.manager_id, :old.hire_date, sysdate, 'Y');
    --이걸 한줄로 줄이는 방법 찾아보기
end;
/

commit;
rollback;

delete from emp_copy
where emp_id = '200';

select * from emp_copy;
select * from emp_copy_del;

desc emp_copy;




--상품 재고 관리
--테이블을 두개 만듦

--상품테이블
--drop table product
create table product(
    pcode number, --상품코드
    pname varchar2(100), --상품명
    price number, --가격
    stock_cnt number default 0, --재고
    constraint pk_product_pcode primary key(pcode)
);

--재고테이블
--drop table product_io
create table product_io (
    iocode number,
    pcode number,
    amount number,
    status char(1),
    io_date date default sysdate,
    constraint pk_product_io_code primary key(iocode),
    constraint fk_product_io_pcode foreign key (pcode) 
        references product(pcode),
    constraint ck_product_io_status check(status in ('I','O'))
);

--drop sequence seq_product_pcode;
--drop sequence seq_product_iocode;
create sequence seq_product_pcode;
create sequence seq_product_io_iocode
start with 1000;

insert into product
values (seq_product_pcode.nextval, '아이폰12', 1500000, 0);

insert into product
values (seq_product_pcode.nextval, '갤럭시21', 990000, 0);


--입출고 데이터가 insert되면, 해당상품의 재고수량을 변경하는 트리거
create or replace trigger trg_product
    before
    insert on product_io
    for each row
begin
    --입고
    if :new.status = 'I' then
        update product
        set stock_cnt = stock_cnt + :new.amount
        where pcode = :new.pcode;
    --출고
    else
        update product
        set stock_cnt = stock_cnt - :new.amount
        where pcode = :new.pcode;
    end if;
end;
/

--입출고 내역
insert into product_io
values (seq_product_io_iocode.nextval, 1, 5, 'I' , sysdate);
insert into product_io
values (seq_product_io_iocode.nextval, 1, 100, 'I' , sysdate);
insert into product_io
values (seq_product_io_iocode.nextval, 1, 30, 'I' , sysdate);

select * from product;
select * from product_io;

commit;

--1. 원DML문의 대상테이블에 접근 할 수 없다.
--2. 트리거 안에서는 원 DML문을 제어 할 수 없다.














