package com.aripd.account.web.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import com.aripd.account.constraint.FieldMatch;

@FieldMatch.List({
		@FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match"),
		@FieldMatch(first = "email", second = "confirmEmail", message = "The email fields must match") })
public class RegistrationForm {
	@NotNull
	@Size(min = 8, max = 25)
	private String password;

	@NotNull
	@Size(min = 8, max = 25)
	private String confirmPassword;

	@NotNull
	@Email
	private String email;

	@NotNull
	@Email
	private String confirmEmail;
}