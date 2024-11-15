-- Active: 1728281287544@@127.0.0.1@1521@XE@HEALTH
-- 운영자 ID : admin / PW : admin1234!
-- (1)전문가1 ID : teacher1 / PW : qwerqwer1!
-- (2)전문가2 ID : teacher2 / PW : qwerqwer1!
-- (3)전문가3 ID : teacher3 / PW : qwerqwer1!
-- (4)사용자001 ID : user001 / PW : qwerqwer1!
-- (5)사용자002 ID : user002 / PW : qwerqwer1!
-- (6)사용자003 ID : user003 / PW : qwerqwer1!
-- (7)사용자004 ID : user004 / PW : qwerqwer1!
-- (8)사용자005 ID : user005 / PW : qwerqwer1!


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
    FOREIGN KEY (SNO) REFERENCES Subscribe (SNO) ON DELETE CASCADE,
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

-- <회원등록>
-- 운영자
INSERT INTO
    MEMBER (
        MNO,
        UUID,
        UPW,
        PHONE,
        NAME,
        MTYPE,
        SSTYPE,
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
        'S',
        'admin@example.com',
        TO_DATE('2024-11-01', 'YYYY-MM-DD')
    );

-- 전문가
-- 전문가1 mno > 1
INSERT INTO
    MEMBER (
        MNO,
        UUID,
        UPW,
        PHONE,
        NAME,
        MTYPE,
        SSTYPE,
        EMAIL,
        REGDATE
    )
VALUES (
        MNO_SEQ.NEXTVAL,
        'teacher1',
        '$2a$10$LghDEbloMx7njgBlIq6LlOf7z9LCOkt9Ndpyz2YVFTeIydJ7k8ssC',
        '01012345678',
        '전문가1',
        't',
        'N',
        'teacher1@naver.com',
        TO_DATE('2024-11-01', 'YYYY-MM-DD')
    );

-- 전문가2 mno > 2
INSERT INTO
    MEMBER (
        MNO,
        UUID,
        UPW,
        PHONE,
        NAME,
        MTYPE,
        SSTYPE,
        EMAIL,
        REGDATE
    )
VALUES (
        MNO_SEQ.NEXTVAL,
        'teacher2',
        '$2a$10$LghDEbloMx7njgBlIq6LlOf7z9LCOkt9Ndpyz2YVFTeIydJ7k8ssC',
        '01012345678',
        '전문가2',
        't',
        'N',
        'teacher2@naver.com',
        TO_DATE('2024-11-01', 'YYYY-MM-DD')
    );

-- 전문가3 mno > 3
INSERT INTO
    MEMBER (
        MNO,
        UUID,
        UPW,
        PHONE,
        NAME,
        MTYPE,
        SSTYPE,
        EMAIL,
        REGDATE
    )
VALUES (
        MNO_SEQ.NEXTVAL,
        'teacher3',
        '$2a$10$LghDEbloMx7njgBlIq6LlOf7z9LCOkt9Ndpyz2YVFTeIydJ7k8ssC',
        '01012345678',
        '전문가3',
        't',
        'N',
        'teacher3@naver.com',
        TO_DATE('2024-11-01', 'YYYY-MM-DD')
    );

-- 일반회원
-- 사용자001 mno > 4
INSERT INTO
    MEMBER (
        MNO,
        UUID,
        UPW,
        PHONE,
        NAME,
        MTYPE,
        SSTYPE,
        EMAIL,
        REGDATE
    )
VALUES (
        MNO_SEQ.NEXTVAL,
        'user001',
        '$2a$10$LghDEbloMx7njgBlIq6LlOf7z9LCOkt9Ndpyz2YVFTeIydJ7k8ssC',
        '01012345678',
        '사용자001',
        'm',
        'N',
        'user001@naver.com',
        TO_DATE('2024-11-01', 'YYYY-MM-DD')
    );

-- 사용자002 mno > 5
INSERT INTO
    MEMBER (
        MNO,
        UUID,
        UPW,
        PHONE,
        NAME,
        MTYPE,
        SSTYPE,
        EMAIL,
        REGDATE
    )
VALUES (
        MNO_SEQ.NEXTVAL,
        'user002',
        '$2a$10$LghDEbloMx7njgBlIq6LlOf7z9LCOkt9Ndpyz2YVFTeIydJ7k8ssC',
        '01012345678',
        '사용자002',
        'm',
        'N',
        'user002@naver.com',
        TO_DATE('2024-11-01', 'YYYY-MM-DD')
    );

