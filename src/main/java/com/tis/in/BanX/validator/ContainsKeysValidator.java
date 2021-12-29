package com.tis.in.BanX.validator;

import java.util.Map;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ContainsKeysValidator implements ConstraintValidator<ContainsKeys, Map<?, ?>> {

	String[] requiredKeys;

	@Override
	public void initialize(ContainsKeys constraintAnnotation) {
		requiredKeys = constraintAnnotation.value();
	}

	@Override
	public boolean isValid(Map<?, ?> value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}
		
		for (String requiredKey : requiredKeys) {
			System.out.println(requiredKey);

			if (!value.containsKey(requiredKey)) {
				return false;
			}
		}

		return true;
	}
}