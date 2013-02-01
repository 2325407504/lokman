package com.aripd.account.dto;

import static org.junit.Assert.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.Test;

import com.aripd.account.dto.AccountDto;

public class AccountDtoTest {

	private static Validator validator;

	@BeforeClass
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void test() {
		AccountDto bean = new AccountDto();

		Set<ConstraintViolation<AccountDto>> constraintViolations = validator
				.validate(bean);
		assertEquals(5, constraintViolations.size());
	}

}
