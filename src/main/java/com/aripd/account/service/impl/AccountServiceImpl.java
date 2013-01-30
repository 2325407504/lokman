package com.aripd.account.service.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aripd.account.domain.Account;
import com.aripd.account.repository.AccountRepository;
import com.aripd.account.service.AccountService;

@Service("accountService")
@Transactional
public class AccountServiceImpl implements AccountService {

	protected static Logger logger = Logger.getLogger(AccountServiceImpl.class);

	@Autowired
	private AccountRepository repository;

	public Account getOne(Long id) {
		logger.debug("Retrieving person based on his id");
		return repository.findOne(id);
	}

	public Account getOneByUsername(String username) {
		logger.debug("Retrieving person based on his username");
		return repository.findOneByUsername(username);
	}

	@PreAuthorize("isFullyAuthenticated()")
	public Account getActiveUser() {
		org.springframework.security.core.userdetails.User securityUser = (org.springframework.security.core.userdetails.User) (SecurityContextHolder.getContext()).getAuthentication().getPrincipal();
		return getOneByUsername(securityUser.getUsername());
	}

	@PreAuthorize("isFullyAuthenticated()")
	public boolean hasUsername(String username) {
		List<Account> list = repository.findAll();
		list.remove(getActiveUser());
		
		Iterator<Account> iterator = list.iterator();
		while (iterator.hasNext()) {
			if (iterator.next().getUsername().equals(username)) {
				return true;
			}
		}
		return false;
	}

	public List<Account> getAll() {
		logger.debug("Retrieving all persons");
		return repository.findAll();
	}

	public Account save(Account account) {
		account.setPassword(DigestUtils.md5Hex(account.getPassword()));
		return repository.save(account);
	}

	public Account update(Account account) {
		Account existing = repository.findOne(account.getId());

		existing.setIsEnabled(account.getIsEnabled());
		existing.setFirstName(account.getFirstName());
		existing.setLastName(account.getLastName());
		existing.setPassword(DigestUtils.md5Hex(account.getPassword()));

		return repository.save(existing);
	}

	public Account delete(Long id) {
		Account account = repository.findOne(id);
		repository.delete(id);
		return account;
	}

	public Account create(Account account) {
		return repository.save(account);
	}

}
