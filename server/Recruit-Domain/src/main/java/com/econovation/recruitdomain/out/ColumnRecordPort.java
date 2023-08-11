package com.econovation.recruitdomain.out;

import com.econovation.recruitcommon.annotation.Port;
import com.econovation.recruitdomain.domains.board.domain.Columns;
import java.util.List;

@Port
public interface ColumnRecordPort {
    Columns save(Columns column);

    List<Columns> saveAll(List<Columns> columns);
}
