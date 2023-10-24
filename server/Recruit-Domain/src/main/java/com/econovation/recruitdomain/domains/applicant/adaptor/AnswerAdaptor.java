package com.econovation.recruitdomain.domains.applicant.adaptor;

import com.econovation.recruitcommon.annotation.Adaptor;
import com.econovation.recruitdomain.domains.applicant.domain.Answer;
import com.econovation.recruitdomain.domains.applicant.domain.AnswerRepository;
import com.econovation.recruitdomain.domains.applicant.exception.ApplicantNotFoundException;
import java.util.List;
import java.util.Optional;
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

    /*    public AnswerPageResponseDto findAll(Integer page) {
        // 그룹별로 31개씩 묶어서 8개씩 조회하도록 페이징 설정
        PageRequest pageRequest =
                PageRequest.of(page - 1, COUNTS_PER_PAGE * ANSWER_COUNTS_PER_PERSON, Sort.by("createdAt"));
        Page<Answer> content = answerRepository.findAll(pageRequest);
        Integer maxPage = content.getTotalPages();
        return AnswerPageResponseDto.of(maxPage, content.getContent());
    }*/

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

    public Answer findByAnswer(String answer) {
        Optional<Answer> result = answerRepository.findByAnswer(answer);
        return result.orElse(null);
    }
}
