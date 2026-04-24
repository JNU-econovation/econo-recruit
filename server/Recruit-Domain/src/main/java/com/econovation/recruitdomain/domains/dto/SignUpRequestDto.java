package com.econovation.recruitdomain.domains.dto;

import com.econovation.recruitcommon.annotation.PasswordValidate;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class SignUpRequestDto {
    @NotBlank private String name;
    @NotNull private Integer year;
    @Email private String email;
    @PasswordValidate private String password;
}
