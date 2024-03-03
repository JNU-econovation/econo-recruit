package com.econovation.recruit.api.record.service;

import com.econovation.recruit.api.applicant.usecase.ApplicantQueryUseCase;
import com.econovation.recruit.api.record.dto.RecordsViewResponseDto;
import com.econovation.recruit.api.record.usecase.RecordUseCase;
import com.econovation.recruit.utils.sort.SortHelper;
import com.econovation.recruit.utils.vo.PageInfo;
import com.econovation.recruitdomain.domains.applicant.domain.MongoAnswer;
import com.econovation.recruitdomain.domains.applicant.exception.ApplicantNotFoundException;
import com.econovation.recruitdomain.domains.dto.CreateRecordDto;
import com.econovation.recruitdomain.domains.dto.UpdateRecordDto;
import com.econovation.recruitdomain.domains.record.domain.Record;
import com.econovation.recruitdomain.domains.record.exception.RecordDuplicateCreatedException;
import com.econovation.recruitdomain.domains.record.exception.RecordNotFoundException;
import com.econovation.recruitdomain.domains.score.domain.Score;
import com.econovation.recruitdomain.out.RecordLoadPort;
import com.econovation.recruitdomain.out.RecordRecordPort;
import com.econovation.recruitdomain.out.ScoreLoadPort;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RecordService implements RecordUseCase {
    private final RecordRecordPort recordRecordPort;
    private final RecordLoadPort recordLoadPort;
    private final ScoreLoadPort scoreLoadPort;
    private final ApplicantQueryUseCase applicantQueryUseCase;
    private final SortHelper<Record> sortHelper;

    @Override
    @Transactional
    public Record createRecord(CreateRecordDto recordDto) {
        if (applicantQueryUseCase.execute(recordDto.getApplicantId()) == null) {
            throw ApplicantNotFoundException.EXCEPTION;
        }
        if (recordLoadPort.findByApplicantId(recordDto.getApplicantId()).isPresent()) {
            throw RecordDuplicateCreatedException.EXCEPTION;
        }
        Record record = CreateRecordDto.toRecord(recordDto);
        return recordRecordPort.save(record);
    }

    @Override
    public List<Record> findAll() {
        return recordLoadPort.findAll();
    }

    /**
     * 1. Newest // 시간 순 오름차순 2. Name // 이름순 오름차순 3. Object // 지원 분야별 오름차순 4. Score // 점수 내림차순
     *
     * @return List<RecordResponseDto> // 지원자의 면접기록을 페이지별로 조회합니다 ( 이 화면에서는 Applicants,Scores,
     *     Records를 모두 조회합니다 )
     */
    @Override
    public RecordsViewResponseDto execute(Integer page, String sortType) {
        List<Record> result = recordLoadPort.findAll(page);
        PageInfo pageInfo = getPageInfo(page);
        if (result.isEmpty()) {
            return RecordsViewResponseDto.of(
                    pageInfo,
                    Collections.emptyList(),
                    Collections.emptyMap(),
                    Collections.emptyList());
        }

        List<String> applicantIds = result.stream().map(Record::getApplicantId).toList();
        List<Score> scores = scoreLoadPort.findByApplicantIds(applicantIds);
        Map<String, Double> scoreMap =
                scores.stream()
                        .collect(
                                Collectors.groupingBy(
                                        Score::getApplicantId,
                                        Collectors.averagingDouble(Score::getScore)));
        List<MongoAnswer> applicants = applicantQueryUseCase.execute(applicantIds);

        if (sortType.equals("score")) {
            List<Record> records = sortRecordsByScoresDesc(result, scoreMap);
            return RecordsViewResponseDto.of(pageInfo, records, scoreMap, applicants);
        } else {
            List<Record> records = sortRecordsByApplicantsAndSortType(result, applicants, sortType);
            return RecordsViewResponseDto.of(pageInfo, records, scoreMap, applicants);
        }
    }

    private List<Record> sortRecordsByScoresDesc(
            List<Record> records, Map<String, Double> scoreMap) {
        // score 내림차순 정렬
        return records.stream()
                .sorted(
                        Comparator.comparing(
                                record -> {
                                    Double score = scoreMap.get(record.getApplicantId());
                                    return score == null ? 0 : score;
                                }))
                .toList();
    }

    private List<Record> sortRecordsByApplicantsAndSortType(
            List<Record> records, List<MongoAnswer> applicants, String sortType) {
        // Newest, Name, Object 정렬
        sortHelper.sort(applicants, sortType);
        Map<String, Integer> applicantIndexMap = new HashMap<>();
        for (int i = 0; i < applicants.size(); i++) {
            applicantIndexMap.put(applicants.get(i).getId(), i);
        }

        return records.stream()
                .sorted(
                        Comparator.comparing(
                                record ->
                                        applicantIndexMap.getOrDefault(
                                                record.getApplicantId(), Integer.MAX_VALUE)))
                .toList();
    }

    private PageInfo getPageInfo(Integer page) {
        long totalCount = recordLoadPort.getTotalCount();
        return new PageInfo(totalCount, page);
    }

    @Override
    public Record findByApplicantId(String applicantId) {
        Optional<Record> byApplicantId = recordLoadPort.findByApplicantId(applicantId);
        return byApplicantId.orElseThrow(() -> RecordNotFoundException.EXCEPTION);
    }

    @Override
    @Transactional
    public void updateRecordUrl(String applicantId, String url) {
        recordLoadPort
                .findByApplicantId(applicantId)
                .ifPresent(
                        record -> {
                            record.updateUrl(url);
                        });
    }

    @Override
    @Transactional
    public void updateRecordContents(String applicantId, String contents) {
        recordLoadPort
                .findByApplicantId(applicantId)
                .ifPresent(
                        record -> {
                            record.updateRecord(contents);
                        });
    }

    @Override
    @Transactional
    public void updateRecord(String applicantId, UpdateRecordDto updateRecordDto) {
        recordLoadPort
                .findByApplicantId(applicantId)
                .ifPresent(
                        record -> {
                            if (updateRecordDto.getUrl() != null) {
                                record.updateUrl(updateRecordDto.getUrl());
                            }
                            if (updateRecordDto.getRecord() != null) {
                                record.updateRecord(updateRecordDto.getRecord());
                            }
                        });
    }
}
