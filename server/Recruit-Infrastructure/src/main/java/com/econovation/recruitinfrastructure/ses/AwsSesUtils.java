package com.econovation.recruitinfrastructure.ses;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.thymeleaf.spring5.SpringTemplateEngine;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.Body;
import software.amazon.awssdk.services.ses.model.Content;
import software.amazon.awssdk.services.ses.model.Message;
import software.amazon.awssdk.services.ses.model.RawMessage;
import software.amazon.awssdk.services.ses.model.SendRawEmailRequest;

@Component
@AllArgsConstructor
@Slf4j
public class AwsSesUtils {

    private final SesClient sesClient;
    private final SpringTemplateEngine templateEngine;

    private Message newMessage(String subject, String html) {
        Content content = Content.builder().data(subject).build();
        return Message.builder()
                .subject(content)
                .body(Body.builder().html(builder -> builder.data(html)).build())
                .build();
    }
    // The HTML body of the email.

    private static SendRawEmailRequest buildSendRawEmailRequest(MimeMessage message)
            throws IOException, MessagingException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        message.writeTo(outputStream);
        RawMessage rawMessage =
                RawMessage.builder()
                        .data(SdkBytes.fromByteBuffer(ByteBuffer.wrap(outputStream.toByteArray())))
                        .build();
        SendRawEmailRequest rawEmailRequest =
                SendRawEmailRequest.builder().rawMessage(rawMessage).build();
        return rawEmailRequest;
    }

    private void setAttachmentToMessage(
            MimeMultipart msg, RawEmailAttachmentDto rawEmailAttachmentDto) {
        try {
            MimeBodyPart att = new MimeBodyPart();
            DataSource fds =
                    new ByteArrayDataSource(
                            rawEmailAttachmentDto.getFileBytes(), rawEmailAttachmentDto.getType());
            att.setDataHandler(new DataHandler(fds));
            att.setFileName(rawEmailAttachmentDto.getFileName());
            msg.addBodyPart(att);
        } catch (Exception e) {
            log.info(e.toString());
            e.printStackTrace();
        }
    }
}
