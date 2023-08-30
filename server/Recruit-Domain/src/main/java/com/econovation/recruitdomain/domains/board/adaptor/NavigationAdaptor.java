package com.econovation.recruitdomain.domains.board.adaptor;

import com.econovation.recruitcommon.annotation.Adaptor;
import com.econovation.recruitdomain.domains.board.domain.Navigation;
import com.econovation.recruitdomain.domains.board.domain.NavigationRepository;
import com.econovation.recruitdomain.domains.board.exception.BoardNotFoundException;
import com.econovation.recruitdomain.out.NavigationLoadPort;
import com.econovation.recruitdomain.out.NavigationRecordPort;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Adaptor
@RequiredArgsConstructor
public class NavigationAdaptor implements NavigationLoadPort, NavigationRecordPort {
    private final NavigationRepository navigationRepository;

    @Override
    public Navigation getByNavigationId(Integer navigationId) {
        return navigationRepository
                .findById(navigationId)
                .orElseThrow(() -> BoardNotFoundException.EXCEPTION);
    }

    @Override
    public List<Navigation> getAllNavigation() {
        return navigationRepository.findAll();
    }

    @Override
    public Navigation save(Navigation navigation) {
        return navigationRepository.save(navigation);
    }

    @Override
    public void deleteById(Integer navId) {
        navigationRepository.deleteById(navId);
    }
}
