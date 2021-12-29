package com.tis.in.BanX.validator;

import java.lang.annotation.Documented;
import static java.lang.annotation.ElementType.*;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = { ContainsKeysValidator.class })
public @interface ContainsKeys {
	String message() default "Invalid Map Keys";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String[] value() default {};
}