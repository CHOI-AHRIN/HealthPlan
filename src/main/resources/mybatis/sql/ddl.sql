-- Active: 1728281287544@@127.0.0.1@1521@XE@HEALTH
-- 운영자 ID : admin / p.w : admin1234!

-- 회원 테이블
CREATE TABLE Member (
    mNo NUMBER NOT NULL, -- 회원번호
    uuid VARCHAR2(30), -- 아이디
    upw VARCHAR2(1000), -- 비밀번호
    phone VARCHAR2(100), -- 전화번호
    name VARCHAR2(20), -- 이름
    mType CHAR(1), -- 회원타입
    email VARCHAR2(100), -- 이메일
    regdate DATE, -- 가입일
    ssType CHAR(1), -- 구독타입(S/A/B/C/N)
    PRIMARY KEY (mNo)
);

-- 보드(챌린지) 테이블
CREATE TABLE Board (
    bNo NUMBER NOT NULL, -- 보드번호
    mNo NUMBER, -- 회원번호
    bType CHAR(1), -- 게시물종류
    title VARCHAR2(100), -- 글제목
    bContents VARCHAR2(2000), -- 글내용
    wDate DATE, -- 작성일자
    bCounts NUMBER, -- 조회수
    Replycnt NUMBER, -- 댓글수
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
    uuid VARCHAR2(30), -- 아이디
    mNo NUMBER NOT NULL, -- 회원번호
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
    uRegdate DATE, -- 댓글수정일
    PRIMARY KEY (rNo),
    FOREIGN KEY (sNo) REFERENCES Subscribe (sNo) ON DELETE CASCADE,
    FOREIGN KEY (mNo) REFERENCES Member (mNo) ON DELETE CASCADE
);

-- 포인트 테이블
CREATE TABLE Point (
    pNo NUMBER NOT NULL, -- 포인트번호
    mNo NUMBER, -- 회원번호
    pSource VARCHAR2(30), -- 포인트출처
    pcount NUMBER, -- 포인트량
    pDate DATE, -- 포인트생성일
    PRIMARY KEY (pNo),
    FOREIGN KEY (mNo) REFERENCES Member (mNo) ON DELETE CASCADE
);

-- 첨부 파일 테이블
CREATE TABLE NATTACH (
    UUID VARCHAR2(200),
    IMGNAME VARCHAR2(200),
    SNO NUMBER,
    BNO NUMBER,
    ANO NUMBER,
    PATH VARCHAR2(200),
    IMAGEURL VARCHAR2(200),
    IMGTYPE CHAR(1 BYTE),
    REGDATE DATE,
    FOREIGN KEY (SNO) REFERENCES Subscribe (SNO) ON DELETE CASCADE
    FOREIGN KEY (BNO) REFERENCES BOARD (BNO) ON DELETE CASCADE
);

-- <<시퀀스>>
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

-- <<기본 쿼리>>
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
        0,
        '0',
        '회원가입',
        SYSDATE
    );

-- 챌린지 게시판
INSERT INTO BOARD (
    BNO,
    MNO,
    title,
    bcontents,
    wdate,
    bcounts,
    replycnt
) VALUES (
    BOARD_SEQ.NEXTVAL,
    0,
    '하루 만 보 걷기',
    '목표: 하루에 10,000보 이상 걷기\n효과: 심혈관 건강 증진, 칼로리 소모',
    SYSDATE,
    0,
    0
);

INSERT INTO BOARD (
    BNO,
    MNO,
    title,
    bcontents,
    wdate,
    bcounts,
    replycnt
) VALUES (
    BOARD_SEQ.NEXTVAL,
    0,
    '스쿼트 100회 도전',
    '목표: 하루 동안 스쿼트 100회 수행\n효과: 하체 근력 강화, 코어 안정성 향상',
    SYSDATE,
    0,
    0
);

INSERT INTO BOARD (
    BNO,
    MNO,
    title,
    bcontents,
    wdate,
    bcounts,
    replycnt
) VALUES (
    BOARD_SEQ.NEXTVAL,
    0,
    '팔굽혀펴기 50회 챌린지',
    '목표: 하루에 팔굽혀펴기 50회 완료\n효과: 상체 근력 강화, 체력 향상',
    SYSDATE,
    0,
    0
);

INSERT INTO BOARD (
    BNO,
    MNO,
    title,
    bcontents,
    wdate,
    bcounts,
    replycnt
) VALUES (
    BOARD_SEQ.NEXTVAL,
    0,
    '플랭크 5분 유지하기',
    '목표: 총 5분 동안 플랭크 자세 유지 (나누어서 진행 가능)\n효과: 코어 근육 강화, 자세 개선',
    SYSDATE,
    0,
    0
);

