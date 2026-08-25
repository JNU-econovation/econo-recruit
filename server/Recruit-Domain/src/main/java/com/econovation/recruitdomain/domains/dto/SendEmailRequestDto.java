package com.econovation.recruitdomain.domains.dto;

import javax.validation.constraints.Email;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class SendEmailRequestDto {
    @Email private String email;
}
