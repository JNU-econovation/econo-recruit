package com.econovation.recruitinfrastructure.ses;


import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SendRawEmailDto {
    private String senderAddress;
    private String title;
    private String body;
    private List<RecipientForRequest> recipients;
}