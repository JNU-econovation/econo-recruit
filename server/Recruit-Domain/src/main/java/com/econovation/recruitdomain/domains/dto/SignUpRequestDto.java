package com.econovation.recruitdomain.domains.dto;

import com.econovation.recruitcommon.annotation.PasswordValidate;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class SignUpRequestDto {
    @NotBlank private String name;
    @NotBlank private Integer year;
    @Email private String email;
    @PasswordValidate private String password;
}
