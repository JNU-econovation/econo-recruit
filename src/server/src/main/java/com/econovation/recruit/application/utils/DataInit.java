package com.econovation.recruit.application.utils;

import com.econovation.recruit.domain.applicant.Applicant;
import com.econovation.recruit.domain.applicant.ApplicantRepository;
import com.econovation.recruit.domain.board.Board;
import com.econovation.recruit.domain.board.BoardRepository;
import com.econovation.recruit.domain.board.Navigation;
import com.econovation.recruit.domain.board.NavigationRepository;
import com.econovation.recruit.domain.card.Card;
import com.econovation.recruit.domain.card.CardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

@Component
@Slf4j
@RequiredArgsConstructor
public class DataInit {
    private final NavigationRepository navigationRepository;
    private final BoardRepository boardRepository;
    private final CardRepository cardRepository;
    private final ApplicantRepository applicantRepository;
    @PostConstruct
    @Transactional
    public void initData() {
        Navigation navigation = Navigation.builder()
                .navTitle("공통")
                .navLoc(0)
                .build();
        navigationRepository.save(navigation);
        Navigation presidentNav = Navigation.builder()
                .navTitle("회장단")
                .navLoc(1)
                .build();
        navigationRepository.save(presidentNav);
        Navigation operationNav = Navigation.builder()
                .navTitle("운영팀")
                .navLoc(2)
                .build();
        navigationRepository.save(operationNav);
        Navigation promotedNav = Navigation.builder()
                .navTitle("홍보 및 디자인 팀")
                .navLoc(3)
                .build();
        navigationRepository.save(promotedNav);
        Navigation applicantMeetNav = Navigation.builder()
                .navTitle("지원자 대응팀")
                .navLoc(4)
                .build();
        navigationRepository.save(applicantMeetNav);
        Navigation otNav = Navigation.builder()
                .navTitle("OT 담당")
                .navLoc(5)
                .build();
        navigationRepository.save(otNav);
        Navigation etcNav = Navigation.builder()
                .navTitle("기타 담")
                .navLoc(6)
                .build();
        navigationRepository.save(etcNav);

        Board board = Board.builder()
                .navigation(navigation)
                .colLoc(0)
                .lowLoc(0)
                .colTitle("[개발자] 지원서")
                .build();
        boardRepository.save(board);

//        Applicant applicant = Applicant.builder()
//                .name("이서현")
//                .supportPath("인스타그램")
//                .portfolio("https://github.com/stove-smooth/sgs-smooth/tree/main/src/backend/chat")
//                .phoneNumber("010-1234-1234")
//                .studentId(100000)
//                .minor("산업공학과")
//                .major("소프트웨어공학과")
//                .grade(4)
//                .email("ymecca12@gmail.com")
//                //.commentCount()
//                .secondPriority(null)
//                .doubleMajor(null)
//                .hopeField("WEB")
//                .firstPriority("")
//                .plan("no plan is plan")
//                .semester(2).build();
//        applicantRepository.save(applicant);
    }



}
