package vhslab.solution.API.Validators.Annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import vhslab.solution.API.Validators.PastOrPresentValidator;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PastOrPresentValidator.class)
@Documented
public @interface PastOrPresentForDate {
    String message() default "Date must be in the past or present";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}