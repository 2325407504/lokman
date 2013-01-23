package com.aripd.account.service;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aripd.account.domain.Account;
import com.aripd.account.repository.AccountRepository;

@Service("accountService")
@Transactional
public class AccountService {

	protected static Logger logger4J = Logger.getLogger(AccountService.class);

	@Autowired
	private AccountRepository repository;

	public Account getOne(Long id) {
		logger4J.debug("Retrieving person based on his id");
		return repository.findOne(id);
	}

	public Account getOneByUsername(String username) {
		logger4J.debug("Retrieving person based on his username");
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
		logger4J.debug("Retrieving all persons");
		return repository.findAll();
	}

	public Boolean save(Account account) {
		//User existing = repository.findOneByUsername(account.getUsername());
		//if (existing != null)
			//return false;

		Account saved = repository.save(account);
		if (saved == null)
			return false;

		logger4J.debug("Adding new person");
		return true;
	}

	public Boolean update(Account account) {
		Account existing = repository.findOneByUsername(account.getUsername());
		if (existing == null)
			return false;

		existing.setFirstName(account.getFirstName());
		existing.setLastName(account.getLastName());

		Account saved = repository.save(existing);
		if (saved == null)
			return false;

		logger4J.debug("Editing existing person");
		return true;
	}

	public void delete(Long id) {
		repository.delete(repository.findOne(id));
	}

	public Boolean delete(Account account) {
		Account existing = repository.findOne(account.getId());
		if (existing == null)
			return false;

		repository.delete(existing);
		Account deleted = repository.findOne(account.getId());
		if (deleted != null)
			return false;

		logger4J.debug("Deleting existing person");
		return true;
	}

}
