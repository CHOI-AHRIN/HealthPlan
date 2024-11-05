-- Active: 1728281287544@@127.0.0.1@1521@XE@HEALTH
-- 171 line: PasswordEncryptor.java 실행해서 터미널에 프린트 된 비밀번호를 UPW의 VALUE 값에다 삽입 후 전체 실행

-- 회원 테이블
CREATE TABLE Member (
    mNo NUMBER NOT NULL, -- 회원번호
    uuid VARCHAR2(230), -- 아이디
    upw VARCHAR2(1000), -- 비밀번호
    phone VARCHAR2(100), -- 전화번호
    name VARCHAR2(220), -- 이름
    mType CHAR(1), -- 회원타입
    email VARCHAR2(100), -- 이메일
    regdate DATE, -- 가입일
    sessionKey VARCHAR2(250), -- 세션키
    sessionLimit TIMESTAMP, -- 세션만료시간
    ssType CHAR(1), -- 구독타입
    PRIMARY KEY (mNo)
);

-- 보드(챌린지, FAQ) 테이블
CREATE TABLE Board (
    bNo NUMBER NOT NULL, -- 보드번호
    mNo NUMBER, -- 회원번호
    bType CHAR(1), -- 게시물종류
    title VARCHAR2(100), -- 글제목
    bContents VARCHAR2(2000), -- 글내용
    wDate DATE, -- 작성일자
    bCounts NUMBER, -- 조회수
    PRIMARY KEY (bNo),
    FOREIGN KEY (mNo) REFERENCES Member (mNo) ON DELETE CASCADE
);

-- 보드 댓글 테이블
CREATE TABLE BReply (
    rNo NUMBER NOT NULL, -- 댓글번호
    mNo NUMBER, -- 회원번호
    bNo NUMBER, -- 보드번호
    rComment VARCHAR2(1000), -- 댓글내용
    rRegdate DATE, -- 댓글작성일
    PRIMARY KEY (rNo),
    FOREIGN KEY (bNo) REFERENCES Board (bNo) ON DELETE CASCADE,
    FOREIGN KEY (mNo) REFERENCES Member (mNo) ON DELETE CASCADE
);

-- 구독 테이블
CREATE TABLE Subscribe (
    sNo NUMBER NOT NULL, -- 구독번호
    uuid VARCHAR2(230), -- 아이디
    mNo NUMBER NOT NULL, -- 회원번호
    lNo NUMBER, -- 구독상품번호
    sType CHAR(1), -- 구독상품타입
    Title VARCHAR2(100), -- 글제목
    Contents VARCHAR2(2000), -- 글내용
    wDate DATE, -- 작성일자
    Counts NUMBER, -- 조회수
    fileId VARCHAR2(250), -- 파일ID
    fileName VARCHAR2(250), -- 파일이름
    replycnt NUMBER, -- 댓글수
    spoint NUMBER, -- 포인트
    PRIMARY KEY (sNo),
    FOREIGN KEY (mNo) REFERENCES Member (mNo) ON DELETE CASCADE
);

-- 구독 댓글 테이블
CREATE TABLE SReply (
    rNo NUMBER NOT NULL, -- 댓글번호
    mNo NUMBER, -- 회원번호
    sNo NUMBER, -- 구독번호
    rComment VARCHAR2(1000), -- 댓글내용
    rRegdate DATE, -- 댓글작성일
    UREGDATE DATE, -- 댓글수정일
    PRIMARY KEY (rNo),
    FOREIGN KEY (sNo) REFERENCES Subscribe (sNo) ON DELETE CASCADE,
    FOREIGN KEY (mNo) REFERENCES Member (mNo) ON DELETE CASCADE
);

-- 커뮤니티 테이블
CREATE TABLE Community (
    cNo NUMBER NOT NULL, -- 커뮤니티번호
    mNo NUMBER, -- 회원번호
    cType CHAR(1), -- 커뮤니티소분류
    Title VARCHAR2(100), -- 글제목
    Contents VARCHAR2(2000), -- 글내용
    wDate DATE, -- 작성일자
    Counts NUMBER, -- 조회수
    replycnt NUMBER, -- 댓글수
    PRIMARY KEY (cNo),
    FOREIGN KEY (mNo) REFERENCES Member (mNo) ON DELETE CASCADE
);

