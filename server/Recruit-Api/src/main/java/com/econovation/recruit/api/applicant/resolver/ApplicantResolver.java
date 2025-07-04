package com.econovation.recruit.api.applicant.resolver;

import com.econovation.recruit.api.applicant.dto.AnswersResponseDto;
import com.econovation.recruit.api.applicant.usecase.ApplicantQueryUseCase;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ApplicantResolver {

    private final ApplicantQueryUseCase applicantQueryUseCase;

    @QueryMapping
    @PreAuthorize("hasAnyRole('ROLE_ROLE_PRESIDENT', 'ROLE_ROLE_OPERATION', 'ROLE_ROLE_TF')")
    public AnswersResponseDto getApplicants(
            @Argument Integer year,
            @Argument Integer page,
            @Argument String order,
            @Argument String searchKeyword,
            @Argument List<String> requestedQnaFields) {
        return applicantQueryUseCase.executeFiltered(
                year, page, order, searchKeyword, requestedQnaFields);
    }

    @QueryMapping
    @PreAuthorize("hasAnyRole('ROLE_ROLE_PRESIDENT', 'ROLE_ROLE_OPERATION', 'ROLE_ROLE_TF')")
    public Map<String, Object> getApplicant(
            @Argument String applicantId, @Argument List<String> requestedQnaFields) {
        return applicantQueryUseCase.executeFiltered(applicantId, requestedQnaFields);
    }
}
