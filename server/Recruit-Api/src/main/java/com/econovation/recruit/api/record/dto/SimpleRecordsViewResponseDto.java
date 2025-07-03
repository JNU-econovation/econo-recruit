package com.econovation.recruit.api.record.dto;

import com.econovation.recruit.utils.vo.PageInfo;
import com.econovation.recruitdomain.domains.applicant.domain.MongoAnswer;
import com.econovation.recruitdomain.domains.record.domain.Record;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SimpleRecordsViewResponseDto {
    private List<SimpleRecordViewResponseDto> records;
    private PageInfo pageInfo;

    public static SimpleRecordsViewResponseDto of(
            PageInfo pageInfo,
            List<Record> records,
            List<MongoAnswer> applicants) {
        List<SimpleRecordViewResponseDto> simpleRecordViewResponseDtos =
                records.stream()
                        .map(
                                record -> {
                                    MongoAnswer applicant =
                                            applicants.stream()
                                                    .filter(
                                                            a ->
                                                                    a.getId()
                                                                            .equals(
                                                                                    record
                                                                                            .getApplicantId()))
                                                    .findFirst()
                                                    .get();
                                    return SimpleRecordViewResponseDto.from(record, applicant);
                                })
                        .toList();
        return SimpleRecordsViewResponseDto.builder()
                .pageInfo(pageInfo)
                .records(simpleRecordViewResponseDtos)
                .build();
    }

    public static SimpleRecordsViewResponseDto empty(PageInfo pageInfo) {
        return SimpleRecordsViewResponseDto.builder().pageInfo(pageInfo).records(List.of()).build();
    }
}
