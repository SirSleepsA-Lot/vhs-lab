package vhslab.solution.API.exceptionHandler;

import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @Autowired
    private MessageSource messageSource;
    public GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        
        logger.error(ex.getMessage(), ex);

        String errorMessage = messageSource.getMessage("error.internalServerError",
                null, LocaleContextHolder.getLocale());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorMessage + ": " + ex.getMessage());
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException ex) {

        logger.error(ex.getMessage(), ex);
        String errorMessageSource = messageSource.getMessage("error.constraintViolationException",
                null, LocaleContextHolder.getLocale());

        StringBuilder errorMessage = new StringBuilder(errorMessageSource);
        ex.getConstraintViolations().forEach(violation ->
                errorMessage.append(String.format("%s %s, ", violation.getPropertyPath(), violation.getMessage())));

        // Remove the trailing comma and space
        errorMessage.delete(errorMessage.length() - 2, errorMessage.length());


        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage.toString());
    }
}
