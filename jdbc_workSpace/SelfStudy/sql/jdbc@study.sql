--===============================
-- 관리자 계정 에서 실행
--===============================
--student 계정 생성 및 권한부여
create user study
identified by study
default tablespace users;

grant connect, resource to study;


drop sequence seq_stock;
drop sequence seq_upsert_stock;
drop table upsert_stock;
drop table stock;
drop trigger tri_upsert_stock;

--===============================
-- stock테이블에서 사용할 no컬럼에 번호부여해줄 sequence생성
--===============================
--drop sequence seq_stock;
create sequence seq_stock
    start with 1
    increment by 1;
    
--시퀀스 생성 확인
select * from user_sequences;

--===============================
-- upsert_stock테이블에서 사용할 no컬럼에 번호부여해줄 sequence생성
--===============================
--drop sequence seq_upsert_stock;
create sequence seq_upsert_stock
    start with 1
    increment by 1;
    
--시퀀스 생성 확인
select * from user_sequences;

--===============================
-- stock 테이블 생성
--===============================
--drop table stock;
create table stock(
    no number,
    product varchar2(100) not null,
    quantity number default 0,
    constraint pk_stock_no primary key(no)
);
--테이블 생성 확인
select * from stock;

--잘 들어가나 확인 해봄
--insert into stock(no,product)
--values (SEQ_STOCK.nextval,'사과');


--===============================
-- upsert_stock 테이블 생성
--===============================
--drop table upsert_stock;
create table upsert_stock(
    no number,
    product_no number,
    product varchar2(100) not null,
    quantity number default 0,
    modification_date date default sysdate,
    constraint pk_upsert_stock_no primary key(no)
--    ,constraint fk_upsert_stock_product_no foreign key(product_no)
--        references stock(no)
);
--테이블 생성 확인
select * from upsert_stock;

--===============================
-- stock테이블 변화시 upsert_stock에 기록 남겨줄
-- TRIGGER 생성
--===============================
--drop trigger tri_upsert_stock;
create or replace trigger tri_upsert_stock
    before
    insert or update on stock
    for each row
begin
    insert into upsert_stock(no, product_no, product, quantity)
    values(seq_upsert_stock.nextval,:new.no, :new.product, :new.quantity);
end;
/
--TRIGGER 생성확인
select * from user_triggers;

insert into stock(no,product)
values(seq_stock.nextval, '사과');

update stock
set quantity = 100
where no = 1;

select * from stock order by no;
select * from upsert_stock order by no;

commit;
rollback;









