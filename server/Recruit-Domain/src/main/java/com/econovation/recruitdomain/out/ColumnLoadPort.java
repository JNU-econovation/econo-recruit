package com.econovation.recruitdomain.out;

import com.econovation.recruitcommon.annotation.Port;
import com.econovation.recruitdomain.domains.board.domain.Column;
import java.util.List;

@Port
public interface ColumnLoadPort {

    List<Column> getColumnByNavigationId(Integer navigationId);
}
