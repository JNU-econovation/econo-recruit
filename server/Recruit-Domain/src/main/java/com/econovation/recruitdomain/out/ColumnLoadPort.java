package com.econovation.recruitdomain.out;

import com.econovation.recruitcommon.annotation.Port;
import com.econovation.recruitdomain.domains.board.domain.Columns;
import java.util.List;

@Port
public interface ColumnLoadPort {
    Columns findById(Integer id);

    List<Columns> getColumnByNavigationId(Integer navigationId);

    Columns getColumnByPrevColLocAndNextColLocAndNavigationId(
            Integer nextColLoc, Integer navigationId);
}
