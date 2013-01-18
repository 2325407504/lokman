package com.aripd.validator;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.aripd.domain.User;
import com.aripd.service.UserService;

@Service("userValidator")
@Transactional
public class ProfileValidator {

	@Resource(name="userService")
	private UserService userService;
	
	public void validate(User user, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required", "It is required!");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "required", "It is required!");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required", "It is required!");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "required", "Password confirmation is required!");

		//if(!(user.getPassword().equals(user.getConfirmPassword()))){
			//errors.rejectValue("password", "notmatch.password");
		//}
		
		if (userService.hasUsername(user.getUsername())) {
			errors.rejectValue("username", "duplicate.username", "Duplicate value");
		}
		
		String email = user.getEmail();
        if (StringUtils.hasLength(email)) {
            EmailValidator emailValidator = new EmailValidator();
            if (!emailValidator.validate(user.getEmail()))
            	errors.rejectValue("email", "email.invalid", new Object[] { email },
                        "Invalid email format.");
        }
	}

}
