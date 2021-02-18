@최종실습문제
상품재고관리프로그램을 작성하세요.
다음과 같은 데이터를 담을수 있도록 처리하세요.
--------------------------------------------------------------
product_id      p_name      price       description     stock
--------------------------------------------------------------
nb_ss7          삼성노트북  1570000        시리즈 7        55
nb_macbook_air  맥북에어    1200000     애플 울트라북     0
pc_ibm          ibmPC       750000      windows 8         10
--------------------------------------------------------------
메인메뉴
***** 상품재고관리프로그램 *****
1. 전체상품조회
2. 상품아이디검색
3. 상품명검색
4. 상품추가
5. 상품정보변경
6. 상품삭제
7. 상품입/출고 메뉴
9. 프로그램종료
수정메뉴
***** 상품정보변경메뉴 *****
1.상품명변경
2.가격변경
3.설명변경
9.메인메뉴로 돌아가기
입출고메뉴
***** 상품입출고메뉴*****
1. 전체입출고내역조회
2. 상품입고
3. 상품출고
9. 메인메뉴로 돌아가기
* 상품정보를 삭제하면, 해당 입출고 데이터도 삭제되도록 처리하세요.
* 입고된 수량보다 많은 수량을 출고하려하면, 에러메세지를 보이고, 실행중지하세요.
* 상품입출고테이블에 데이터가 삽입될때마다, 자동으로 재고테이블의 수량이
변경될 수 있도록 트리거를 작성하세요.
상품테이블 PRODUCT_STOCK
* PRODUCT_ID  VARCHAR2(30) PRIMARY KEY,
* PRODUCT_NAME  VARCHAR2(30)  NOT NULL,
* PRICE NUMBER(10)  NOT NULL,
* DESCRIPTION VARCHAR2(50),
* STOCK NUMBER DEFAULT 0
상품입축고 테이블 PRODUCT_IO
* IO_NO NUMBER PRIMARY KEY => sequence처리할 것.
* PRODUCT_ID VARCHAR2(30) => PRODUCT_STOCK테이블 PRODUCT_ID 참조
* IODATE DATE DEFAULT SYSDATE
* AMOUNT NUMBER
* STATUS CHAR(1) CHECK (STATUS IN ('I', 'O'))
