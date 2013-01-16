package com.aripd.validator;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.aripd.domain.User;
import com.aripd.service.UserService;

@Service("userValidator")
@Transactional
public class UserValidator {

	@Resource(name="userService")
	private UserService userService;
	
	public boolean supports(Class clazz) {
		return User.class.isAssignableFrom(clazz);

	}

	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "required", "It is required!");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required", "It is required!");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "required", "Password confirmation is required!");

		User user = (User) target;
		
		//if(!(user.getPassword().equals(user.getConfirmPassword()))){
			//errors.rejectValue("password", "notmatch.password");
		//}
		
		if (userService.hasUsername(user.getUsername())) {
			errors.rejectValue("username", "duplicate.username", "Duplicate value");
		}
		
	}

}
