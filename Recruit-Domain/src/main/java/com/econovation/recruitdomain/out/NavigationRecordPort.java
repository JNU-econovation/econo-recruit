package com.econovation.recruitdomain.out;


import com.econovation.recruitdomain.domain.board.Navigation;

public interface NavigationRecordPort {
    Navigation save(Navigation navigation);

    void deleteById(Integer navId);
}
