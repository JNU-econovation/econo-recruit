package com.econovation.recruit.application.port.in;


import com.econovation.recruitdomain.domain.applicant.Applicant;
import com.econovation.recruitdomain.domain.card.Card;
import java.util.List;

public interface CardRegisterUseCase {

    Card saveApplicantCard(Applicant applicant);

    List<Card> findAll();

    void deleteById(Integer cardId);
}
