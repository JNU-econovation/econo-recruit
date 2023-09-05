package com.econovation.recruitdomain.domains.dto;

import lombok.Data;
import lombok.Getter;

@Data
public class EmailSendDto {
    private String email;

    private String applicantId;
}
