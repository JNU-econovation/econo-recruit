package com.econovation.recruitdomain.out;

import com.econovation.recruitdomain.domains.board.domain.Columns;
import java.util.List;

public interface ColumnRecordPort {
    Columns save(Columns column);

    List<Columns> saveAll(List<Columns> columns);
}
