package it.linksmt.academy.messageSenderApp.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BadWordValidator.class)
public @interface BadWordConstraint {

    String message() default "Non si dicono parolacce";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String wordtofind() default "cacca";
}
