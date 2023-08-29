package com.econovation.recruit.api.card.service;

import com.econovation.recruit.api.card.usecase.NavigationUseCase;
import com.econovation.recruitdomain.domains.board.domain.Navigation;
import com.econovation.recruitdomain.out.NavigationLoadPort;
import com.econovation.recruitdomain.out.NavigationRecordPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NavigationService implements NavigationUseCase {
    private final NavigationLoadPort navigationLoadPort;
    private final NavigationRecordPort navigationRecordPort;

    @Override
    public Navigation createNavigation(String navTitle) {
        Navigation navigation = Navigation.builder().navTitle(navTitle).build();
        return navigationRecordPort.save(navigation);
    }

    @Override
    public void deleteById(Integer navId) {
        navigationRecordPort.deleteById(navId);
    }

    @Override
    public Navigation updateNavigationByNavLoc(Integer navigationId, String navTitle) {
        Navigation navigation = navigationLoadPort.getByNavigationId(navigationId);
        navigation.setNavTitle(navTitle);
        return navigationRecordPort.save(navigation);
    }
}
