package com.econovation.recruitdomain.domains.dto;

import com.econovation.recruitcommon.annotation.PasswordValidate;
import javax.validation.constraints.Email;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class SignUpRequestDto {
    private String name;
    private Integer year;
    @Email private String email;
    @PasswordValidate private String password;
}
