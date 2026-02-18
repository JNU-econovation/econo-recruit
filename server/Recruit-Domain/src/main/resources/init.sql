INSERT INTO board (board_id, created_at, updated_at, card_id,card_type,column_id,navigation_id,next_board_id) VALUES (1,NOW(),NOW(),null,"INVISIBLE",1,1,null);
INSERT INTO board (board_id, created_at, updated_at, card_id,card_type,column_id,navigation_id,next_board_id) VALUES (2,NOW(),NOW(),null,"INVISIBLE",2,1,null);
INSERT INTO board (board_id, created_at, updated_at, card_id,card_type,column_id,navigation_id,next_board_id) VALUES (3,NOW(),NOW(),null,"INVISIBLE",3,1,null);
INSERT INTO navigation (navigation_id, created_at, updated_at, title) VALUES (1,NOW(),NOW(),"공통");
INSERT INTO navigation (navigation_id, created_at, updated_at, title) VALUES (2,NOW(),NOW(),"회장단");
INSERT INTO navigation (navigation_id, created_at, updated_at, title) VALUES (3,NOW(),NOW(),"운영팀");
INSERT INTO navigation (navigation_id, created_at, updated_at, title) VALUES (4,NOW(),NOW(),"홍보 및 디자인팀");
INSERT INTO navigation (navigation_id, created_at, updated_at, title) VALUES (5,NOW(),NOW(),"지원자 대응팀");
INSERT INTO navigation (navigation_id, created_at, updated_at, title) VALUES (6,NOW(),NOW(),"OT 담당팀");
INSERT INTO navigation (navigation_id, created_at, updated_at, title) VALUES (7,NOW(),NOW(),"기타 참고");

-- 31기 ~ 40기 공통 컬럼(개발자/디자이너/기획자) 초기화
-- navigation_id = 1(공통) 기준으로 year별 컬럼을 생성합니다.
INSERT INTO columns (created_at, updated_at, navigation_id, title, year)
SELECT NOW(), NOW(), 1, t.title, y.year
FROM (
    SELECT 31 AS year
    UNION ALL SELECT 32
    UNION ALL SELECT 33
    UNION ALL SELECT 34
    UNION ALL SELECT 35
    UNION ALL SELECT 36
    UNION ALL SELECT 37
    UNION ALL SELECT 38
    UNION ALL SELECT 39
    UNION ALL SELECT 40
) y
CROSS JOIN (
    SELECT "개발자" AS title
    UNION ALL SELECT "디자이너"
    UNION ALL SELECT "기획자"
) t
WHERE NOT EXISTS (
    SELECT 1
    FROM columns c
    WHERE c.navigation_id = 1
      AND c.year = y.year
      AND c.title = t.title
);

-- year별 공통 컬럼의 연결 순서를 개발자 -> 디자이너 -> 기획자 로 맞춥니다.
UPDATE columns c
SET c.next_columns_id = CASE
    WHEN c.title = "개발자" THEN (
        SELECT c2.columns_id
        FROM columns c2
        WHERE c2.navigation_id = 1
          AND c2.year = c.year
          AND c2.title = "디자이너"
        LIMIT 1
    )
    WHEN c.title = "디자이너" THEN (
        SELECT c3.columns_id
        FROM columns c3
        WHERE c3.navigation_id = 1
          AND c3.year = c.year
          AND c3.title = "기획자"
        LIMIT 1
    )
    ELSE NULL
END
WHERE c.navigation_id = 1
  AND c.year BETWEEN 31 AND 40
  AND c.title IN ("개발자", "디자이너", "기획자");
