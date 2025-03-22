package com.econovation.recruit.api.sms.helper;

import com.econovation.recruitinfrastructure.ncp.NcpProperties;
import com.econovation.recruitinfrastructure.ncp.NcpSmsDto;
import com.econovation.recruitinfrastructure.ncp.NcpSmsResponse;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import static com.econovation.recruitinfrastructure.ncp.NcpClients.*;

@Component
@RequiredArgsConstructor
public class NcpSmsSender {

    private final NcpProperties ncpProperties;
    private final NcpSmsClient smsClient;

    public boolean sendSms(String message, String... phoneNumber) {
        String timeStamp = String.valueOf(Instant.now().toEpochMilli());
        String signature =
                makeSignature(
                        timeStamp, ncpProperties.getAccessKey(), ncpProperties.getSecretKey());
        NcpSmsResponse response = smsClient.createSmsRequest(
                ncpProperties.getAccessKey(),
                timeStamp,
                signature,
                NcpSmsDto.builder()
                        .from("010-3066-5016")
                        .type("SMS")
                        .subject("[Econovation-Recruit]")
                        .content(message)
                        .messages(Arrays.stream(phoneNumber).map(NcpSmsDto.Message::new).toList())
                        .build()
                )
                .getBody();


        if(response == null || response.getStatusCode().equals("202")) {
            return false;
        }

        return true;
    }

    public boolean sendSmsWithSubject(String subject, String message, String... phoneNumber) {
        String timeStamp = String.valueOf(Instant.now().toEpochMilli());
        String signature =
                makeSignature(
                        timeStamp, ncpProperties.getAccessKey(), ncpProperties.getSecretKey());
        NcpSmsResponse response = smsClient.createSmsRequest(
                ncpProperties.getAccessKey(),
                timeStamp,
                signature,
                NcpSmsDto.builder()
                        .from("010-3066-5016")
                        .subject(subject)
                        .type("SMS")
                        .content(message)
                        .messages(Arrays.stream(phoneNumber).map(NcpSmsDto.Message::new).toList())
                        .build()
                )
                .getBody();

        if(response != null && response.getStatusCode().equals("202")) {
            return false;
        }

        return true;
    }

    private String makeSignature(String timeStamp, String accessKey, String secretKey) {
        String space = " "; // 공백
        String newLine = "\n"; // 줄바꿈
        String method = "POST"; // HTTP 메소드
        String url = "/sms/v2/services/ncp:sms:kr:324868537230:kjm/messages"; // 도메인을 제외한 "/" 아래 전체 url (쿼리스트링 포함)
        String message =
                        method
                        + space
                        + url
                        + newLine
                        + timeStamp
                        + newLine
                        + accessKey;

        try {
            SecretKey signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");

            mac.init(signingKey);

            byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));

            return Base64.getEncoder().encodeToString(rawHmac);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("암호화 알고리즘을 찾을 수 없음",e);
        } catch (InvalidKeyException e){
            throw new IllegalArgumentException("올바르지 않은 SigningKey",e);
        } catch (UnsupportedEncodingException e){
            throw new IllegalArgumentException("지원하지 않는 인코딩 방식",e);
        }
    }

}
