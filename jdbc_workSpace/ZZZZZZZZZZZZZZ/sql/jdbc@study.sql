--계정생성
create user product
identified by product
default tablespace users;

--권한부여
grant connect, resource to product;

--테이블 두개
--시퀀스 하나
--트리거 하나

--상품재고 테이블 product_stock
drop table product_stock;

create table product_stock(
    product_id varchar2(30),
    product_name varchar2(30) not null,
    price number(10) not null,
    description varchar2(50),
    stock number default 0,
    constraint pk_product_stock_id primary key(product_id)
);

--상품입출고 테이블 product_io
drop table product_io;

create table product_io(
    io_no number,
    product_id varchar2(30),
    iodate date default sysdate,
    amount number,
    status char(1),
    constraint pk_product_io_no primary key(io_no),
    constraint fk_product_io_id foreign key(product_id) 
        references product_stock(product_id),
    constraint ck_product_io_status check(status in ('I','O'))
);

--시퀀스 생성
drop sequence seq_product;

create sequence seq_product
    start with 1
    increment by 1;
    
--트리거 생성
--product_io 에 레코드or튜플or행 이 추가될때마다 product_stock에 재고량 변경
drop trigger tri_product;

create or replace trigger tri_product
    before
    insert on product_io
    for each row
begin
    --입고
    if :new.status = 'I' then
        update product_stock
        set stock = stock + :new.amount
        where product_id = :new.product_id;
    --출고
    else --O겠지
        update product_stock
        set stock = stock - :new.amount
        where product_id = :new.product_id;
    end if;
end;
/

commit;
rollback;

insert into product_stock
values ('2','배',6000,'맛있는 배', 0);

insert into product_io
values(seq_product.nextval, '1', sysdate, 20, 'I');


select * from product_stock;

select * from product_io;












