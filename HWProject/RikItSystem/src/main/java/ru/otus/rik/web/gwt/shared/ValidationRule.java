package ru.otus.rik.web.gwt.shared;

import com.google.gwt.validation.client.impl.Validation;
import ru.otus.rik.web.gwt.shared.model.LoginFormDTO;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

public class ValidationRule {
    public static boolean isValid(LoginFormDTO loginForm) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<LoginFormDTO>> violations = validator.validate(loginForm);
        return violations.isEmpty();
    }
}
