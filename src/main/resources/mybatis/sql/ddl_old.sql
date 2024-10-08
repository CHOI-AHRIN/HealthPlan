
DESC BREPLY;

        SELECT 
            r.rno, 
            r.mno, 
            r.bno, 
            r.rComment, 
            r.rRegdate, 
            r.status,
            b.chno, 
            a.aNo, 
            a.fileName
        FROM 
            BREPLY r
        LEFT JOIN 
            BOARD b ON r.bno = b.bno
        LEFT JOIN 
            BATTACHED a ON r.bno = a.bno  
        ORDER BY 
            r.rno;

      
-- 테이블 생성
CREATE TABLE SUBSCRIBE (
   SNO NUMBER NOT NULL,
   MNO NUMBER NULL,
   STYPE CHAR(1) NULL,
   TITLE VARCHAR2(100) NULL,
   CONTENTS VARCHAR2(2000) NULL,
   WDATE DATE NULL,
   COUNTS NUMBER NULL
);

-- 컬럼 주석 추가
COMMENT ON COLUMN SUBSCRIBE.STYPE IS 'S:구독/T:전문가/L:강의';


CREATE TABLE POINT (
   PNO   NUMBER   NOT NULL,
   MNO   NUMBER   NULL,
   PCOUNT   NUMBER   NULL,
   PSOURCE   VARCHAR2(30)   NULL,
   PDATE   DATE   NULL
);

-- 컬럼 주석 추가
COMMENT ON COLUMN POINT.PSOURCE IS '1. 수입료, 2. 충전 3. 무료포인트';

CREATE TABLE MEMBER (
   MNO   NUMBER   NOT NULL,
   UUID   VARCHAR2(30)   NULL,
   UPW   VARCHAR2(50)   NULL,
   PHONE   VARCHAR2(20)   NULL,
   NAME   VARCHAR2(20)   NULL,
   MTYPE   CHAR(1)   NULL,
   EMAIL   VARCHAR(100)   NULL,
   REGDATE   DATE   NULL
);

-- 컬럼 주석 추가
COMMENT ON COLUMN MEMBER.MTYPE IS  'T: 전문가 M: 일반 사용자 A: 운영자';



CREATE TABLE BREPLY (
   RNO   NUMBER   NOT NULL,
   MNO   NUMBER   NULL,
   BNO   NUMBER   NULL,
   RCOMMENT   VARCHAR2(1000)   NULL,
   RREGDATE   DATE   NULL
);

CREATE TABLE BATTACHED (
   ANO   VARCHAR2(50)   NOT NULL,
   BNO   NUMBER   NULL,
   FILENAME   VARCHAR2(50)   NULL
);

CREATE TABLE BOARD (
   BNO   NUMBER   NOT NULL,
   MNO   NUMBER   NULL,
   BTYPE   CHAR(1)   NULL,
   TITLE   VARCHAR2(100)   NULL,
   BCONTENTS   VARCHAR2(2000)   NULL,
   WDATE   DATE   NULL,
   BCOUNTS   NUMBER   NULL
);

-- 컬럼 주석 추가
COMMENT ON COLUMN BOARD.BTYPE IS 'H: 챌린지 F:FAQ';

   

CREATE TABLE ORDERS (
   ONO   NUMBER   NOT NULL,
   MNO   NUMBER   NULL,
   OTYPE   CHAR(1)   NULL,
   STATUS   CHAR(1)   NULL,
   OAMOUNT   NUMBER   NULL,
   PRICE   NUMBER   NULL,
   OCOMMENT   VARCHAR2(500)   NULL,
   ODATE   DATE   NULL
);

-- 컬럼 주석 추가
COMMENT ON COLUMN ORDERS.OTYPE IS  'S  / A  / B / C  / N';
COMMENT ON COLUMN ORDERS.STATUS IS 'O: 주문 B: 장바구니 R: 반품';


CREATE TABLE COMMUNITY (
   CNO   NUMBER   NOT NULL,
   MNO   NUMBER   NULL,
   CTYPE   CHAR(1)   NULL   ,
   TITLE   VARCHAR2(100)   NULL,
   CONTENTS   VARCHAR2(2000)   NULL,
   BDATE   DATE   NULL,
   COUNTS   NUMBER   NULL
);

-- 컬럼 주석 추가
COMMENT ON COLUMN COMMUNITY.CTYPE IS   'D: 식단  E: 운동';


