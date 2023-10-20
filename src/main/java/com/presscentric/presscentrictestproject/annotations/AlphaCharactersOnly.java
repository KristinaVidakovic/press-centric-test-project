package com.presscentric.presscentrictestproject.annotations;

import com.presscentric.presscentrictestproject.annotations.validators.AlphaCharactersOnlyValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AlphaCharactersOnlyValidator.class)
public @interface AlphaCharactersOnly {
    String message() default "Attribute must contain only alphabetical characters";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
