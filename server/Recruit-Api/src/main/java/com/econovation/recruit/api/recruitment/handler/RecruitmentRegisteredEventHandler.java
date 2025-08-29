package com.econovation.recruit.api.recruitment.handler;

import com.econovation.recruit.api.card.util.ColumnsUtil;
import com.econovation.recruit.api.recruitment.quartz.RecruitmentScheduler;
import com.econovation.recruit.api.recruitment.util.LatestRecruitmentVo;
import com.econovation.recruitdomain.domains.board.domain.Board;
import com.econovation.recruitdomain.domains.board.domain.Columns;
import com.econovation.recruitdomain.domains.recruitment.event.RecruitmentRegistered;
import com.econovation.recruitdomain.out.BoardLoadPort;
import com.econovation.recruitdomain.out.BoardRecordPort;
import com.econovation.recruitdomain.out.ColumnLoadPort;
import com.econovation.recruitdomain.out.ColumnRecordPort;
import com.econovation.recruitdomain.out.RecruitmentPort;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Slf4j
@Component
@RequiredArgsConstructor
public class RecruitmentRegisteredEventHandler {

    private final RecruitmentPort recruitmentPort;
    private final RecruitmentScheduler recruitmentScheduler;
    private final LatestRecruitmentVo latestRecruitment;

    private final ColumnLoadPort columnLoadPort;
    private final ColumnRecordPort columnRecordPort;

    private final BoardLoadPort boardLoadPort;
    private final BoardRecordPort boardRecordPort;

    @Async
    @Transactional
    @EventListener(RecruitmentRegistered.class)
    public void handle(RecruitmentRegistered event) {
        doReserve(event);
        createCommonColumn();
    }

    private void doReserve(RecruitmentRegistered event) {
        // 1. 전역 상태 최신화
        // 2. NON_START 상태를, startAt 시간이 되면 RECRUITING 상태로 변경하는 작업 예약
        // 3. RECRUITING 상태를, endAt 시간이 되면, END 상태로 변경하는 작업 예약
        recruitmentPort
                .findById(event.getId())
                .ifPresent(
                        recruitment -> {
                            latestRecruitment.refreshRecruitment(recruitment);
                            recruitmentScheduler.reserveStart(recruitment);
                            recruitmentScheduler.reserveEnd(recruitment);
                        });
    }

    /** 새로운 모집이 등록되었으므로, 새로운 기수에 맞는 개발자,디자이너,기획자 컬럼을 새로 추가합니다. */
    private void createCommonColumn() {
        List<String> columnNames = List.of("개발자", "디자이너", "기획자");
        int year = latestRecruitment.getYear();

        columnNames = filterNotExists(columnNames, year);

        // 모든 컬럼이 존재하지 않으면
        if (!columnNames.isEmpty()) {
            List<Columns> commonColumns =
                    columnNames.stream()
                            .map(name -> Columns.createCommonColumn(name, year))
                            .toList();

            List<Columns> saved = columnRecordPort.saveAll(commonColumns);

            List<Columns> existColumns = columnLoadPort.getColumnsByNavigationIdAndYear(1, year);

            connect(existColumns);
            createInvisibleBoards(existColumns);
        }
    }

    private List<String> filterNotExists(List<String> columnNames, int year) {
        return columnNames.stream()
                .filter(name -> !columnLoadPort.existsColumnsByTitle(name, year))
                .peek(name -> log.info("{} 기수에 {} 컬럼이 존재하지 않아 생성합니다.", year, name))
                .toList();
    }

    /**
     * 끊긴 컬럼들을 찾아서 연결합니다.
     *
     * @param columns
     */
    private void connect(List<Columns> columns) {
        int start = ColumnsUtil.findNextColumnIdIsNull(columns);
        int end = columns.size();

        // 0 -> 1 -> 2
        ColumnsUtil.connectAll(columns.subList(start, end));
    }

    private void createInvisibleBoards(List<Columns> columns) {
        List<Board> invisibleBoards =
                columns.stream().map(c -> Board.creatInvisibleBoard(c.getId(), 1)).toList();
        boardRecordPort.saveAll(invisibleBoards);
    }
}
