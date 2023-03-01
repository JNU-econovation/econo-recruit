package com.econovation.recruit.application.service;

import com.econovation.recruit.application.port.in.NavigationUseCase;
import com.econovation.recruit.application.port.out.NavigationLoadPort;
import com.econovation.recruit.application.port.out.NavigationRecordPort;
import com.econovation.recruit.domain.board.Board;
import com.econovation.recruit.domain.board.Navigation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class NavigationService implements NavigationUseCase {
    private final NavigationLoadPort navigationLoadPort;
    private final NavigationRecordPort navigationRecordPort;
    @Override
    public Navigation createNavigation(String navTitle) {
        Integer navLoc = getNewestNavLoc();
        Navigation navigation = Navigation.builder()
                .navTitle(navTitle)
                .navLoc(navLoc)
                .build();
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

    private Integer getNewestNavLoc() {
        //hopeField 에 일치하는 board List
        List<Navigation> navigations = navigationLoadPort.getAllNavigation();
        Navigation navigation = navigations.stream()
                .max(Comparator.comparing(Navigation::getNavLoc))
                .orElseThrow(NoSuchFieldError::new);
        return navigation.getNavLoc();
    }

}
