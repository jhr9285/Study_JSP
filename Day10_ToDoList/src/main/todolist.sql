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
    seq_todolist.NEXTVAL, 'MVC복습', '진행중', sysdate
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