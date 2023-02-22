CREATE TABLE todolist(
    no NUMBER PRIMARY KEY,
    content VARCHAR(200) NOT NULL,
    state VARCHAR(100) NOT NULL,
    wdate DATE DEFAULT sysdate
);

CREATE SEQUENCE seq_todolist;

INSERT INTO 
    todolist 
VALUES(
    seq_todolist.NEXTVAL, '네이버 레시피 페이지 만들기', '완료', '2022-12-12'
);
INSERT INTO 
    todolist 
VALUES(
    seq_todolist.NEXTVAL, '네이버 회원가입 페이지 만들기', '완료', '2022-12-15'
);
INSERT INTO 
    todolist 
VALUES(
    seq_todolist.NEXTVAL, 'Smart Join 회원가입 페이지 만들기', '완료', '2023-01-06'
);
INSERT INTO 
    todolist 
VALUES(
    seq_todolist.NEXTVAL, 'Oracle 이용하여 SQL 평가 문제 풀기', '완료', '2023-01-12'
);
INSERT INTO 
    todolist 
VALUES(
    seq_todolist.NEXTVAL, '인터페이스 구현 평가 문제 풀기', '완료', '2023-02-06'
);
INSERT INTO 
    todolist 
VALUES(
    seq_todolist.NEXTVAL, '통합구현 평가 문제 풀기', '완료', '2023-02-15'
);
INSERT INTO 
    todolist 
VALUES(
    seq_todolist.NEXTVAL, '펌웨어 구현 평가 문제 풀기', '완료', sysdate
);
INSERT INTO 
    todolist 
VALUES(
    seq_todolist.NEXTVAL, 'Swing 이용하여 학사관리 프로그램 만들기', '완료', '2023-02-20'
);
INSERT INTO 
    todolist 
VALUES(
    seq_todolist.NEXTVAL, 'JSP 이용하여 전화번호부 만들기', '준비중', '2023-02-20'
);


SELECT
    *
FROM
    todolist
;

commit;

-- 페이징 처리 쿼리문
SELECT
    *
FROM
    (
        SELECT
            tb.*, rownum rNum
        FROM
            (
                SELECT
                    *
                FROM
                    todolist
                WHERE
                    content LIKE '%MVC%'
                ORDER BY
                    no DESC
            ) tb
    )
WHERE
    rNum BETWEEN 1 AND 10  -- 페이징처리는 rounum으로. (고정된 인덱스 생성)
;

-- 검색 결과 카운팅 쿼리문
SELECT
    COUNT(*)
FROM
    todolist
WHERE
    content LIKE '%MVC%'
;

-- 데이터 2배씩 증가
INSERT INTO
    todolist
SELECT
    seq_todolist.NEXTVAL, content, state, wdate
FROM
    todolist
;
