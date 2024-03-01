package com.econovation.recruit.api.applicant.service;

import com.econovation.recruit.api.applicant.aggregate.AnswerAggregate;
import com.econovation.recruit.api.applicant.dto.AnswersResponseDto;
import com.econovation.recruit.api.applicant.query.AnswerQuery;
import com.econovation.recruit.api.applicant.usecase.ApplicantQueryUseCase;
import com.econovation.recruit.utils.sort.SortHelper;
import com.econovation.recruit.utils.vo.PageInfo;
import com.econovation.recruitdomain.domains.applicant.adaptor.AnswerAdaptor;
import com.econovation.recruitdomain.domains.applicant.domain.MongoAnswer;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ApplicantService implements ApplicantQueryUseCase {
    private final AnswerAdaptor answerAdaptor;
    private final QueryGateway queryGateway;
    private final SortHelper<MongoAnswer> sortHelper;

    @Value("${econovation.year}")
    private Integer year;

    @Transactional(readOnly = true)
    public Map<String, Object> execute(String answerId) {
        Map<String, Object> qna =
                queryGateway
                        .query(
                                new AnswerQuery(answerId),
                                ResponseTypes.instanceOf(AnswerAggregate.class))
                        .join()
                        .getQna();
        qna.put("id", answerId);
        return qna;
    }

    @Transactional(readOnly = true)
    public AnswersResponseDto execute(Integer year, Integer page, String sortType) {
        PageInfo pageInfo = getPageInfo(year, page);
        List<MongoAnswer> result = answerAdaptor.findByYear(year, page);

        sortHelper.sort(result, sortType);
        List<Map<String, Object>> sortedResult = result.stream().map(MongoAnswer::getQna).toList();

        if (sortedResult.isEmpty()) {
            return AnswersResponseDto.of(Collections.emptyList(), pageInfo);
        }
        return AnswersResponseDto.of(sortedResult, pageInfo);
    }

    private PageInfo getPageInfo(Integer year, Integer page) {
        long totalCount = answerAdaptor.getTotalCountByYear(year);
        return new PageInfo(totalCount, page);
    }

    @Override
    public List<Map<String, Object>> execute(
            List<String> fields, Integer year, Integer page, String sortType) {
        AnswersResponseDto execute = execute(year, page, sortType);
        return splitByAnswerFilteredByFields(fields, execute.getAnswers());
    }

    @Override
    @Transactional(readOnly = true)
    public List<MongoAnswer> execute(List<String> applicantIds) {
        return answerAdaptor.findByApplicantIds(applicantIds);
    }

    @Override
    public Map<String, Map<String, Object>> findAllApplicantVo(List<String> fields) {
        List<MongoAnswer> answers = answerAdaptor.findAll();
        Map<String, Map<String, Object>> result = new HashMap<>();
        if (answers.isEmpty()) {
            return Collections.emptyMap();
        }
        answers.stream()
                .forEach(
                        answer -> {
                            Map<String, Object> map = new HashMap<>();
                            fields.forEach(
                                    field -> {
                                        if (answer.getQna().containsKey(field)) {
                                            map.put(field, answer.getQna().get(field));
                                        }
                                    });
                            result.put(answer.getId(), map);
                        });
        return result;
    }

<<<<<<< HEAD
=======
    @Override
    public AnswersResponseDto search(Integer page, String searchKeyword) {
        List<MongoAnswer> answers = answerAdaptor.findBySearchKeyword(page, searchKeyword);
        return AnswersResponseDto.of(
                answers.stream().map(MongoAnswer::getQna).toList(),
                new PageInfo(answers.size(), 1));
    }

>>>>>>> f414665 ([feat]: Mongo Auto Text Indexing 및 페이지네이션 검색 기능 추가)
    private List<Map<String, Object>> splitByAnswerFilteredByFields(
            List<String> fields, List<Map<String, Object>> answers) {
        return answers.stream()
                .map(
                        answer -> {
                            Map<String, Object> map = new HashMap<>();
                            fields.forEach(
                                    field -> {
                                        if (answer.containsKey(field)) {
                                            map.put(field, answer.get(field));
                                        } else {
                                            map.put(field, "");
                                        }
                                    });
                            return map;
                        })
                .toList();
    }

    private List<Map<String, Object>> splitByAnswers(
            List<String> fields, List<MongoAnswer> answers) {
        return answers.stream()
                .map(
                        answer -> {
                            Map<String, Object> map = new HashMap<>();
                            map.put("id", answer.getId());
                            fields.forEach(
                                    field -> {
                                        if (answer.getQna().containsKey(field)) {
                                            map.put(field, answer.getQna().get(field).toString());
                                        }
                                    });
                            return map;
                        })
                .toList();
    }

    @Override
    public List<Map<String, Object>> execute() {
        List<MongoAnswer> answers = answerAdaptor.findAll();
        return answers.stream()
                .map(
                        answer -> {
                            Map<String, Object> map = new HashMap<>();
                            map.put("id", answer.getId());
                            map.putAll(answer.getQna());
                            return map;
                        })
                .toList();
    }

    @Override
    public Map<String, Object> execute(String applicantId, List<String> fields) {
        AnswerAggregate join =
                queryGateway
                        .query(
                                new AnswerQuery(applicantId),
                                ResponseTypes.instanceOf(AnswerAggregate.class))
                        .join();
        MongoAnswer mongoAnswer = new MongoAnswer(join.getId(), join.getYear(), join.getQna());
        return splitByAnswers(fields, List.of(mongoAnswer)).get(0);
    }

    @Override
    public List<Map<String, Object>> execute(List<String> fields, Integer page) {
        List<MongoAnswer> byYear = answerAdaptor.findByYear(year, page);
        return splitByAnswers(fields, byYear);
    }
}
