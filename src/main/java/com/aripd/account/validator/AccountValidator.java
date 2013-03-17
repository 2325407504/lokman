package com.aripd.account.validator;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.aripd.account.domain.Account;
import com.aripd.account.service.AccountService;
import com.aripd.common.validator.EmailValidator;

@Service("accountValidator")
@Transactional
public class AccountValidator {

	@Resource(name="accountService")
	private AccountService accountService;
	
	public void validate(Account account, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required", "It is required!");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "required", "It is required!");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required", "It is required!");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "required", "Password confirmation is required!");

		//if(!(account.getPassword().equals(account.getConfirmPassword()))){
			//errors.rejectValue("password", "notmatch.password");
		//}
		
		if (accountService.hasUsername(account.getUsername())) {
			errors.rejectValue("username", "duplicate.username", "Duplicate value");
		}
		
		String email = account.getEmail();
        if (StringUtils.hasLength(email)) {
            EmailValidator emailValidator = new EmailValidator();
            if (!emailValidator.validate(account.getEmail()))
            	errors.rejectValue("email", "email.invalid", new Object[] { email },
                        "Invalid email format.");
        }
	}

}
