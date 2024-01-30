package com.econovation.recruitinfrastructure.slack;

import com.econovation.recruitinfrastructure.slack.config.SlackProperties;
import com.slack.api.Slack;
import com.slack.api.webhook.Payload;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SlackMessageProvider {

    private final SlackProperties slackProperties;

    @Transactional
    public void sendMessage(String url, String text) {
        // 슬랙 url 이 null 일경우 안보냄.
        if (Objects.isNull(url)) return;
        try {
            doSend(url, text);
        } catch (Exception ignored) {
            log.error("SlackMessageProvider.sendMessage failed : ", ignored);
        }
    }

    /** 호스트가 존재하는 지 확인하기 위해 동기로 처리 */
    public void register(String url) throws UnknownHostException {
        final String text = "Recruit 슬랙 알림이 성공적으로 등록되었습니다!";
        doSend(url, text);
    }

    private void doSend(String url, String text) throws UnknownHostException {
        final Slack slack = Slack.getInstance();
        final Payload payload =
                Payload.builder()
                        .text(text)
                        .username(slackProperties.getUsername())
                        .iconUrl(slackProperties.getIconUrl())
                        .build();
        try {
            String responseBody = slack.send(url, payload).getBody();
            if (!StringUtils.equals(responseBody, "ok")) {
                throw new UnknownHostException("올바른 슬랙 URL이 아닙니다.");
            }
        } catch (UnknownHostException error) {
            // 호스트가 존재하지 않을 경우 abort
            throw error;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage());
        }
    }
}