-- 사용자003 mno > 6
INSERT INTO
    MEMBER (
        MNO,
        UUID,
        UPW,
        PHONE,
        NAME,
        MTYPE,
        SSTYPE,
        EMAIL,
        REGDATE
    )
VALUES (
        MNO_SEQ.NEXTVAL,
        'user003',
        '$2a$10$LghDEbloMx7njgBlIq6LlOf7z9LCOkt9Ndpyz2YVFTeIydJ7k8ssC',
        '01012345678',
        '사용자003',
        'm',
        'N',
        'user003@naver.com',
        TO_DATE('2024-11-01', 'YYYY-MM-DD')
    );

-- 사용자004 mno > 7
INSERT INTO
    MEMBER (
        MNO,
        UUID,
        UPW,
        PHONE,
        NAME,
        MTYPE,
        SSTYPE,
        EMAIL,
        REGDATE
    )
VALUES (
        MNO_SEQ.NEXTVAL,
        'user004',
        '$2a$10$LghDEbloMx7njgBlIq6LlOf7z9LCOkt9Ndpyz2YVFTeIydJ7k8ssC',
        '01012345678',
        '사용자004',
        'm',
        'N',
        'user004@naver.com',
        TO_DATE('2024-11-01', 'YYYY-MM-DD')
    );

-- 사용자005 mno > 8
INSERT INTO
    MEMBER (
        MNO,
        UUID,
        UPW,
        PHONE,
        NAME,
        MTYPE,
        SSTYPE,
        EMAIL,
        REGDATE
    )
VALUES (
        MNO_SEQ.NEXTVAL,
        'user005',
        '$2a$10$LghDEbloMx7njgBlIq6LlOf7z9LCOkt9Ndpyz2YVFTeIydJ7k8ssC',
        '01012345678',
        '사용자005',
        'm',
        'N',
        'user005@naver.com',
        TO_DATE('2024-11-01', 'YYYY-MM-DD')
    );

-- 초기 포인트 등록
-- 운영자
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
        TO_DATE('2024-11-01', 'YYYY-MM-DD')
    );

-- 전문가1
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
        1,
        '0',
        '회원가입',
        TO_DATE('2024-11-01', 'YYYY-MM-DD')
    );

-- 전문가2
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
        2,
        '0',
        '회원가입',
        TO_DATE('2024-11-01', 'YYYY-MM-DD')
    );

-- 전문가3
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
        3,
        '0',
        '회원가입',
        TO_DATE('2024-11-01', 'YYYY-MM-DD')
    );

-- 사용자001
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
        4,
        '0',
        '회원가입',
        TO_DATE('2024-11-01', 'YYYY-MM-DD')
    );

-- 사용자002
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
        5,
        '0',
        '회원가입',
        TO_DATE('2024-11-01', 'YYYY-MM-DD')
    );

-- 사용자003
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
        6,
        '0',
        '회원가입',
        TO_DATE('2024-11-01', 'YYYY-MM-DD')
    );

-- 사용자004
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
        7,
        '0',
        '회원가입',
        TO_DATE('2024-11-01', 'YYYY-MM-DD')
    );

-- 사용자005
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
        8,
        '0',
        '회원가입',
        TO_DATE('2024-11-01', 'YYYY-MM-DD')
    );

-- 챌린지 게시판
-- 2024-11-01
INSERT INTO
    BOARD (
        BNO,
        MNO,
        title,
        bcontents,
        wdate,
        bcounts,
        replycnt
    )
VALUES (
        BOARD_SEQ.NEXTVAL,
        0,
        '하루 만 보 걷기',
        '목표: 하루에 10,000보 이상 걷기
효과: 심혈관 건강 증진, 칼로리 소모',
        TO_DATE('2024-11-01', 'YYYY-MM-DD'),
        10,
        0
    );

INSERT INTO
    BOARD (
        BNO,
        MNO,
        title,
        bcontents,
        wdate,
        bcounts,
        replycnt
    )
VALUES (
        BOARD_SEQ.NEXTVAL,
        0,
        '스쿼트 100회 도전',
        '목표: 하루 동안 스쿼트 100회 수행
효과: 하체 근력 강화, 코어 안정성 향상',
        TO_DATE('2024-11-01', 'YYYY-MM-DD'),
        8,
        0
    );

