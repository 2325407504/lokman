package com.aripd.account.validator;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.aripd.account.domain.Role;

@Service("roleValidator")
@Transactional
public class RoleValidator {

	public void validate(Role role, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "code", "required", "It is required!");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required", "It is required!");

		/*
		if (role.getCode() == null || role.getCode().length() == 0) {
			errors.rejectValue("code", "error.empty.code");
		}

		else if (role.getName() == null || role.getName().length() == 0) {
			errors.rejectValue("name", "error.empty.name");
		}
		*/
	}

}
