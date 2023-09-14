package com.econovation.recruitcommon.annotation;

import com.econovation.recruitcommon.aspect.PasswordValidateAspect;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = PasswordValidateAspect.class)
@Target({
    //        ElementType.METHOD,
    ElementType.FIELD,
    ElementType.PARAMETER,
})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordValidate {
    String message() default "Invalid password";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