INSERT INTO
    BOARD (
        BNO,
        MNO,
        title,
        bcontents,
        wdate,
        bcounts,
        replycnt
    )
VALUES (
        BOARD_SEQ.NEXTVAL,
        0,
        '팔굽혀펴기 50회 챌린지',
        '목표: 하루에 팔굽혀펴기 50회 완료
효과: 상체 근력 강화, 체력 향상',
        TO_DATE('2024-11-01', 'YYYY-MM-DD'),
        4,
        0
    );

-- 2024-11-02
INSERT INTO
    BOARD (
        BNO,
        MNO,
        title,
        bcontents,
        wdate,
        bcounts,
        replycnt
    )
VALUES (
        BOARD_SEQ.NEXTVAL,
        0,
        '플랭크 5분 유지하기',
        '목표: 총 5분 동안 플랭크 자세 유지 (나누어서 진행 가능)
효과: 코어 근육 강화, 자세 개선',
        TO_DATE('2024-11-02', 'YYYY-MM-DD'),
        50,
        0
    );

INSERT INTO
    BOARD (
        BNO,
        MNO,
        title,
        bcontents,
        wdate,
        bcounts,
        replycnt
    )
VALUES (
        BOARD_SEQ.NEXTVAL,
        0,
        '러닝 또는 조깅 3km 완주',
        '목표: 하루에 3km 러닝 또는 조깅하기
효과: 심폐 지구력 향상, 스트레스 해소',
        TO_DATE('2024-11-02', 'YYYY-MM-DD'),
        77,
        0
    );

INSERT INTO
    BOARD (
        BNO,
        MNO,
        title,
        bcontents,
        wdate,
        bcounts,
        replycnt
    )
VALUES (
        BOARD_SEQ.NEXTVAL,
        0,
        '버피 테스트 50회',
        '목표: 하루에 버피 50회 수행
효과: 전신 근력 강화, 심박수 증가',
        TO_DATE('2024-11-02', 'YYYY-MM-DD'),
        14,
        0
    );

-- 2024-11-03
INSERT INTO
    BOARD (
        BNO,
        MNO,
        title,
        bcontents,
        wdate,
        bcounts,
        replycnt
    )
VALUES (
        BOARD_SEQ.NEXTVAL,
        0,
        '줄넘기 1,000회',
        '목표: 하루에 줄넘기 1,000회 완료
효과: 심폐 지구력 향상, 하체 근력 강화',
        TO_DATE('2024-11-03', 'YYYY-MM-DD'),
        33,
        0
    );

INSERT INTO
    BOARD (
        BNO,
        MNO,
        title,
        bcontents,
        wdate,
        bcounts,
        replycnt
    )
VALUES (
        BOARD_SEQ.NEXTVAL,
        0,
        '자전거 타기 10km 도전',
        '목표: 하루에 10km 자전거 타기
효과: 하체 근력 강화, 심폐 지구력 향상, 칼로리 소모',
        TO_DATE('2024-11-03', 'YYYY-MM-DD'),
        9,
        0
    );

INSERT INTO
    BOARD (
        BNO,
        MNO,
        title,
        bcontents,
        wdate,
        bcounts,
        replycnt
    )
VALUES (
        BOARD_SEQ.NEXTVAL,
        0,
        '1분 동안 플랭크 유지',
        '목표: 하루에 총 1분 동안 플랭크 자세 유지
효과: 코어 근력 강화, 자세 개선, 전신 안정성 향상',
        TO_DATE('2024-11-03', 'YYYY-MM-DD'),
        44,
        0
    );

-- 2024-11-04
INSERT INTO
    BOARD (
        BNO,
        MNO,
        title,
        bcontents,
        wdate,
        bcounts,
        replycnt
    )
VALUES (
        BOARD_SEQ.NEXTVAL,
        0,
        '계단 오르기 30층 도전',
        '목표: 하루 동안 계단 30층 오르기
효과: 하체 근력 및 심폐 지구력 향상, 체력 증진',
        TO_DATE('2024-11-04', 'YYYY-MM-DD'),
        12,
        0
    );

