package com.appgate.dtp.shared.utils;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;

import java.util.Set;
import java.util.stream.Collectors;

public interface SelfValidating {

    ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

    default <T> void validateSelf(final T objToValidate) {

            Set<ConstraintViolation<T>> constraintViolations =
                validatorFactory.getValidator().validate(objToValidate);
            if (constraintViolations.isEmpty())
                return;

            final var errors = constraintViolations.stream()
                .map(tConstraintViolation -> tConstraintViolation.getPropertyPath().toString() + " " + tConstraintViolation.getMessage())
                .collect(Collectors.joining(", ", "[", "]"));

            throw new ConstraintViolationException(errors, constraintViolations);
    }
}
