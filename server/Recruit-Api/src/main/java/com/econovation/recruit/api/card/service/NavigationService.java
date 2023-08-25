package com.econovation.recruit.api.card.service;

import com.econovation.recruit.api.card.usecase.NavigationUseCase;
import com.econovation.recruitdomain.domains.board.domain.Navigation;
import com.econovation.recruitdomain.out.NavigationLoadPort;
import com.econovation.recruitdomain.out.NavigationRecordPort;
import java.util.Comparator;
import java.util.List;
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
    public Navigation updateNavigationByNavLoc(Integer navLoc, String navTitle) {
        Navigation navigation = navigationLoadPort.getByNavLoc(navLoc);
        navigation.setNavTitle(navTitle);
        return navigationRecordPort.save(navigation);
    }
}
