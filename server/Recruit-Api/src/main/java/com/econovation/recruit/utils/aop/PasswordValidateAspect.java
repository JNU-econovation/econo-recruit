package com.econovation.recruit.utils.aop;

import com.econovation.recruitcommon.annotation.PasswordValidate;
import com.econovation.recruitdomain.domains.interviewer.exception.InvalidPasswordException;
import java.lang.reflect.Field;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PasswordValidateAspect {

    @Around("@annotation(com.econovation.recruitcommon.annotation.PasswordValidate)")
    public Object validatePasswordField(ProceedingJoinPoint joinPoint) throws Throwable {
        Object target = joinPoint.getTarget();
        Class<?> targetClass = target.getClass();

        Field[] fields = targetClass.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(PasswordValidate.class)) {
                field.setAccessible(true);
                Object fieldValue = field.get(target);
                if (fieldValue instanceof String) {
                    String password = (String) fieldValue;
                    if (!isValidPassword(password)) {
                        throw InvalidPasswordException.EXCEPTION;
                    }
                }
            }
        }
        return joinPoint.proceed();
    }

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
}