INSERT INTO
    BOARD (
        BNO,
        MNO,
        title,
        bcontents,
        wdate,
        bcounts,
        replycnt
    )
VALUES (
        BOARD_SEQ.NEXTVAL,
        0,
        '크런치 200회 챌린지',
        '목표: 하루 동안 크런치 200회 수행
효과: 복근 강화, 코어 안정성 증대, 자세 개선',
        TO_DATE('2024-11-04', 'YYYY-MM-DD'),
        20,
        0
    );

INSERT INTO
    BOARD (
        BNO,
        MNO,
        title,
        bcontents,
        wdate,
        bcounts,
        replycnt
    )
VALUES (
        BOARD_SEQ.NEXTVAL,
        0,
        '요가 30분 수련',
        '목표: 하루에 30분 동안 요가 수련하기
효과: 유연성 향상, 정신적 안정, 근육 이완 및 스트레스 해소',
        TO_DATE('2024-11-04', 'YYYY-MM-DD'),
        6,
        0
    );

-- 2024-11-05
INSERT INTO
    BOARD (
        BNO,
        MNO,
        title,
        bcontents,
        wdate,
        bcounts,
        replycnt
    )
VALUES (
        BOARD_SEQ.NEXTVAL,
        0,
        '1시간 스트레칭 챌린지',
        '목표: 하루에 1시간 동안 전신 스트레칭
효과: 유연성 향상, 근육 긴장 완화, 부상 예방',
        TO_DATE('2024-11-05', 'YYYY-MM-DD'),
        8,
        0
    );

INSERT INTO
    BOARD (
        BNO,
        MNO,
        title,
        bcontents,
        wdate,
        bcounts,
        replycnt
    )
VALUES (
        BOARD_SEQ.NEXTVAL,
        0,
        '등산 5km 도전',
        '목표: 하루에 5km 등산하기
효과: 하체 근력 강화, 심폐 지구력 향상, 자연 속에서 스트레스 해소',
        TO_DATE('2024-11-05', 'YYYY-MM-DD'),
        17,
        0
    );

INSERT INTO
    BOARD (
        BNO,
        MNO,
        title,
        bcontents,
        wdate,
        bcounts,
        replycnt
    )
VALUES (
        BOARD_SEQ.NEXTVAL,
        0,
        '덤벨 5kg로 100회 리프팅',
        '목표: 하루에 5kg 덤벨을 이용해 100회 리프팅
효과: 상체 근력 강화, 체력 증진, 근육 톤 업',
        TO_DATE('2024-11-05', 'YYYY-MM-DD'),
        0,
        0
    );

-- 챌린지 게시글 댓글 (랭킹)
-- user001(4) --> 6
INSERT INTO
    BREPLY (
        rno,
        bno,
        mno,
        rcomment,
        rregdate
    )
VALUES (
        BREPLY_SEQ.NEXTVAL,
        3,
        4,
        '인증합니다!!',
        CURRENT_TIMESTAMP AT TIME ZONE 'Asia/Seoul'
    );

INSERT INTO
    BREPLY (
        rno,
        bno,
        mno,
        rcomment,
        rregdate
    )
VALUES (
        BREPLY_SEQ.NEXTVAL,
        15,
        4,
        '인증합니다!!',
        CURRENT_TIMESTAMP AT TIME ZONE 'Asia/Seoul'
    );

INSERT INTO
    BREPLY (
        rno,
        bno,
        mno,
        rcomment,
        rregdate
    )
VALUES (
        BREPLY_SEQ.NEXTVAL,
        10,
        4,
        '인증합니다!!',
        CURRENT_TIMESTAMP AT TIME ZONE 'Asia/Seoul'
    );

INSERT INTO
    BREPLY (
        rno,
        bno,
        mno,
        rcomment,
        rregdate
    )
VALUES (
        BREPLY_SEQ.NEXTVAL,
        14,
        4,
        '인증합니다~',
        CURRENT_TIMESTAMP AT TIME ZONE 'Asia/Seoul'
    );

INSERT INTO
    BREPLY (
        rno,
        bno,
        mno,
        rcomment,
        rregdate
    )
VALUES (
        BREPLY_SEQ.NEXTVAL,
        13,
        4,
        '달려봅시다~',
        CURRENT_TIMESTAMP AT TIME ZONE 'Asia/Seoul'
    );

