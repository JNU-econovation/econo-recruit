package com.econovation.recruitdomain.domains.applicant.adaptor;

import com.econovation.recruitcommon.annotation.Adaptor;
import com.econovation.recruitdomain.domains.applicant.domain.Answer;
import com.econovation.recruitdomain.domains.applicant.domain.AnswerRepository;
import com.econovation.recruitdomain.domains.applicant.exception.ApplicantNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;

@Adaptor
@RequiredArgsConstructor
public class AnswerAdaptor {
    private final AnswerRepository answerRepository;

    public void save(Answer answer) {
        answerRepository.save(answer);
    }

    public void saveAll(List<Answer> answers) {
        answerRepository.saveAll(answers);
    }

    public List<Answer> findAll() {
        return answerRepository.findAll();
    }

    public List<Answer> findByAnswerIds(List<String> applicantIds) {
        List<Answer> applicants = answerRepository.findByApplicantIdIn(applicantIds);
        if (applicants.isEmpty()) {
            throw ApplicantNotFoundException.EXCEPTION;
        }
        return applicants;
    }

    public List<Answer> findByAnswerId(String applicantId) {
        List<Answer> applicants = answerRepository.findByApplicantId(applicantId);
        if (applicants.isEmpty()) {
            throw ApplicantNotFoundException.EXCEPTION;
        }
        return applicants;
    }
}
