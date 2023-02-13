package com.econovation.recruit.application.port.out;

import com.econovation.recruit.domain.board.Navigation;

public interface NavigationLoadPort {
    Navigation getByNavLoc(Integer navLoc);
}
