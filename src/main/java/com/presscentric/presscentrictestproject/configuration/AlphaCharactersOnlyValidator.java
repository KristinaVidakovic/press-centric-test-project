package com.presscentric.presscentrictestproject.configuration;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AlphaCharactersOnlyValidator implements ConstraintValidator<AlphaCharactersOnly, String> {
    @Override
    public void initialize(AlphaCharactersOnly constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        return value.matches("^[a-zA-Z]*$");
    }
}
