package com.econovation.recruitdomain.domains.dto;

import com.econovation.recruitdomain.domains.record.domain.Record;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
@Getter
public class CreateRecordDto {
    private String applicantId;
    private String url;
    private String record;

    public static Record toRecord(CreateRecordDto createRecordDto) {
        return Record.builder()
                .applicantId(createRecordDto.getApplicantId())
                .url(createRecordDto.getUrl())
                .record(createRecordDto.getRecord())
                .build();
    }
}
