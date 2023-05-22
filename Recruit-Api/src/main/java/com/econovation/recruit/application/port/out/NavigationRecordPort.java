package com.econovation.recruit.application.port.out;

import com.econovation.recruit.domain.board.Navigation;

public interface NavigationRecordPort {
    Navigation save(Navigation navigation);

    void deleteById(Integer navId);
}
