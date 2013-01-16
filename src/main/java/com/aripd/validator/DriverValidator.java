package com.aripd.validator;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.aripd.domain.Driver;

@Service("driverValidator")
@Transactional
public class DriverValidator {

	public boolean supports(Class clazz) {
		return Driver.class.isAssignableFrom(clazz);
	}
	
	public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstname", "required", "It is required!");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastname", "required", "It is required!");

        Driver driver = (Driver) target;		
	}

}