INSERT INTO
    BREPLY (
        rno,
        bno,
        mno,
        rcomment,
        rregdate
    )
VALUES (
        BREPLY_SEQ.NEXTVAL,
        7,
        4,
        '인증해요',
        CURRENT_TIMESTAMP AT TIME ZONE 'Asia/Seoul'
    );

-- user002(5) --> 4
INSERT INTO
    BREPLY (
        rno,
        bno,
        mno,
        rcomment,
        rregdate
    )
VALUES (
        BREPLY_SEQ.NEXTVAL,
        3,
        5,
        '인증합니다!!',
        CURRENT_TIMESTAMP AT TIME ZONE 'Asia/Seoul'
    );

INSERT INTO
    BREPLY (
        rno,
        bno,
        mno,
        rcomment,
        rregdate
    )
VALUES (
        BREPLY_SEQ.NEXTVAL,
        15,
        5,
        '인증합니다!!',
        CURRENT_TIMESTAMP AT TIME ZONE 'Asia/Seoul'
    );

INSERT INTO
    BREPLY (
        rno,
        bno,
        mno,
        rcomment,
        rregdate
    )
VALUES (
        BREPLY_SEQ.NEXTVAL,
        11,
        5,
        '인증합니다!!',
        CURRENT_TIMESTAMP AT TIME ZONE 'Asia/Seoul'
    );

INSERT INTO
    BREPLY (
        rno,
        bno,
        mno,
        rcomment,
        rregdate
    )
VALUES (
        BREPLY_SEQ.NEXTVAL,
        4,
        5,
        '인증합니다!!',
        CURRENT_TIMESTAMP AT TIME ZONE 'Asia/Seoul'
    );

-- user003(6) --> 9
INSERT INTO
    BREPLY (
        rno,
        bno,
        mno,
        rcomment,
        rregdate
    )
VALUES (
        BREPLY_SEQ.NEXTVAL,
        7,
        6,
        '인증해요',
        CURRENT_TIMESTAMP AT TIME ZONE 'Asia/Seoul'
    );

INSERT INTO
    BREPLY (
        rno,
        bno,
        mno,
        rcomment,
        rregdate
    )
VALUES (
        BREPLY_SEQ.NEXTVAL,
        14,
        6,
        '인증합니다~',
        CURRENT_TIMESTAMP AT TIME ZONE 'Asia/Seoul'
    );

INSERT INTO
    BREPLY (
        rno,
        bno,
        mno,
        rcomment,
        rregdate
    )
VALUES (
        BREPLY_SEQ.NEXTVAL,
        6,
        6,
        '완료~ 화이팅!',
        CURRENT_TIMESTAMP AT TIME ZONE 'Asia/Seoul'
    );

INSERT INTO
    BREPLY (
        rno,
        bno,
        mno,
        rcomment,
        rregdate
    )
VALUES (
        BREPLY_SEQ.NEXTVAL,
        13,
        6,
        '인증합니다~',
        CURRENT_TIMESTAMP AT TIME ZONE 'Asia/Seoul'
    );

INSERT INTO
    BREPLY (
        rno,
        bno,
        mno,
        rcomment,
        rregdate
    )
VALUES (
        BREPLY_SEQ.NEXTVAL,
        2,
        6,
        '눈물 쏙 뽑앗습니다 ..',
        CURRENT_TIMESTAMP AT TIME ZONE 'Asia/Seoul'
    );

INSERT INTO
    BREPLY (
        rno,
        bno,
        mno,
        rcomment,
        rregdate
    )
VALUES (
        BREPLY_SEQ.NEXTVAL,
        1,
        6,
        '인증합니다~',
        CURRENT_TIMESTAMP AT TIME ZONE 'Asia/Seoul'
    );

INSERT INTO
    BREPLY (
        rno,
        bno,
        mno,
        rcomment,
        rregdate
    )
VALUES (
        BREPLY_SEQ.NEXTVAL,
        3,
        6,
        '껌이죠 ~ ㅋ',
        CURRENT_TIMESTAMP AT TIME ZONE 'Asia/Seoul'
    );

INSERT INTO
    BREPLY (
        rno,
        bno,
        mno,
        rcomment,
        rregdate
    )
VALUES (
        BREPLY_SEQ.NEXTVAL,
        10,
        6,
        '인증합니다~',
        CURRENT_TIMESTAMP AT TIME ZONE 'Asia/Seoul'
    );

