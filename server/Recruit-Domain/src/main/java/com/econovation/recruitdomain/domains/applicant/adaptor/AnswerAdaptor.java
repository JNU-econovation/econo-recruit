package com.econovation.recruitdomain.domains.applicant.adaptor;

import static com.econovation.recruitcommon.consts.RecruitStatic.COUNTS_PER_PAGE;

import com.econovation.recruitcommon.annotation.Adaptor;
import com.econovation.recruitdomain.domains.applicant.domain.Answer;
import com.econovation.recruitdomain.domains.applicant.domain.AnswerRepository;
import com.econovation.recruitdomain.domains.applicant.exception.ApplicantNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

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

    public List<Answer> findAll(Integer page) {
        // 그룹별로 28개씩 묶어서 8개씩 조회하도록 페이징 설정
        PageRequest pageRequest =
                PageRequest.of(page - 1, COUNTS_PER_PAGE * 28, Sort.by("createdAt"));
        List<Answer> content = answerRepository.findAll(pageRequest).getContent();
        return content;
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
