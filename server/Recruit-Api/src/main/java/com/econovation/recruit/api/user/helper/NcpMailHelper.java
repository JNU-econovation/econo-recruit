package com.econovation.recruit.api.user.helper;

import com.econovation.recruitcommon.annotation.Helper;
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
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@Helper
@RequiredArgsConstructor
public class NcpMailHelper {
    private final NcpProperties ncpProperties;
    private final NcpClient ncpClient;

    @SneakyThrows
    public void sendMail(String title, String body, String recipientAddress) {
        String timeStamp = String.valueOf(Instant.now().toEpochMilli());
        ncpClient.sendMail(timeStamp, ncpProperties.getAccessKey(), makeSignature(timeStamp),createSendRawEmailDto(title, body, recipientAddress));
    }
    public String makeSignature(String timeStamp) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
        String space = " ";  // 공백
        String newLine = "\n";  // 줄바꿈
        String method = "POST";  // HTTP 메소드
        String url = "/api/v1/mails";  // 도메인을 제외한 "/" 아래 전체 url (쿼리스트링 포함)
        String timestamp = timeStamp;  // 현재 타임스탬프 (epoch, millisecond)
        String accessKey = ncpProperties.getAccessKey();  // access key id (from portal or sub account)
        String secretKey = ncpProperties.getSecretKey();  // secret key (from portal or sub account)

        String message = new StringBuilder()
                .append(method)
                .append(space)
                .append(url)
                .append(newLine)
                .append(timestamp)
                .append(newLine)
                .append(accessKey)
                .toString();

        SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(signingKey);

        byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
        String encodeBase64String = Base64.getEncoder().encodeToString(rawHmac);

        return encodeBase64String;

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
