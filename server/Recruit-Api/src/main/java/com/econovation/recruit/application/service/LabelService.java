package com.econovation.recruit.application.service;

import com.econovation.recruit.application.port.in.LabelUseCase;
import com.econovation.recruitcommon.utils.Result;
import com.econovation.recruitdomain.common.aop.redissonLock.RedissonLock;
import com.econovation.recruitdomain.domains.card.Card;
import com.econovation.recruitdomain.domains.interviewer.domain.Interviewer;
import com.econovation.recruitdomain.domains.label.Label;
import com.econovation.recruitdomain.domains.label.exception.LabelNotFoundException;
import com.econovation.recruitdomain.out.CardLoadPort;
import com.econovation.recruitdomain.out.InterviewerLoadPort;
import com.econovation.recruitdomain.out.LabelLoadPort;
import com.econovation.recruitdomain.out.LabelRecordPort;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LabelService implements LabelUseCase {
    private final LabelRecordPort labelRecordPort;
    private final LabelLoadPort labelLoadPort;
    private final InterviewerLoadPort interviewerLoadPort;
    private final CardLoadPort cardLoadPort;

    @Override
    @RedissonLock(LockName = "라벨", identifier = "applicantId")
    @Transactional
    public Label createLabel(Integer applicantId, Integer idpId) {
        Card card = cardLoadPort.findByApplicantId(applicantId);
        Label label = Label.builder().idpId(idpId).applicantId(applicantId).build();
        Result<Label> result = labelRecordPort.save(label);
        result.onSuccess(label1 -> card.plusLabelCount());
        result.onFailure(
                e -> {
                    throw LabelNotFoundException.EXCEPTION;
                });
        return label;
    }

    @Override
    public List<String> findByApplicantId(Integer applicantId) {
        List<Label> labels = labelLoadPort.loadLabelByApplicantId(applicantId);

        if (labels.isEmpty()) {
            throw LabelNotFoundException.EXCEPTION;
        }

        List<Integer> idpIds = labels.stream().map(Label::getIdpId).collect(Collectors.toList());
        List<Interviewer> interviewers = interviewerLoadPort.loadInterviewerByIdpIds(idpIds);

        Map<Integer, String> interviewerMap =
                interviewers.stream()
                        .collect(Collectors.toMap(Interviewer::getId, Interviewer::getName));

        return labels.stream()
                .map(label -> interviewerMap.get(label.getIdpId()))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    @RedissonLock(LockName = "라벨", identifier = "applicantId")
    public void deleteLabel(Integer applicantId, Integer idpId) {
        Label label = labelLoadPort.loadLabelByApplicantIdAndIdpId(applicantId, idpId);

        // Card LabelCount 감소
        cardLoadPort.findByApplicantId(applicantId).minusLabelCount();

        labelRecordPort.delete(label);
    }
}
