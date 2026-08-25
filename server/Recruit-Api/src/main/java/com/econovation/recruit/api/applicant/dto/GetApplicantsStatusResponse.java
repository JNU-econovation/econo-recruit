package com.econovation.recruit.api.applicant.dto;

import static com.econovation.recruitcommon.consts.RecruitStatic.PASS_STATE_KEY;

import com.econovation.recruitdomain.domains.applicant.constant.ApplicantQnaKeys;
import com.econovation.recruitdomain.domains.applicant.domain.state.ApplicantState;
import com.econovation.recruitdomain.domains.applicant.domain.state.PeriodStates;
import com.econovation.recruitdomain.domains.applicant.exception.ApplicantWrongStateException;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class GetApplicantsStatusResponse {
    private String field;
    private String field1;
    private String field2;
    private String name;
    private String id;
    private Integer year;
    private ApplicantStateResponse state;

    public static GetApplicantsStatusResponse of(Map<String, Object> result, PeriodStates period) {
        if (result.get(PASS_STATE_KEY) instanceof ApplicantState applicantState) {
            String passState = applicantState.getPassState();
            boolean isPassable = applicantState.isPassable(period);
            boolean isNonPassable = applicantState.isNonPassable(period);

            return GetApplicantsStatusResponse.builder()
                    .field((String) result.get(ApplicantQnaKeys.FIELD))
                    .field1((String) result.get(ApplicantQnaKeys.FIELD1))
                    .field2((String) result.get(ApplicantQnaKeys.FIELD2))
                    .name((String) result.get(ApplicantQnaKeys.NAME))
                    .id((String) result.get(ApplicantQnaKeys.ID))
                    .year((Integer) result.get(ApplicantQnaKeys.YEAR))
                    .state(ApplicantStateResponse.of(passState, isPassable, isNonPassable))
                    .build();
        }
        throw ApplicantWrongStateException.wrongStatusException;
    }
}
