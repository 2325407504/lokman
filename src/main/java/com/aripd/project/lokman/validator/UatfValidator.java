package com.aripd.project.lokman.validator;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.aripd.project.lokman.domain.Uatf;

@Service("uatfValidator")
@Transactional
public class UatfValidator {

	public void validate(Uatf uatf, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "code", "required", "It is required!");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "company", "required", "It is required!");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "county", "required", "It is required!");	
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", "required", "It is required!");	
	}

}
