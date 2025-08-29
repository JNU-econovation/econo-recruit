package com.econovation.recruit.columns;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.econovation.recruit.api.card.util.ColumnsUtil;
import com.econovation.recruitdomain.domains.board.domain.Columns;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ColumnTest {

    private List<Columns> fixture;

    @BeforeEach
    void init() {
        // 테스트용 fixture 객체 초기화
        fixture = new ArrayList<>(10);
        for (int i = 1; i <= 10; i++) {
            Columns columns =
                    Columns.builder().id(i).title("test" + i).year(0).navigationId(0).build();
            fixture.add(columns);
        }
    }

    @Test
    @DisplayName("connectAll을 호출하면 연결이 끊긴 컬럼없이 모두 연결된다.")
    void columnsUtilTest() {
        ColumnsUtil.connectAll(fixture);

        long nullCnt = fixture.stream().filter(c -> c.getNextColumnsId() == null).count();

        assertEquals(nullCnt, 1);
    }

    @Test
    @DisplayName("connectAll 호출 전과 후의 nextColumnId가 null인 개수는 다르다.")
    void columnUtilTest2() {

        assertAll(
                () -> {
                    long nullCnt =
                            fixture.stream().filter(c -> c.getNextColumnsId() == null).count();
                    assertEquals(nullCnt, fixture.size());
                },
                () -> {
                    ColumnsUtil.connectAll(fixture);
                    long nullCnt =
                            fixture.stream().filter(c -> c.getNextColumnsId() == null).count();
                    assertEquals(nullCnt, 1);
                });
    }

    @Test
    @DisplayName("findNextColumnsIdIsNull은 nextColumnsId가 null인 가장 처음의 인덱스를 반환한다.")
    void columnsUtilTest3() {
        assertAll(
                () -> {
                    int idx = ColumnsUtil.findNextColumnIdIsNull(fixture);
                    assertEquals(idx, 0);
                },
                () -> {
                    ColumnsUtil.connectAll(fixture);
                    int idx = ColumnsUtil.findNextColumnIdIsNull(fixture);
                    assertEquals(idx, fixture.size() - 1);
                });
    }
}
