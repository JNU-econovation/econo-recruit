package com.econovation.recruit.adapter.in.controller.stomp;


import com.econovation.recruit.adapter.in.controller.stomp.Message;
import com.econovation.recruit.adapter.in.controller.stomp.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import java.security.Principal;

@Controller
@Slf4j
public class MessageController {

    @MessageMapping("/message")
    @SendTo("/topic/messages")
    public ResponseMessage getMessage(final Message message) throws InterruptedException {
        Thread.sleep(1000);
        log.info(message.getMessageContent());
        return new ResponseMessage(HtmlUtils.htmlEscape(message.getMessageContent()));
    }

    @MessageMapping("/private-message")
    @SendToUser("/topic/private-messages")
    public ResponseMessage getPrivateMessage(final Message message, Principal principal) {

        return new ResponseMessage(HtmlUtils.htmlEscape("Sending private Message To User"+ principal.getName() + ":"
                + message.getMessageContent()));

    }
}