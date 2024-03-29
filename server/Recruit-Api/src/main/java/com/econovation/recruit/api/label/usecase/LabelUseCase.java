package com.econovation.recruit.api.label.usecase;

import com.econovation.recruitcommon.annotation.UseCase;
import java.util.List;

@UseCase
public interface LabelUseCase {

    Boolean createLabel(String applicantId);

    List<String> findByApplicantId(String applicantId);

    void deleteLabel(String applicantId);

    void deleteLabelByCardId(Long cardId);

    Boolean createLabelByCardId(Long cardId);

    List<String> findByCardId(Long cardId);
}
