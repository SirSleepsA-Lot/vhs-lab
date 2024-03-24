package vhslab.solution.API.Validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import vhslab.solution.API.Validators.Annotations.PastOrPresentForDate;

import java.sql.Date;

public class PastOrPresentValidator implements ConstraintValidator<PastOrPresentForDate, Date> {

    @Override
    public void initialize(PastOrPresentForDate constraintAnnotation) {
        // No initialization needed
    }

    @Override
    public boolean isValid(Date value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        Date now = new Date(System.currentTimeMillis());
        return !value.after(now);
    }
}