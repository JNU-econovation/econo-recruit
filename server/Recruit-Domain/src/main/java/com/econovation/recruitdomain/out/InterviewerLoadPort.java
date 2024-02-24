package com.econovation.recruitdomain.out;

import com.econovation.recruitdomain.domains.interviewer.domain.Interviewer;
import com.econovation.recruitdomain.domains.interviewer.domain.Role;
import java.util.List;
import java.util.Optional;

public interface InterviewerLoadPort {
    Interviewer loadInterviewById(Long idpId);

    List<Interviewer> loadInterviewerByIdpIds(List<Long> idpIds);

    List<Interviewer> findAll();

    Interviewer loadInterviewerByEmail(String email);

    Optional<Interviewer> loadOptionalInterviewerByEmail(String email);

    List<Interviewer> loadInterviewerByRole(Role role);
}
