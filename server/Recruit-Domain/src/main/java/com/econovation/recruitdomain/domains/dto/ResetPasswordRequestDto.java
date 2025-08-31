package com.econovation.recruitdomain.domains.dto;

import com.econovation.recruitcommon.annotation.PasswordValidate;
import javax.validation.constraints.Email;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class ResetPasswordRequestDto {
    @Email private String email;
    @PasswordValidate private String password;
}
