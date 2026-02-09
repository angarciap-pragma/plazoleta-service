package com.plazoleta.service.infrastructure.adapter.in.web.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ContainsLetterValidator.class)
@Documented
public @interface ContainsLetter {

	String message() default "El nombre debe contener al menos una letra.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
