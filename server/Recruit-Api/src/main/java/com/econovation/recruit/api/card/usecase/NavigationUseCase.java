package com.econovation.recruit.api.card.usecase;

import com.econovation.recruitdomain.domains.board.domain.Navigation;

public interface NavigationUseCase {
    Navigation createNavigation(String navTitle);

    void deleteById(Integer navId);

    Navigation updateNavigationByNavLoc(Integer navLoc, String navTitle);
}