INSERT INTO
    BREPLY (
        rno,
        bno,
        mno,
        rcomment,
        rregdate
    )
VALUES (
        BREPLY_SEQ.NEXTVAL,
        12,
        6,
        '힘드네요 ㅠㅠ 인증합니다~~',
        CURRENT_TIMESTAMP AT TIME ZONE 'Asia/Seoul'
    );

-- user004(7) --> 2
INSERT INTO
    BREPLY (
        rno,
        bno,
        mno,
        rcomment,
        rregdate
    )
VALUES (
        BREPLY_SEQ.NEXTVAL,
        11,
        7,
        '아궁 힘들어~ ㅠ',
        CURRENT_TIMESTAMP AT TIME ZONE 'Asia/Seoul'
    );

INSERT INTO
    BREPLY (
        rno,
        bno,
        mno,
        rcomment,
        rregdate
    )
VALUES (
        BREPLY_SEQ.NEXTVAL,
        5,
        7,
        '쉬어가며 했습니다~ ㅠ',
        CURRENT_TIMESTAMP AT TIME ZONE 'Asia/Seoul'
    );
-- user005(8) --> 15
INSERT INTO
    BREPLY (
        rno,
        bno,
        mno,
        rcomment,
        rregdate
    )
VALUES (
        BREPLY_SEQ.NEXTVAL,
        1,
        8,
        '완료했습니다.',
        CURRENT_TIMESTAMP AT TIME ZONE 'Asia/Seoul'
    );

INSERT INTO
    BREPLY (
        rno,
        bno,
        mno,
        rcomment,
        rregdate
    )
VALUES (
        BREPLY_SEQ.NEXTVAL,
        2,
        8,
        '완료했습니다.',
        CURRENT_TIMESTAMP AT TIME ZONE 'Asia/Seoul'
    );

INSERT INTO
    BREPLY (
        rno,
        bno,
        mno,
        rcomment,
        rregdate
    )
VALUES (
        BREPLY_SEQ.NEXTVAL,
        3,
        8,
        '완료했습니다.',
        CURRENT_TIMESTAMP AT TIME ZONE 'Asia/Seoul'
    );

INSERT INTO
    BREPLY (
        rno,
        bno,
        mno,
        rcomment,
        rregdate
    )
VALUES (
        BREPLY_SEQ.NEXTVAL,
        4,
        8,
        '완료했습니다.',
        CURRENT_TIMESTAMP AT TIME ZONE 'Asia/Seoul'
    );

INSERT INTO
    BREPLY (
        rno,
        bno,
        mno,
        rcomment,
        rregdate
    )
VALUES (
        BREPLY_SEQ.NEXTVAL,
        5,
        8,
        '완료했습니다.',
        CURRENT_TIMESTAMP AT TIME ZONE 'Asia/Seoul'
    );

INSERT INTO
    BREPLY (
        rno,
        bno,
        mno,
        rcomment,
        rregdate
    )
VALUES (
        BREPLY_SEQ.NEXTVAL,
        6,
        8,
        '완료했습니다.',
        CURRENT_TIMESTAMP AT TIME ZONE 'Asia/Seoul'
    );

INSERT INTO
    BREPLY (
        rno,
        bno,
        mno,
        rcomment,
        rregdate
    )
VALUES (
        BREPLY_SEQ.NEXTVAL,
        7,
        8,
        '완료했습니다.',
        CURRENT_TIMESTAMP AT TIME ZONE 'Asia/Seoul'
    );

INSERT INTO
    BREPLY (
        rno,
        bno,
        mno,
        rcomment,
        rregdate
    )
VALUES (
        BREPLY_SEQ.NEXTVAL,
        8,
        8,
        '완료했습니다.',
        CURRENT_TIMESTAMP AT TIME ZONE 'Asia/Seoul'
    );

INSERT INTO
    BREPLY (
        rno,
        bno,
        mno,
        rcomment,
        rregdate
    )
VALUES (
        BREPLY_SEQ.NEXTVAL,
        9,
        8,
        '완료했습니다.',
        CURRENT_TIMESTAMP AT TIME ZONE 'Asia/Seoul'
    );

INSERT INTO
    BREPLY (
        rno,
        bno,
        mno,
        rcomment,
        rregdate
    )
