package com.aripd.validator;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.aripd.domain.User;

@Service("userValidator")
@Transactional
public class UserValidator {

	public boolean supports(Class clazz) {
		return User.class.isAssignableFrom(clazz);

	}

	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "required", "Username is required!");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required", "Password is required!");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "required", "Password confirmation is required!");

		User user = (User) target;
		
		//if(!(user.getPassword().equals(user.getConfirmPassword()))){
			//errors.rejectValue("password", "notmatch.password");
		//}
	}

}