INSERT INTO BOARD (
    BNO,
    MNO,
    title,
    bcontents,
    wdate,
    bcounts,
    replycnt
) VALUES (
    BOARD_SEQ.NEXTVAL,
    0,
    '러닝 또는 조깅 3km 완주',
    '목표: 하루에 3km 러닝 또는 조깅하기\n효과: 심폐 지구력 향상, 스트레스 해소',
    SYSDATE,
    0,
    0
);

INSERT INTO BOARD (
    BNO,
    MNO,
    title,
    bcontents,
    wdate,
    bcounts,
    replycnt
) VALUES (
    BOARD_SEQ.NEXTVAL,
    0,
    '버피 테스트 50회',
    '목표: 하루에 버피 50회 수행\n효과: 전신 근력 강화, 심박수 증가',
    SYSDATE,
    0,
    0
);

INSERT INTO BOARD (
    BNO,
    MNO,
    title,
    bcontents,
    wdate,
    bcounts,
    replycnt
) VALUES (
    BOARD_SEQ.NEXTVAL,
    0,
    '줄넘기 1,000회',
    '목표: 하루에 줄넘기 1,000회 완료\n효과: 심폐 지구력 향상, 하체 근력 강화',
    SYSDATE,
    0,
    0
);

INSERT INTO BOARD (
    BNO,
    MNO,
    title,
    bcontents,
    wdate,
    bcounts,
    replycnt
) VALUES (
    BOARD_SEQ.NEXTVAL,
    0,
    '자전거 타기 10km 도전',
    '목표: 하루에 10km 자전거 타기\n효과: 하체 근력 강화, 심폐 지구력 향상, 칼로리 소모',
    SYSDATE,
    0,
    0
);

INSERT INTO BOARD (
    BNO,
    MNO,
    title,
    bcontents,
    wdate,
    bcounts,
    replycnt
) VALUES (
    BOARD_SEQ.NEXTVAL,
    0,
    '1분 동안 플랭크 유지',
    '목표: 하루에 총 1분 동안 플랭크 자세 유지\n효과: 코어 근력 강화, 자세 개선, 전신 안정성 향상',
    SYSDATE,
    0,
    0
);

INSERT INTO BOARD (
    BNO,
    MNO,
    title,
    bcontents,
    wdate,
    bcounts,
    replycnt
) VALUES (
    BOARD_SEQ.NEXTVAL,
    0,
    '계단 오르기 30층 도전',
    '목표: 하루 동안 계단 30층 오르기\n효과: 하체 근력 및 심폐 지구력 향상, 체력 증진',
    SYSDATE,
    0,
    0
);

INSERT INTO BOARD (
    BNO,
    MNO,
    title,
    bcontents,
    wdate,
    bcounts,
    replycnt
) VALUES (
    BOARD_SEQ.NEXTVAL,
    0,
    '크런치 200회 챌린지',
    '목표: 하루 동안 크런치 200회 수행\n효과: 복근 강화, 코어 안정성 증대, 자세 개선',
    SYSDATE,
    0,
    0
);

INSERT INTO BOARD (
    BNO,
    MNO,
    title,
    bcontents,
    wdate,
    bcounts,
    replycnt
) VALUES (
    BOARD_SEQ.NEXTVAL,
    0,
    '요가 30분 수련',
    '목표: 하루에 30분 동안 요가 수련하기\n효과: 유연성 향상, 정신적 안정, 근육 이완 및 스트레스 해소',
    SYSDATE,
    0,
    0
);

INSERT INTO BOARD (
    BNO,
    MNO,
    title,
    bcontents,
    wdate,
    bcounts,
    replycnt
) VALUES (
    BOARD_SEQ.NEXTVAL,
    0,
    '1시간 스트레칭 챌린지',
    '목표: 하루에 1시간 동안 전신 스트레칭\n효과: 유연성 향상, 근육 긴장 완화, 부상 예방',
    SYSDATE,
    0,
    0
);

INSERT INTO BOARD (
    BNO,
    MNO,
    title,
    bcontents,
    wdate,
    bcounts,
    replycnt
) VALUES (
    BOARD_SEQ.NEXTVAL,
    0,
    '등산 5km 도전',
    '목표: 하루에 5km 등산하기\n효과: 하체 근력 강화, 심폐 지구력 향상, 자연 속에서 스트레스 해소',
    SYSDATE,
    0,
    0
);

INSERT INTO BOARD (
    BNO,
    MNO,
    title,
    bcontents,
    wdate,
    bcounts,
    replycnt
) VALUES (
    BOARD_SEQ.NEXTVAL,
    0,
    '덤벨 5kg로 100회 리프팅',
    '목표: 하루에 5kg 덤벨을 이용해 100회 리프팅\n효과: 상체 근력 강화, 체력 증진, 근육 톤 업',
    SYSDATE,
    0,
    0
);

commit;
