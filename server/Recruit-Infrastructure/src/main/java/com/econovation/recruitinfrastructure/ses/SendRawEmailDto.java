package com.econovation.recruitinfrastructure.ses;


import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SendRawEmailDto {
    // Replace sender@example.com with your "From" address.
    // This address must be verified with Amazon SES.
    private String senderAddress = "에코노베이션 Recruit팀 <recruit@econovation.com>";
    private String title;
    private String body;
    private List<RecipientForRequest> recipients;
}
