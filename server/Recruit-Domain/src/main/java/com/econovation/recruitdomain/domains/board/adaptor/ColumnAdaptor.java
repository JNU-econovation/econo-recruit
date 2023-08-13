package com.econovation.recruitdomain.domains.board.adaptor;

import com.econovation.recruitcommon.annotation.Adaptor;
import com.econovation.recruitdomain.domains.board.domain.ColumnRepository;
import com.econovation.recruitdomain.domains.board.domain.Columns;
import com.econovation.recruitdomain.domains.board.exception.ColumnsNotFoundException;
import com.econovation.recruitdomain.out.ColumnLoadPort;
import com.econovation.recruitdomain.out.ColumnRecordPort;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Adaptor
public class ColumnAdaptor implements ColumnRecordPort, ColumnLoadPort {
    private final ColumnRepository columnRepository;

    @Override
    public Columns findById(Integer id) {
        return columnRepository.findById(id).orElseThrow(() -> ColumnsNotFoundException.EXCEPTION);
    }

    @Override
    public List<Columns> getColumnByNavigationId(Integer navigationId) {
        return columnRepository.findByNavigationId(navigationId);
    }

    @Override
    public Columns getColumnByPrevColLocAndNextColLocAndNavigationId(
            Integer nextColLoc, Integer navigationId) {
        return columnRepository
                .findByNextColIdAndNavigationId(nextColLoc, navigationId)
                .orElseThrow(() -> ColumnsNotFoundException.EXCEPTION);
    }

    @Override
    public Columns getColumnById(Integer nextColId) {
        return columnRepository
                .findById(nextColId)
                .orElseThrow(() -> ColumnsNotFoundException.EXCEPTION);
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
