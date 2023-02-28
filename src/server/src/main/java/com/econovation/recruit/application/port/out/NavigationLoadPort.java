package com.econovation.recruit.application.port.out;

import com.econovation.recruit.domain.board.Navigation;

import java.util.List;

public interface NavigationLoadPort {
    Navigation getByNavLoc(Integer navLoc);

    List<Navigation> getAllNavigation();
}
