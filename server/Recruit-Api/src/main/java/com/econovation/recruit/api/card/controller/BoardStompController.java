package com.econovation.recruit.api.card.controller;

import com.econovation.recruit.api.card.usecase.BoardLoadUseCase;
import com.econovation.recruitdomain.domains.board.domain.Board;
import com.econovation.recruitdomain.domains.card.domain.Card;
import com.econovation.recruitdomain.domains.model.BoardMessage;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Slf4j
@Tag(name = "[2.0]. 칸반보드 API", description = "칸반보드 관련 API")
public class BoardStompController {
    private final BoardLoadUseCase boardLoadUseCase;
    private final SimpMessagingTemplate template; // 특정 Broker로 메세지를 전달

    // Client 가 SEND 할 수 있는 경로
    // stompConfig 에서 설정한 applicationDestinationPrefixes 와 @MessageMapping 경로가 병합됨
    // "/pub/chat/enter"
    @MessageMapping(value = "/boards/message")
    public void enter(Card message) {
        List<Board> cards = boardLoadUseCase.findAllByNavigationId(0);
        //        message.setMessage(cards.toString());
        template.convertAndSend("/sub/board", cards);
        log.info("들어온 메세지 : " + message);
        ////      채팅이력 저장하기
        //        Map<String, Integer> newestLocationByNavLocAndColLoc =
        // boardUseCase.getNewestLocationByNavLocAndColLoc(message.getNavLoc(),
        // message.getColLoc());
        //        Board board =
        // boardUseCase.createWorkBoard(message.getMessage(),message.getNavLoc(),
        //                newestLocationByNavLocAndColLoc.get("colLoc"),
        // newestLocationByNavLocAndColLoc.get("lowLoc"));
    }

    // 업무카드 생성
    @MessageMapping(value = "/boards/cards")
    public void createCard(BoardMessage message) {
        // DB에 채팅내용 저장
        //        Map<String, Integer> newestLocationByNavLocAndColLoc =
        //                boardLoadUseCase.getNewestLocationByNavLocAndColLoc(
        //                        message.getNavLoc(), message.getColLoc());
        //        Board board =
        //                boardLoadUseCase.createWorkBoard(
        //                        message.getMessage(),
        //                        message.getNavLoc(),
        //                        newestLocationByNavLocAndColLoc.get("colLoc"),
        //                        newestLocationByNavLocAndColLoc.get("lowLoc"));
        //        template.convertAndSend("/sub/board", board);
        //        System.out.println("들어온 메세지 : " + message);
    }

    // 업무카드 위치 변경
    /*    @MessageMapping(value = "/boards/message")
    public void createCard(BoardMessage message) {
                    template.convertAndSend("/sub/board", message);
                    System.out.println("들어온 메세지 : "+message);
                    // DB에 채팅내용 저장
                    Map<String, Integer> newestLocationByNavLocAndColLoc = boardUseCase.getNewestLocationByNavLocAndColLoc(message.getNavLoc(), message.getColLoc());
                    Board board = boardUseCase.createWorkBoard(message.getMessage(),message.getNavLoc(),
                                                    newestLocationByNavLocAndColLoc.get("colLoc"), newestLocationByNavLocAndColLoc.get("lowLoc"));
    }*/

}
