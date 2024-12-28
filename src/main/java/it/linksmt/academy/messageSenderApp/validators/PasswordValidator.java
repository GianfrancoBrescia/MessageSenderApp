package it.linksmt.academy.messageSenderApp.validators;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordConstraint, Object> {

    String firstField;
    String secondField;

    @Override
    public void initialize(PasswordConstraint constraintAnnotation) {
        firstField = constraintAnnotation.firstfield();
        secondField = constraintAnnotation.secondfield();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        String firstValueToValidate = (String) new BeanWrapperImpl(value).getPropertyValue(firstField);
        String secondValueToValidate = (String) new BeanWrapperImpl(value).getPropertyValue(secondField);
        return firstValueToValidate.equals(secondValueToValidate);
    }
}
