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
UPDATE columns developer
LEFT JOIN columns designer
       ON designer.navigation_id = developer.navigation_id
      AND designer.year = developer.year
      AND designer.title = "디자이너"
SET developer.next_columns_id = designer.columns_id
WHERE developer.navigation_id = 1
  AND developer.year BETWEEN 31 AND 40
  AND developer.title = "개발자";

UPDATE columns designer
LEFT JOIN columns pm
       ON pm.navigation_id = designer.navigation_id
      AND pm.year = designer.year
      AND pm.title = "기획자"
SET designer.next_columns_id = pm.columns_id
WHERE designer.navigation_id = 1
  AND designer.year BETWEEN 31 AND 40
  AND designer.title = "디자이너";

UPDATE columns pm
SET pm.next_columns_id = NULL
WHERE pm.navigation_id = 1
  AND pm.year BETWEEN 31 AND 40
  AND pm.title = "기획자";
