package com.aripd.validator;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.aripd.domain.Role;

@Service("roleValidator")
@Transactional
public class RoleValidator {

	public boolean supports(Class clazz) {
		return Role.class.isAssignableFrom(clazz);

	}

	public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "code", "required", "Code is required!");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required", "Name is required!");

		Role role = (Role) target;
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