VALUES (
        BREPLY_SEQ.NEXTVAL,
        10,
        8,
        '완료했습니다.',
        CURRENT_TIMESTAMP AT TIME ZONE 'Asia/Seoul'
    );

INSERT INTO
    BREPLY (
        rno,
        bno,
        mno,
        rcomment,
        rregdate
    )
VALUES (
        BREPLY_SEQ.NEXTVAL,
        11,
        8,
        '완료했습니다.',
        CURRENT_TIMESTAMP AT TIME ZONE 'Asia/Seoul'
    );

INSERT INTO
    BREPLY (
        rno,
        bno,
        mno,
        rcomment,
        rregdate
    )
VALUES (
        BREPLY_SEQ.NEXTVAL,
        12,
        8,
        '완료했습니다.',
        CURRENT_TIMESTAMP AT TIME ZONE 'Asia/Seoul'
    );

INSERT INTO
    BREPLY (
        rno,
        bno,
        mno,
        rcomment,
        rregdate
    )
VALUES (
        BREPLY_SEQ.NEXTVAL,
        13,
        8,
        '완료했습니다.',
        CURRENT_TIMESTAMP AT TIME ZONE 'Asia/Seoul'
    );

INSERT INTO
    BREPLY (
        rno,
        bno,
        mno,
        rcomment,
        rregdate
    )
VALUES (
        BREPLY_SEQ.NEXTVAL,
        14,
        8,
        '완료했습니다.',
        CURRENT_TIMESTAMP AT TIME ZONE 'Asia/Seoul'
    );

INSERT INTO
    BREPLY (
        rno,
        bno,
        mno,
        rcomment,
        rregdate
    )
VALUES (
        BREPLY_SEQ.NEXTVAL,
        15,
        8,
        '완료했습니다.',
        CURRENT_TIMESTAMP AT TIME ZONE 'Asia/Seoul'
    );

-- 구독 게시판 (전문가)
-- 전문가1
INSERT INTO
    SUBSCRIBE (
        SNO,
        MNO,
        UUID,
        STYPE,
        TITLE,
        CONTENTS,
        SPOINT,
        WDATE,
        COUNTS
    )
VALUES (
        SNO_SEQ.NEXTVAL,
        1,
        'teacher1',
        'T',
        '1:1 훈련이 필요하다면?',
        ' 전문가와 함께하는 1:1 맞춤형 운동 및 식단 코칭! 
개인 목표와 라이프스타일에 최적화된 솔루션을 제공하여 체계적인 관리와 함께 건강한 변화를 만들어갑니다.
당신만을 위한 코칭으로 최고의 결과를 경험하세요!',
        50000,
        TO_DATE('2024-11-04', 'YYYY-MM-DD'),
        7
    );

-- 전문가2
INSERT INTO
    SUBSCRIBE (
        SNO,
        MNO,
        UUID,
        STYPE,
        TITLE,
        CONTENTS,
        SPOINT,
        WDATE,
        COUNTS
    )
VALUES (
        SNO_SEQ.NEXTVAL,
        2,
        'teacher2',
        'T',
        '나만의 건강 코치가 필요하다면?',
        '1:1 맞춤형 운동과 식단 코칭으로 당신의 목표를 현실로 만드세요!
전문 코치의 세심한 관리와 피드백을 통해 효율적인 건강 관리와 지속 가능한 변화를 제공합니다.
지금 바로 나만의 코치를 만나보세요!',
        30000,
        TO_DATE('2024-11-01', 'YYYY-MM-DD'),
        33
    );

-- 전문가3
INSERT INTO
    SUBSCRIBE (
        SNO,
        MNO,
        UUID,
        STYPE,
        TITLE,
        CONTENTS,
        SPOINT,
        WDATE,
        COUNTS
    )
VALUES (
        SNO_SEQ.NEXTVAL,
        3,
        'teacher3',
        'T',
        '체계적인 건강 관리, 지금 시작하세요!',
        ' 전문가와 함께하는 1:1 운동 및 식단 코칭! 목표에 맞는 최적의 플랜으로 건강한 변화를 만들어갑니다.',
        40000,
        TO_DATE('2024-11-02', 'YYYY-MM-DD'),
        19
    );

