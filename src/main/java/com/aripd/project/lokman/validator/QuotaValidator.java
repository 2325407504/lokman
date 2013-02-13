package com.aripd.project.lokman.validator;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.aripd.project.lokman.domain.Quota;

@Service("quotaValidator")
@Transactional
public class QuotaValidator {

	public void validate(Quota quota, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required", "It is required!");
	}

}
