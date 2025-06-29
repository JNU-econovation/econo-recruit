package com.econovation.recruitdomain.domains.dto;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class VerifyCodeRequestDto {
    @Email
    private String email;
    private String code;

    @AssertTrue(message = "인증 코드는 100000에서 999999 사이의 6자리 숫자여야 합니다")
    public boolean isCodeValid() {
        if (code == null) {
            return false;
        }
        try {
            int codeValue = Integer.parseInt(code);
            return codeValue >= 100000 && codeValue <= 999999;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
