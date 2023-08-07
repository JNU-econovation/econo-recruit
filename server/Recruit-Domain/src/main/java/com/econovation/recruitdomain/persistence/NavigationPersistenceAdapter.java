// package com.econovation.recruit.adapter.out.persistence;
//
//
// import com.econovation.recruit.application.port.out.NavigationLoadPort;
// import com.econovation.recruit.application.port.out.NavigationRecordPort;
// import com.econovation.recruitdomain.domain.board.Navigation;
// import com.econovation.recruitdomain.domain.board.NavigationRepository;
// import java.util.List;
// import lombok.RequiredArgsConstructor;
// import org.springframework.stereotype.Component;
//
// @Component
// @RequiredArgsConstructor
// public class NavigationPersistenceAdapter implements NavigationLoadPort, NavigationRecordPort {
//    private static final String NOT_MATCH_NAVLOC_MESSAGE = "해당하는 navigation index가 없습니다";
//    private final NavigationRepository navigationRepository;
//
//    @Override
//    public Navigation getByNavLoc(Integer navLoc) {
//        return navigationRepository
//                .findNavigationByNavLoc(navLoc)
//                .orElseThrow(() -> new IllegalArgumentException(NOT_MATCH_NAVLOC_MESSAGE));
//    }
//
//    @Override
//    public List<Navigation> getAllNavigation() {
//        return navigationRepository.findAll();
//    }
//
//    @Override
//    public Navigation save(Navigation navigation) {
//        return navigationRepository.save(navigation);
//    }
//
//    @Override
//    public void deleteById(Integer navId) {
//        navigationRepository.deleteById(navId);
//    }
// }
