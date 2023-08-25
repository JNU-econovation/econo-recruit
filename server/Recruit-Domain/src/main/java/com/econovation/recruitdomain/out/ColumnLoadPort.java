package com.econovation.recruitdomain.out;

import com.econovation.recruitdomain.domains.board.domain.Columns;
import java.util.List;

public interface ColumnLoadPort {
    Columns findById(Integer id);

    List<Columns> getColumnByNavigationId(Integer navigationId);
    Columns getColumnByNextColumnsId(Integer nextColId);
}
