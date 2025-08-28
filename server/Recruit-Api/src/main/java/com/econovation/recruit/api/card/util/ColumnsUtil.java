package com.econovation.recruit.api.card.util;

import com.econovation.recruitdomain.domains.board.domain.Columns;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ColumnsUtil {

    /**
     * 리스트를 순회하며, 중간에 nextBoardId가 null 인 것을 찾으면 바로 반환하고, 리스트를 모두 순회했는데, 찾지 못하면 마지막 인덱스 반환
     *
     * @param columns
     * @return
     */
    public static int findNextColumnIdIsNull(List<Columns> columns) {

        for (int i = 0; i < columns.size(); i++) {
            Columns c = columns.get(i);

            if (c.getNextColumnsId() == null) return i;
        }

        return columns.size() - 1;
    }

    public static boolean isSatisfiedCommonColumns(List<Columns> columns){
        int developer = 1; int designer = 1; int productManager = 1;

        for(Columns c : columns){
            switch (c.getTitle()){
                case "개발자":
                    developer--;
                    break;
                case "디자이너":
                    designer--;
                    break;
                case "기획자":
                    productManager--;
                    break;
            }
        }

        return developer+designer+productManager==0;
    }

    public static List<Columns> connectAll(List<Columns> columns) {
        // 0 -> 1 -> 2 이렇게 연결한다.
        for (int i = 0; i < columns.size() - 1; i++) {
            connect(columns.get(i), columns.get(i + 1));
        }

        return columns;
    }

    public static void connect(Columns before, Columns after) {
        before.updateNextColumnsId(after.getId());
    }
}
