package com.econovation.recruit.api.card.usecase;

import com.econovation.recruitcommon.annotation.UseCase;
import com.econovation.recruitdomain.domains.board.domain.Columns;
import java.util.List;

@UseCase
public interface ColumnsUseCase {
    List<Columns> getByNavigationId(Integer navigationId);
}
