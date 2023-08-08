package com.econovation.recruitdomain.domains.applicant.adapter;


import com.econovation.recruitcommon.annotation.Adaptor;
import com.econovation.recruitdomain.domains.applicant.domain.Question;
import com.econovation.recruitdomain.domains.applicant.domain.QuestionRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;

@Adaptor
@RequiredArgsConstructor
public class QuestionAdaptor {
    private final QuestionRepository questionRepository;

    public void save(Question question) {
        questionRepository.save(question);
    }

    public void saveAll(List<Question> questions) {
        questionRepository.saveAll(questions);
    }

    public List<Question> findAll() {
        List<Question> all = questionRepository.findAll();
        if(all.isEmpty()){
            throw
        }
    }
}
