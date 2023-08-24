package com.econovation.recruitdomain.domains.dto;

import com.econovation.recruitdomain.domains.record.domain.Record;
import java.util.UUID;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class CreateRecordDto {
    private UUID applicantId;
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
