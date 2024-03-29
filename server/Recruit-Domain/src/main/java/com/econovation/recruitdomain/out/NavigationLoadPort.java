package com.econovation.recruitdomain.out;

import com.econovation.recruitdomain.domains.board.domain.Navigation;
import java.util.List;

public interface NavigationLoadPort {
    Navigation getByNavigationId(Integer navigationId);

    List<Navigation> getAllNavigation();
}
