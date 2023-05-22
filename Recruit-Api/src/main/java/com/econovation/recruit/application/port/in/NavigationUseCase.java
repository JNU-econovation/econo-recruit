package com.econovation.recruit.application.port.in;

import com.econovation.recruit.domain.board.Navigation;

public interface NavigationUseCase {
    Navigation createNavigation(String navTitle);

    void deleteById(Integer navId);

    Navigation updateNavigationByNavLoc(Integer navLoc, String navTitle);
}
