package com.econovation.recruit.api.record.dto;

import com.econovation.recruitdomain.domains.applicant.constant.ApplicantQnaKeys;
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
                        + applicant.getQna().get(ApplicantQnaKeys.FIELD).toString()
                        + "] "
                        + applicant.getQna().get(ApplicantQnaKeys.NAME).toString();
        return new SimpleRecordViewResponseDto(
                recordVo.getApplicantId(),
                name,
                applicant.getQna().get(ApplicantQnaKeys.FIELD1).toString(),
                applicant.getQna().get(ApplicantQnaKeys.FIELD2).toString(),
                applicant.getQna().get(ApplicantQnaKeys.GRADE).toString(),
                applicant.getQna().get(ApplicantQnaKeys.SEMESTER).toString(),
                applicant.getApplicantState());
    }
}
