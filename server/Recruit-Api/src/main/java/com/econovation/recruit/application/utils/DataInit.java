/*
package com.econovation.recruit.application.utils;


@Component
@Slf4j
@RequiredArgsConstructor
public class DataInit {
    private final NavigationRepository navigationRepository;
    private final BoardRepository boardRepository;
    private final CardRepository cardRepository;
    private final ApplicantRepository applicantRepository;
    private final EntityManager em;

    @PostConstruct
    //    @Transactional
    public void initData() {
        Navigation navigation = Navigation.builder().navTitle("공통").navLoc(0).build();
        Navigation n1 = navigationRepository.save(navigation);
        Navigation presidentNav = Navigation.builder().navTitle("회장단").navLoc(1).build();
        navigationRepository.save(presidentNav);
        Navigation operationNav = Navigation.builder().navTitle("운영팀").navLoc(2).build();
        navigationRepository.save(operationNav);
        Navigation promotedNav = Navigation.builder().navTitle("홍보 및 디자인 팀").navLoc(3).build();
        navigationRepository.save(promotedNav);
        Navigation applicantMeetNav = Navigation.builder().navTitle("지원자 대응팀").navLoc(4).build();
        navigationRepository.save(applicantMeetNav);
        Navigation otNav = Navigation.builder().navTitle("OT 담당").navLoc(5).build();
        navigationRepository.save(otNav);
        Navigation etcNav = Navigation.builder().navTitle("기타 담당").navLoc(6).build();
        navigationRepository.save(etcNav);

        Board board =
                Board.builder().navigation(n1).colLoc(0).lowLoc(0).colTitle("[개발자] 지원서").build();
        Board b1 = boardRepository.save(board);

        Applicant applicant =
                Applicant.builder()
                        .name("이서현")
                        .supportPath("인스타그램")
                        .portfolio(
                                "https://github.com/stove-smooth/sgs-smooth/tree/main/src/backend/chat")
                        .phoneNumber("010-1234-1234")
                        .studentId(100001)
                        .minor("산업공학과")
                        .major("소프트웨어공학과")
                        .grade(4)
                        .email("ymecca12@gmail.com")
                        // .commentCount()
                        .secondPriority("-")
                        .doubleMajor("-")
                        .hopeField("WEB")
                        .firstPriority("")
                        .plan("no plan is plan")
                        .semester(2)
                        .build();
        Applicant save = applicantRepository.save(applicant);

        Card card = Card.builder().board(b1).workCardInfo("마감기한좀 늘려줘").applicant(null).build();
        cardRepository.save(card);

        Card card2 =
                Card.builder().board(b1).workCardInfo("장난 아니야 진심으로 말하는거야").applicant(null).build();
        Card c1 = cardRepository.save(card2);
    }
}
*/
