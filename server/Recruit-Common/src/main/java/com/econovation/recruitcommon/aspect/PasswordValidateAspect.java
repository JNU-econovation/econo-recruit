package com.econovation.recruitcommon.aspect;

import com.econovation.recruitcommon.annotation.PasswordValidate;
import com.econovation.recruitcommon.exception.InvalidPasswordException;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PasswordValidateAspect implements ConstraintValidator<PasswordValidate, String>  {

    private boolean isValidPassword(String password) {
        if (password == null || password.length() < 10) {
            return false; // 비밀번호 길이가 10글자 미만인 경우 검증 실패
        }

        boolean hasDigit = false;
        boolean hasSpecialChar = false;

        for (char ch : password.toCharArray()) {
            if (Character.isDigit(ch)) {
                hasDigit = true;
            } else if (isSpecialCharacter(ch)) {
                hasSpecialChar = true;
            }
        }

        return hasDigit && hasSpecialChar;
    }

    private boolean isSpecialCharacter(char ch) {
        // 특수 문자 여부를 확인하는 로직을 구현
        // 예를 들어, 일부 특수 문자를 확인할 수 있습니다.
        return ch == '@' || ch == '#' || ch == '!' || ch == '$' || ch == '%' || ch == '^'
                || ch == '&' || ch == '*';
    }

    @Override
    public void initialize(PasswordValidate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (!isValidPassword(value)) {
            throw InvalidPasswordException.EXCEPTION;
        }
        return true;
    }
}
