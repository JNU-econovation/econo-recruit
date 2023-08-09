package com.econovation.recruitcommon.annotation;

import java.lang.annotation.*;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface ExceptionDoc {
    @AliasFor(annotation = Component.class)
    String value() default "";
}
