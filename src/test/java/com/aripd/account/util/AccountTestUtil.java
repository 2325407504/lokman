package com.aripd.account.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aripd.account.domain.Account;
import com.aripd.account.dto.AccountDto;

public class AccountTestUtil {

	public static AccountDto createDTO(Long id, String firstName,
			String lastName, String email, String username, String password, String dateOfBirth) {
		
		AccountDto dto = new AccountDto();
		if (id != null) dto.setId(id);
		dto.setFirstName(firstName);
		dto.setLastName(lastName);
		dto.setEmail(email);
		dto.setUsername(username);
		dto.setPassword(password);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(dateOfBirth);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		dto.setDateOfBirth(date);
		
		return dto;
	}

	public static Account createModelObject(Long id, String firstName,
			String lastName, String email, String username, String password, String dateOfBirth) {
		
		Account model = new Account();
		if (id != null) model.setId(id);
		model.setFirstName(firstName);
		model.setLastName(lastName);
		model.setEmail(email);
		model.setUsername(username);
		model.setPassword(password);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(dateOfBirth);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		model.setDateOfBirth(date);
		
		return model;
	}

}
