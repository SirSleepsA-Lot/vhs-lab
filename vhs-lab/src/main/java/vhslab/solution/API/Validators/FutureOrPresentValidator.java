package vhslab.solution.API.Validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import vhslab.solution.API.Validators.Annotations.FutureOrPresentForDate;

import java.sql.Date;

public class FutureOrPresentValidator implements ConstraintValidator<FutureOrPresentForDate, Date> {

    @Override
    public void initialize(FutureOrPresentForDate constraintAnnotation) {
        // No initialization needed
    }

    @Override
    public boolean isValid(Date value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        Date now = new Date(System.currentTimeMillis());
        return value.after(now);
    }
}
