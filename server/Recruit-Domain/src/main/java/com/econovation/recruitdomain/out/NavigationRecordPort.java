package com.econovation.recruitdomain.out;

import com.econovation.recruitdomain.domains.board.domain.Navigation;

public interface NavigationRecordPort {
    Navigation save(Navigation navigation);

    void deleteById(Integer navId);
}
