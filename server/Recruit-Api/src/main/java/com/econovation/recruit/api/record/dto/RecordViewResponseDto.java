package com.econovation.recruit.api.record.dto;

import com.econovation.recruitdomain.domains.applicant.domain.MongoAnswer;
import com.econovation.recruitdomain.domains.record.domain.Record;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RecordViewResponseDto {
    private String applicantId;
    private Double scores;
    private String url;
    private String record;
    private String field1;
    private String field2;
    private String grade;
    private String semester;
    private String modifiedAt;

    public static RecordViewResponseDto from(Record recordVo, Double score, MongoAnswer applicant) {
        return new RecordViewResponseDto(
                recordVo.getApplicantId(),
                score,
                recordVo.getUrl(),
                recordVo.getRecord(),
                applicant.getQna().get("field1").toString(),
                applicant.getQna().get("field2").toString(),
                applicant.getQna().get("grade").toString(),
                applicant.getQna().get("semester").toString(),
                applicant.getModifiedDate());
    }
}