-- 구독 게시판 (구독)
-- 전문가1
INSERT INTO
    SUBSCRIBE (
        SNO,
        MNO,
        UUID,
        STYPE,
        TITLE,
        CONTENTS,
        SPOINT,
        WDATE,
        COUNTS
    )
VALUES (
        SNO_SEQ.NEXTVAL,
        1,
        'teacher1',
        'L',
        '당신만의 운동 루틴을 완성하세요!',
        '체계적인 운동 계획과 함께 최상의 컨디션을 유지하세요. 맞춤형 강의를 통해 건강한 삶을 시작하세요!',
        150000,
        TO_DATE('2024-11-15', 'YYYY-MM-DD'),
        77
    );

-- 전문가2
INSERT INTO
    SUBSCRIBE (
        SNO,
        MNO,
        UUID,
        STYPE,
        TITLE,
        CONTENTS,
        SPOINT,
        WDATE,
        COUNTS
    )
VALUES (
        SNO_SEQ.NEXTVAL,
        2,
        'teacher2',
        'L',
        '지금 시작하는 건강한 변신',
        '운동 초보부터 전문가까지! 누구나 쉽게 따라 할 수 있는 강의로 체력을 키우고 목표를 달성하세요.',
        80000,
        TO_DATE('2024-11-07', 'YYYY-MM-DD'),
        87
    );

-- 전문가3
INSERT INTO
    SUBSCRIBE (
        SNO,
        MNO,
        UUID,
        STYPE,
        TITLE,
        CONTENTS,
        SPOINT,
        WDATE,
        COUNTS
    )
VALUES (
        SNO_SEQ.NEXTVAL,
        3,
        'teacher3',
        'L',
        '바쁜 일상 속 효과적인 운동법',
        ' 짧고 효율적인 운동 강의로 당신의 하루를 더 활기차게 만들어보세요.',
        70000,
        TO_DATE('2024-11-05', 'YYYY-MM-DD'),
        34
    );

-- 댓글 수 카운트
UPDATE BOARD SET
    REPLYCNT = (SELECT COUNT(*) FROM BREPLY WHERE BNO = 1)
WHERE BNO = 1;

UPDATE BOARD SET
    REPLYCNT = (SELECT COUNT(*) FROM BREPLY WHERE BNO = 2)
WHERE BNO = 2;

UPDATE BOARD SET
    REPLYCNT = (SELECT COUNT(*) FROM BREPLY WHERE BNO = 3)
WHERE BNO = 3;

UPDATE BOARD SET
    REPLYCNT = (SELECT COUNT(*) FROM BREPLY WHERE BNO = 4)
WHERE BNO = 4;

UPDATE BOARD SET
    REPLYCNT = (SELECT COUNT(*) FROM BREPLY WHERE BNO = 5)
WHERE BNO = 5;

UPDATE BOARD SET
    REPLYCNT = (SELECT COUNT(*) FROM BREPLY WHERE BNO = 6)
WHERE BNO = 6;

UPDATE BOARD SET
    REPLYCNT = (SELECT COUNT(*) FROM BREPLY WHERE BNO = 7)
WHERE BNO = 7;

UPDATE BOARD SET
    REPLYCNT = (SELECT COUNT(*) FROM BREPLY WHERE BNO = 8)
WHERE BNO = 8;

UPDATE BOARD SET
    REPLYCNT = (SELECT COUNT(*) FROM BREPLY WHERE BNO = 9)
WHERE BNO = 9;

UPDATE BOARD SET
    REPLYCNT = (SELECT COUNT(*) FROM BREPLY WHERE BNO = 10)
WHERE BNO = 10;

UPDATE BOARD SET
    REPLYCNT = (SELECT COUNT(*) FROM BREPLY WHERE BNO = 11)
WHERE BNO = 11;

UPDATE BOARD SET
    REPLYCNT = (SELECT COUNT(*) FROM BREPLY WHERE BNO = 12)
WHERE BNO = 12;

UPDATE BOARD SET
    REPLYCNT = (SELECT COUNT(*) FROM BREPLY WHERE BNO = 13)
WHERE BNO = 13;

UPDATE BOARD SET
    REPLYCNT = (SELECT COUNT(*) FROM BREPLY WHERE BNO = 14)
WHERE BNO = 14;

UPDATE BOARD SET
    REPLYCNT = (SELECT COUNT(*) FROM BREPLY WHERE BNO = 15)
WHERE BNO = 15;


commit;