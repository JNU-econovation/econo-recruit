package com.econovation.recruit.api.applicant.service;

import com.econovation.recruit.api.applicant.usecase.AnswerLoadUseCase;
import com.econovation.recruitcommon.exception.OutOfIndexException;
import com.econovation.recruitdomain.domains.applicant.adaptor.AnswerAdaptor;
import com.econovation.recruitdomain.domains.applicant.domain.Answer;
import com.econovation.recruitdomain.domains.score.adaptor.ScoreAdaptor;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnswerService implements AnswerLoadUseCase {
    private final AnswerAdaptor answerAdaptor;
    private final ScoreAdaptor scoreAdaptor;

    @Override
    public Map<String, String> execute(String applicantId) {
        List<Answer> answers = answerAdaptor.findByAnswerId(applicantId);
        HashMap<String, String> collect =
                answers.stream()
                        .collect(
                                Collectors.toMap(
                                        answer -> answer.getQuestion().getName(),
                                        Answer::getAnswer,
                                        (existing, replacement) -> existing,
                                        LinkedHashMap::new));

        LinkedHashMap<String, String> result = new LinkedHashMap<>();
        result.put("id", applicantId);
        result.putAll(collect);

        return result;
    }

    @Override
    public List<Map<String, String>> execute(List<String> fields) {
        List<Answer> answers = answerAdaptor.findAll();
        return splitByAnswersInApplicantId(fields, answers);
    }

    @Override
    public Map<String, HashMap<String, String>> findAllApplicantVo(List<String> fields) {
        List<Answer> answers = answerAdaptor.findAll();
        if (answers.isEmpty()) {
            return Collections.emptyMap();
        }
        return answers.stream()
                .filter(answer -> fields.contains(answer.getQuestion().getName()))
                .collect(
                        Collectors.groupingBy(
                                Answer::getApplicantId,
                                Collectors.toMap(
                                        answer -> answer.getQuestion().getName(),
                                        Answer::getAnswer,
                                        (existing, replacement) -> existing,
                                        HashMap::new)));
    }

    private List<Map<String, String>> splitByAnswersInApplicantId(
            List<String> fields, List<Answer> answers) {
        return answers.stream()
                .collect(
                        Collectors.groupingBy(
                                Answer::getApplicantId,
                                Collectors.toMap(
                                        answer -> answer.getQuestion().getName(),
                                        Answer::getAnswer,
                                        (existing, replacement) -> existing,
                                        HashMap::new)))
                .entrySet()
                .stream()
                .map(
                        entry -> {
                            HashMap<String, String> map = new HashMap<>();
                            map.put("id", entry.getKey());
                            fields.forEach(
                                    field -> {
                                        if (entry.getValue().containsKey(field)) {
                                            map.put(field, entry.getValue().get(field));
                                        } else {
                                            map.put(field, "");
                                        }
                                    });
                            return map;
                        })
                .collect(Collectors.toList());
    }

    private List<Map<String, String>> splitByAnswersInApplicantId(List<Answer> answers) {
        return answers.stream()
                .collect(
                        Collectors.groupingBy(
                                Answer::getApplicantId,
                                Collectors.toMap(
                                        answer -> answer.getQuestion().getName(),
                                        Answer::getAnswer,
                                        (existing, replacement) -> existing,
                                        HashMap::new)))
                .entrySet()
                .stream()
                .map(
                        entry -> {
                            LinkedHashMap<String, String> map = new LinkedHashMap<>();
                            map.put("id", entry.getKey());
                            entry.getValue().forEach(map::put);
                            return map;
                        })
                .collect(Collectors.toList());
    }

    @Override
    public List<Map<String, String>> execute(List<String> fields, List<String> applicantIds) {
        List<Answer> answers = answerAdaptor.findByAnswerIds(applicantIds);
        return splitByAnswersInApplicantId(fields, answers);
    }

    @Override
    public List<Map<String, String>> execute() {
        List<Answer> answers = answerAdaptor.findAll();
        return splitByAnswersInApplicantId(answers);
    }

    @Override
    public List<Map<String, String>> execute(Integer page) {
        if (page < 1) throw OutOfIndexException.EXCEPTION;
        List<Answer> answers = answerAdaptor.findAll(page);
        List<Map<String, String>> results = splitByAnswersInApplicantId(answers);
        return results;

        //        if (sortType.equals("score")) {
        // Map -> id
        /*            List<List<Score>> scores = results.stream().map(map -> map.get("id"))
                .map(scoreAdaptor::findByApplicantId).collect(Collectors.toList());
        // groupBy applicant 평균 점수
        Map<String, Double> averageScore = scores.stream().mapToDouble(list -> list.stream()
                        .mapToDouble(Score::getScore).average().orElse(0)).boxed()
                .collect(Collectors.toMap(*/
    }

    @Override
    public Map<String, String> execute(String applicantId, List<String> fields) {
        List<Answer> answers = answerAdaptor.findByAnswerId(applicantId);
        return splitByAnswersInApplicantId(fields, answers).get(0);
    }
}
