package it.linksmt.academy.messageSenderApp.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BadWordValidator implements ConstraintValidator<BadWordConstraint, String> {

    String wordtofind;

    @Override
    public void initialize(BadWordConstraint constraintAnnotation) {
        wordtofind = constraintAnnotation.wordtofind();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !value.toLowerCase().contains(wordtofind);
    }
}
