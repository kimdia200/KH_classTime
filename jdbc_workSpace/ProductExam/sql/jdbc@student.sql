--상품테이블 PRODUCT_STOCK
--* PRODUCT_ID  VARCHAR2(30) PRIMARY KEY,
--* PRODUCT_NAME  VARCHAR2(30)  NOT NULL,
--* PRICE NUMBER(10)  NOT NULL,
--* DESCRIPTION VARCHAR2(50),
--* STOCK NUMBER DEFAULT 0 

--drop table product_stock
create table product_stock(
    product_id varchar2(30),
    product_name varchar2(30) not null,
    price number(10) not null,
    description varchar2(50),
    stock number default 0,
    constraint pk_product_stock_id primary key(product_id)
);


--상품입축고 테이블 PRODUCT_IO
--* IO_NO NUMBER PRIMARY KEY => sequence처리할 것.
--* PRODUCT_ID VARCHAR2(30) => PRODUCT_STOCK테이블 PRODUCT_ID 참조
--* IODATE DATE DEFAULT SYSDATE
--* AMOUNT NUMBER
--* STATUS CHAR(1) CHECK (STATUS IN ('I', 'O'))

--drop table product_io
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

--번호 부여 시퀀스 생성
create sequence seq_product_io
    start with 1
    increment by 1;

--트리거 생성
create or replace trigger tri_product
    before
    update on product_io
    for each row
begin
    --입고
    if :new.status = 'I' then
        update product_stock
        set stock = stock + :new.amount
        where product_id = :new.product_id;
    --출고
    else
        update product_stock
        set stock = stock - :new.amount
        where product_id = :new.product_id;
    end if;
end;
/









