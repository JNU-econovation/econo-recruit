package com.econovation.recruit.application.port.in;

import com.econovation.recruit.domain.applicant.Applicant;
import com.econovation.recruit.domain.card.Card;

public interface CardRegisterUseCase {

    Card saveApplicantCard(Applicant applicant);
}
