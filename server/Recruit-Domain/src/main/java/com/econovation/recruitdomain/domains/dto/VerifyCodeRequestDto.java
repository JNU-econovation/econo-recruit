package com.econovation.recruitdomain.domains.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class VerifyCodeRequestDto {
    @Email private String email;
    private String code;

    @AssertTrue(message = "인증 코드는 100000에서 999999 사이의 6자리 숫자여야 합니다")
    private boolean isCodeValid() {
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
