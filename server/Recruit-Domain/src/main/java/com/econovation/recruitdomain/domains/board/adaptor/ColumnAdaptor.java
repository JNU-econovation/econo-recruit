package com.econovation.recruitdomain.domains.board.adaptor;

import com.econovation.recruitcommon.annotation.Adaptor;
import com.econovation.recruitdomain.domains.board.domain.Column;
import com.econovation.recruitdomain.domains.board.domain.ColumnRepository;
import com.econovation.recruitdomain.out.ColumnLoadPort;
import com.econovation.recruitdomain.out.ColumnRecordPort;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Adaptor
public class ColumnAdaptor implements ColumnRecordPort, ColumnLoadPort{
    private final ColumnRepository columnRepository;
    @Override
    public List<Column> getColumnByNavigationId(Integer navigationId) {
        return columnRepository.findByNavigationId(navigationId);
    }

    @Override
    public Column save(Column column) {
        return columnRepository.save(column);
    }

    @Override
    public List<Column> saveAll(List<Column> columns) {
        return columnRepository.saveAll(columns);
    }
}
