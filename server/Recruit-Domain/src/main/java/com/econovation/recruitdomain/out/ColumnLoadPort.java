package com.econovation.recruitdomain.out;

import com.econovation.recruitcommon.annotation.Port;
import com.econovation.recruitdomain.domains.board.domain.Columns;
import java.util.List;

@Port
public interface ColumnLoadPort {

    List<Columns> getColumnByNavigationId(Integer navigationId);

    Columns getColumnByPrevColLocAndNextColLocAndNavigationId(
            Integer prevColLoc, Integer nextColLoc, Integer navigationId);
}
