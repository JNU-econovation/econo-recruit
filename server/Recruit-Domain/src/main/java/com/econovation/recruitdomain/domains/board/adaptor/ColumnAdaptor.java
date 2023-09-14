package com.econovation.recruitdomain.domains.board.adaptor;

import com.econovation.recruitcommon.annotation.Adaptor;
import com.econovation.recruitdomain.domains.board.domain.ColumnRepository;
import com.econovation.recruitdomain.domains.board.domain.Columns;
import com.econovation.recruitdomain.domains.board.exception.ColumnsNotFoundException;
import com.econovation.recruitdomain.out.ColumnLoadPort;
import com.econovation.recruitdomain.out.ColumnRecordPort;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Adaptor
public class ColumnAdaptor implements ColumnRecordPort, ColumnLoadPort {
    private final ColumnRepository columnRepository;

    @Override
    public Columns findById(Integer columnsId) {
        return columnRepository
                .findById(columnsId)
                .orElseThrow(() -> ColumnsNotFoundException.EXCEPTION);
    }

    @Override
    public List<Columns> getColumnByNavigationId(Integer navigationId) {
        return columnRepository.findByNavigationId(navigationId);
    }

    @Override
    public Columns getColumnByNextColumnsId(Integer nextColId) {
        return columnRepository
                .findById(nextColId)
                .orElseThrow(() -> ColumnsNotFoundException.EXCEPTION);
    }

    @Override
    public Optional<Columns> getColumnOptionalByNextColumnsId(Integer nextColId) {
        return columnRepository.findById(nextColId);
    }

    @Override
    public List<Columns> getColumnsByNavigationId(Integer navigationId) {
        List<Columns> byNavigationId = columnRepository.findByNavigationId(navigationId);
        if (byNavigationId.isEmpty()) {
            return null;
        }
        return byNavigationId;
    }

    @Override
    public Optional<Columns> getByNextColumnsId(Integer nextColumnId) {
        return columnRepository.findByNextColumnsId(nextColumnId);
    }

    @Override
    public Columns save(Columns column) {
        return columnRepository.save(column);
    }

    @Override
    public List<Columns> saveAll(List<Columns> columns) {
        return columnRepository.saveAll(columns);
    }
}
