package com.econovation.recruitdomain.out;

import com.econovation.recruitcommon.annotation.Port;
import com.econovation.recruitcommon.utils.Result;
import com.econovation.recruitdomain.domains.board.domain.Columns;
import java.util.List;

@Port
public interface ColumnLoadPort {
    Columns findById(Integer id);

    List<Columns> getColumnByNavigationId(Integer navigationId);

    Columns getColumnByPrevColLocAndNextColLocAndNavigationId(
            Integer nextColId, Integer navigationId);

    Columns getColumnById(Integer nextColId);
}