CREATE TABLE CREPLY (
   RNO   NUMBER   NOT NULL,
   MNO   NUMBER   NULL,
   CNO   NUMBER   NULL,
   RCOMMENT   VARCHAR2(1000)   NULL,
   RREGDATE   DATE   NULL
);

CREATE TABLE SREPLY (
   RNO   NUMBER   NOT NULL,
   MNO   NUMBER   NULL,
   SNO   NUMBER   NULL,
   RCOMMENT   VARCHAR2(1000)   NULL,
   RREGDATE   DATE   NULL
);

CREATE TABLE CATTACHED (
   ANO   VARCHAR2(50)   NOT NULL,
   CNO   NUMBER   NULL,
   FILENAME   VARCHAR2(50)   NULL
);

CREATE TABLE SATTACHED (
   ANO   VARCHAR2(50)   NOT NULL,
   SNO   NUMBER   NOT NULL,
   FILENAME   VARCHAR2(50)   NULL
);

--

ALTER TABLE BOARD ADD(DIF   VARCHAR(5)   NOT NULL);
ALTER TABLE BOARD ADD(CHNO   VARCHAR(1)   NOT NULL);
ALTER TABLE BOARD ADD(STATUS   CHAR(1)   NOT NULL);

INSERT INTO BOARD VALUES (123, 123, 'H', '안녕하세요', '내용입니다', SYSDATE, '', '', '', '');

ALTER TABLE BOARD MODIFY(STATUS NULL);
ALTER TABLE BOARD MODIFY(DIF NULL);
ALTER TABLE BOARD MODIFY(CHNO NULL);

SELECT * FROM BOARD;
DELETE FROM BOARD WHERE BNO=123;


DROP TRIGGER board_bno_trigger;

CREATE OR REPLACE TRIGGER board_bno_trigger
BEFORE INSERT ON board
FOR EACH ROW
BEGIN
  SELECT board_seq.NEXTVAL
  INTO :new.bno
  FROM dual;
END;
/
ALTER TABLE board MODIFY dif VARCHAR2(20);

----
SELECT 
   COUNT() AS order_count
FROM BOARD
GROUP BY 
    TRUNC(WDATE)
ORDER BY 
    WDATE;


SELECT
FROM BOARD
ORDER BY TRUNC(WDATE) DESC, CHNO ASC;
COMMIT;

DELETE FROM BOARD;
COMMIT;

ALTER TABLE BOARD DROP COLUMN STATUS;
ALTER TABLE BREPLY ADD STATUS VARCHAR2(50); 

SELECT * FROM BATTACHED;
SELECT * FROM BREPLY;
SELECT * FROM BOARD;
INSERT INTO BREPLY VALUES (222, NULL, 121244, 'hihi', SYSDATE, NULL);

DELETE FROM BREPLY;
COMMIT;

-----이게 ㄹㅇ죵나 중요함 이거 시퀀스랑 트리거 생성안하면 작동안함 (오늘의 챌린지 글 번호 증가)

-- 시퀀스 생성
CREATE SEQUENCE seq_board_bno
START WITH 1
INCREMENT BY 1
NOCACHE;


-- 트리거 삭제
DROP TRIGGER HEALTHPLAN.BOARD_BNO_TRIGGER;

-- 트리거 생성
CREATE OR REPLACE TRIGGER HEALTHPLAN.BOARD_BNO_TRIGGER
BEFORE INSERT ON board
FOR EACH ROW
BEGIN
  -- 보드 번호 자동 증가
  SELECT seq_board_bno.NEXTVAL INTO :NEW.bno FROM dual;
END;
/
------------------------------------------------------------------------
-- 이것도 댓글번호 자동증가 시퀀스/트리거
CREATE SEQUENCE RNO_SEQ
START WITH 1
INCREMENT BY 1;

CREATE OR REPLACE TRIGGER BREPLY_RNO_TRIGGER
BEFORE INSERT ON BREPLY
FOR EACH ROW
BEGIN
    :NEW.RNO := RNO_SEQ.NEXTVAL;
END;



----
CREATE SEQUENCE BReply_seq
START WITH 1
INCREMENT BY 1
NOCACHE;
--
CREATE SEQUENCE BOARD_SEQ
START WITH 1
INCREMENT BY 1
NOCACHE;
