package com.aripd.account.helper;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

import com.aripd.account.domain.Account;
import com.aripd.account.domain.Role;
import com.aripd.common.helper.EntityHelper;
import com.aripd.common.support.ValueGenerator;

/**
 * Account Helper
 * 
 * @author maachou
 * 
 */
@Component
public class AccountHelper implements EntityHelper<Account> {

	private static final String PASSWORD_UI = "**********";

	@Override
	public Account copyFrom(final Account entity) {
		Account copy = new Account();
		//copy.setId(entity.getId());
		copy.setFirstName(entity.getFirstName());
		copy.setLastName(entity.getLastName());
		copy.setEmail(entity.getEmail());
		copy.setPassword(PASSWORD_UI);
		copy.setIsEnabled(entity.getIsEnabled());
		return copy;
	}

	@Override
	public Account copyWithoutPkFrom(final Account entity) {
		Account copy = new Account();
		copy.setFirstName(entity.getFirstName());
		copy.setLastName(entity.getLastName());
		copy.setEmail(entity.getEmail());
		copy.setPassword(PASSWORD_UI);
		copy.setIsEnabled(entity.getIsEnabled());
		return copy;
	}

	@Override
	public Account createEntityInstance() {
		return new Account();
	}

	@Override
	public Account createRandomEntity() {
		Account account = new Account();
		account.setFirstName(ValueGenerator.getMaxString(100));
		account.setLastName(ValueGenerator.getMaxString(100));
		account.setEmail(ValueGenerator.getUniqueEmail());
		account.setPassword(PASSWORD_UI);
		account.setIsEnabled(true);
		return account;
	}

	@Override
	public Account updateFrom(final Account fromEntity, Account toEntity) {
		toEntity.setFirstName(fromEntity.getFirstName());
		toEntity.setLastName(fromEntity.getLastName());
		if (!fromEntity.getPassword().equals(PASSWORD_UI)) {
			toEntity.setPassword(DigestUtils.md5Hex(fromEntity.getPassword()));
		}
		toEntity.setIsEnabled(fromEntity.getIsEnabled());
		return toEntity;
	}

	/**
	 * Getting the account role names.
	 * 
	 * @param account
	 * 
	 * @return list of role names
	 */
	public List<String> getCodes(final Account account) {
		List<String> roleNames = new ArrayList<String>();

		for (Role role : account.getRoles()) {
			roleNames.add(role.getCode());
		}
		return roleNames;
	}

}