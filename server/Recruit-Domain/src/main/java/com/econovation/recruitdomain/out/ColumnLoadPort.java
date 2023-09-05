package com.econovation.recruitdomain.out;

import com.econovation.recruitdomain.domains.board.domain.Columns;
import java.util.List;
import java.util.Optional;

public interface ColumnLoadPort {
    Columns findById(Integer id);

    List<Columns> getColumnByNavigationId(Integer navigationId);

    Columns getColumnByNextColumnsId(Integer nextColId);
    Optional<Columns> getColumnOptionalByNextColumnsId(Integer nextColId);

    List<Columns> getColumnsByNavigationId(Integer navigationId);

    Optional<Columns> getByNextColumnsId(Integer id);
}
