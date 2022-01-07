package com.tis.in.BanX.validator;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.stereotype.Component;

import com.tis.in.BanX.exception.ValidationsFatalException;

@Component
public class CustomBeanValidatorImpl implements CustomBeanValidator {
    ValidatorFactory valdiatorFactory = null; 

    public CustomBeanValidatorImpl() {
        valdiatorFactory = Validation.buildDefaultValidatorFactory(); 
    }

    @Override
    public <T> void validateFields(T object) throws ValidationsFatalException {
        Validator validator = valdiatorFactory.getValidator(); 
        Set<ConstraintViolation<T>> failedValidations = validator.validate(object); 

        if (!failedValidations.isEmpty()) {
            List<String> allErrors = failedValidations.stream().map(failure -> failure.getMessage())
                    .collect(Collectors.toList());
            throw new ValidationsFatalException("Validation failure; Invalid request.", allErrors);
        }
    }
}