package com.econovation.recruitdomain.out;

import com.econovation.recruitdomain.domains.board.domain.Columns;
import java.util.List;
import java.util.Optional;

public interface ColumnLoadPort {
    Columns findById(Integer id);

    List<Columns> getColumnByNavigationId(Integer navigationId);

    Columns getColumnByNextColumnsId(Integer nextColId);

    Columns getColumnByYearAndTitle(String title, Integer year);

    Optional<Columns> getColumnOptionalByNextColumnsId(Integer nextColId);

    boolean existsColumnsByTitle(String title, int year);

    List<Columns> getColumnsByNavigationId(Integer navigationId);

    List<Columns> getColumnsByNavigationIdAndYear(Integer navigationId, Integer year);

    Optional<Columns> getByNextColumnsId(Integer id);
}
