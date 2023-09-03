package com.econovation.recruit.api.user.helper;

import com.econovation.recruitcommon.annotation.Helper;
import com.econovation.recruitcommon.exception.RecruitDynamicException;
import com.econovation.recruitinfrastructure.ncp.NcpClient;
import com.econovation.recruitinfrastructure.ncp.NcpProperties;
import com.econovation.recruitinfrastructure.ses.RecipientForRequest;
import com.econovation.recruitinfrastructure.ses.SendRawEmailDto;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.codec.binary.Base64;

@Helper
@RequiredArgsConstructor
public class NcpMailHelper {
    private final NcpProperties ncpProperties;
    private final NcpClient ncpClient;

    static String method = "POST"; // method
    static String space = " "; // space
    static String newLine = "\n"; // new line


    @SneakyThrows
    public void sendMail(String title, String body, String recipientAddress) {
        String timeStamp = String.valueOf(Instant.now().toEpochMilli());
        String signature = makeSignature(ncpProperties.getAccessKey(), ncpProperties.getSecretKey(), "/api/v1/mails", timeStamp);
        ncpClient.sendMail(ncpProperties.getAccessKey(), timeStamp,  signature, createSendRawEmailDto(title, body, recipientAddress));
    }
    public String makeSignature(
            String accessKey, String secretKey, String url, String timeStamp) {
        String result;
        try {
            String message =
                    new StringBuilder()
                            .append(method)
                            .append(space)
                            .append(url)
                            .append(newLine)
                            .append(timeStamp)
                            .append(newLine)
                            .append(accessKey)
                            .toString();

            SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(signingKey);

            byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
            String encodeBase64String = Base64.encodeBase64String(rawHmac);

            result = encodeBase64String;

        } catch (Exception ex) {
            throw new RecruitDynamicException(0, "400", ex.getMessage());
        }
        return result;
    }
    public SendRawEmailDto createSendRawEmailDto(String title, String body, String recipientAddress) {
        return SendRawEmailDto.builder()
                .senderAddress(ncpProperties.getSenderAddress())
                .title(title)
                .body(body)
                .recipients(createRecipientForRequest(List.of(recipientAddress)))
                .build();
    }
    public List<RecipientForRequest> createRecipientForRequest(List<String> recipientAddress) {
        List<RecipientForRequest> recipients = new java.util.ArrayList<>(Collections.emptyList());
        for (String address : recipientAddress) {
            recipients.add(RecipientForRequest.builder()
                    .address(address)
                    .build());
        }
        return recipients;
    }

}
