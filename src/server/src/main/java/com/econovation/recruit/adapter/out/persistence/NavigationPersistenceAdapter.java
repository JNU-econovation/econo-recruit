package com.econovation.recruit.adapter.out.persistence;

import com.econovation.recruit.application.port.out.NavigationLoadPort;
import com.econovation.recruit.domain.board.Navigation;
import com.econovation.recruit.domain.board.NavigationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class NavigationPersistenceAdapter implements NavigationLoadPort {
    private static final String NOT_MATCH_NAVLOC_MESSAGE = "해당하는 navigation index가 없습니다";
    private final NavigationRepository navigationRepository;

    @Override
    public Navigation getByNavLoc(Integer navLoc) {
        return navigationRepository.findNavigationByNavLoc(navLoc)
                .orElseThrow(() -> new IllegalArgumentException(NOT_MATCH_NAVLOC_MESSAGE));
    }
    @Override
    public List<Navigation> getAllNavigation() {
        return navigationRepository.findAll();
    }
}
