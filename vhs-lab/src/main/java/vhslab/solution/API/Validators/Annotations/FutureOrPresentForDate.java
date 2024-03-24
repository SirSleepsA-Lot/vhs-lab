package vhslab.solution.API.Validators.Annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import vhslab.solution.API.Validators.FutureOrPresentValidator;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FutureOrPresentValidator.class)
@Documented
public @interface FutureOrPresentForDate {
    String message() default "Date must be in the past or present";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