-- 커뮤니티 댓글 테이블
CREATE TABLE CReply (
    rNo NUMBER NOT NULL, -- 댓글번호
    mNo NUMBER, -- 회원번호
    cNo NUMBER, -- 커뮤니티번호
    rComment VARCHAR2(1000), -- 댓글내용
    rRegdate DATE, -- 댓글작성일
    PRIMARY KEY (rNo),
    FOREIGN KEY (cNo) REFERENCES Community (cNo) ON DELETE CASCADE,
    FOREIGN KEY (mNo) REFERENCES Member (mNo) ON DELETE CASCADE
);

-- 포인트 테이블
CREATE TABLE Point (
    pNo NUMBER NOT NULL, -- 포인트번호
    mNo NUMBER, -- 회원번호
    pSource VARCHAR2(100), -- 포인트출처
    pcount NUMBER,
    pDate DATE, -- 포인트생성일
    PRIMARY KEY (pNo),
    FOREIGN KEY (mNo) REFERENCES Member (mNo) ON DELETE CASCADE
);

-- 주문 테이블
CREATE TABLE Orders (
    oNo NUMBER NOT NULL, -- 주문번호
    mNo NUMBER, -- 회원번호
    oType CHAR(1), -- 주문타입
    status CHAR(1), -- 상태
    amount NUMBER, -- 금액
    price NUMBER, -- 가격
    oComment VARCHAR2(250), -- 주문코멘트
    oDate DATE, -- 주문일자
    PRIMARY KEY (oNo),
    FOREIGN KEY (mNo) REFERENCES Member (mNo) ON DELETE CASCADE
);

-- 첨부 파일 테이블
CREATE TABLE NATTACH (
    UUID VARCHAR2(200),
    IMGNAME VARCHAR2(200),
    SNO NUMBER,
    ANO NUMBER,
    PATH VARCHAR2(200),
    IMAGEURL VARCHAR2(200),
    IMGTYPE CHAR(1 BYTE),
    REGDATE DATE,
    FOREIGN KEY (SNO) REFERENCES Subscribe (sNo) ON DELETE CASCADE
);

-- 시퀀스
-- 보드 댓글 번호
CREATE SEQUENCE BREPLY_SEQ MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1
START WITH 1 NOCACHE;

-- 보드 게시글 번호
CREATE SEQUENCE BOARD_SEQ MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1
START WITH 1 NOCACHE;

-- 회원 번호
CREATE SEQUENCE MNO_SEQ MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1
START WITH 1 NOCACHE;

-- 포인트 번호
CREATE SEQUENCE PNO_SEQ MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1
START WITH 1 NOCACHE;

-- 구독 댓글 번호
CREATE SEQUENCE RNO_SEQ MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1
START WITH 1 NOCACHE;

-- 구독 첨부파일 번호
CREATE SEQUENCE ANO_SEQ MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1
START WITH 1 NOCACHE;

-- 구독 게시글 번호
CREATE SEQUENCE SNO_SEQ MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1
START WITH 1 NOCACHE;

-- 기본 쿼리
-- PasswordEncryptor.java 실행해서 터미널에 프린트 된 비밀번호를 UPW의 VALUE 값에다 삽입 후 전체 실행
INSERT INTO
    MEMBER (
        MNO,
        UUID,
        UPW,
        PHONE,
        NAME,
        MTYPE,
        EMAIL,
        REGDATE
    )
VALUES (
        0,
        'admin',
        '$2a$10$Qa1F9kP3SNJy0sk8JruEqeq2BXU1KO.Ej/b73oCft.5.chE4cIW0O',
        '01012345678',
        '운영자',
        'a',
        'admin@example.com',
        SYSDATE
    );
-- 초기 포인트 등록
INSERT INTO
    POINT (
        PNO,
        MNO,
        PCOUNT,
        PSOURCE,
        PDATE
    )
VALUES (
        PNO_SEQ.NEXTVAL,
        MNO_SEQ.CURRVAL,
        '0',
        '회원가입',
        SYSDATE
    );

-- 챌린지 게시판
INSERT INTO
    BOARD (
        bno,
        mno,
        title,
        bcontents,
        wdate,
        bcounts
    )
VALUES (
        BOARD_SEQ.NEXTVAL,
        1,
        '제목입니다',
        '내용입니다',
        SYSDATE,
        0
    );

commit;