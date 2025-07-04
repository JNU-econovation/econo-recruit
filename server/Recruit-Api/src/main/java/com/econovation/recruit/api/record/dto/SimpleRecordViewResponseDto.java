package com.econovation.recruit.api.record.dto;

import com.econovation.recruitdomain.domains.applicant.domain.MongoAnswer;
import com.econovation.recruitdomain.domains.applicant.domain.state.ApplicantState;
import com.econovation.recruitdomain.domains.record.domain.Record;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SimpleRecordViewResponseDto {
    private String applicantId;
    private String name;
    private String field1;
    private String field2;
    private String grade;
    private String semester;
    private ApplicantState state;

    public static SimpleRecordViewResponseDto from(Record recordVo, MongoAnswer applicant) {
        String name =
                "["
                        + applicant.getQna().get("field").toString()
                        + "] "
                        + applicant.getQna().get("name").toString();
        return new SimpleRecordViewResponseDto(
                recordVo.getApplicantId(),
                name,
                applicant.getQna().get("field1").toString(),
                applicant.getQna().get("field2").toString(),
                applicant.getQna().get("grade").toString(),
                applicant.getQna().get("semester").toString(),
                applicant.getApplicantState());
    }
}
