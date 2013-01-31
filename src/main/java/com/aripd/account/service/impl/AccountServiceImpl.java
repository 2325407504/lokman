package com.aripd.account.service.impl;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aripd.account.domain.Account;
import com.aripd.account.dto.AccountDto;
import com.aripd.account.exception.AccountNotFoundException;
import com.aripd.account.repository.AccountRepository;
import com.aripd.account.service.AccountService;

/**
 * This implementation of the AccountService interface communicates with the
 * database by using a Spring Data JPA repository.
 * 
 * @author aripd.com
 */
@Service("accountService")
@Transactional
public class AccountServiceImpl implements AccountService {

	protected static Logger logger = Logger.getLogger(AccountServiceImpl.class);

	@Resource
	private AccountRepository repository;

	/**
	 * This setter method should be used only by unit tests.
	 * 
	 * @param repository
	 */
	protected void setAccountRepository(AccountRepository repository) {
		this.repository = repository;
	}

	public Account getOne(Long id) {
		logger.debug("Retrieving account based on id");
		return repository.findOne(id);
	}

	public Account getOneByUsername(String username) {
		logger.debug("Retrieving account based on username");
		return repository.findOneByUsername(username);
	}

	@PreAuthorize("isFullyAuthenticated()")
	public Account getActiveUser() {
		org.springframework.security.core.userdetails.User securityUser = (org.springframework.security.core.userdetails.User) (SecurityContextHolder
				.getContext()).getAuthentication().getPrincipal();
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
		logger.debug("Retrieving all accounts");
		return repository.findAll();
	}

	public Account save(AccountDto formData) throws AccountNotFoundException {
		if (formData.getId() == null)
			return this.create(formData);
		else
			return this.update(formData);
	}

	@Transactional
	@Override
	public Account create(AccountDto created) {
		logger.debug("Creating a new account with information: " + created);

		Account account = Account.getBuilder(created.getFirstName(),
				created.getLastName(), created.getEmail(),
				created.getUsername(), created.getPassword(),
				created.getDateOfBirth()).build();

		return repository.save(account);
	}

	@Transactional(rollbackFor = AccountNotFoundException.class)
	@Override
	public Account delete(Long id) throws AccountNotFoundException {
		logger.debug("Deleting account with id: " + id);

		Account deleted = repository.findOne(id);

		if (deleted == null) {
			logger.debug("No accoutn found with id: " + id);
			throw new AccountNotFoundException();
		}

		repository.delete(deleted);
		return deleted;
	}

	@Transactional(readOnly = true)
	@Override
	public List<Account> findAll() {
		logger.debug("Finding all accounts");
		return repository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Account findById(Long id) {
		logger.debug("Finding account by id: " + id);
		return repository.findOne(id);
	}

	@Transactional(rollbackFor = AccountNotFoundException.class)
	@Override
	public Account update(AccountDto updated) throws AccountNotFoundException {
		logger.debug("Updating account with information: " + updated);

		Account account = repository.findOne(updated.getId());

		if (account == null) {
			logger.debug("No account found with id: " + updated.getId());
			throw new AccountNotFoundException();
		}

		account.update(updated.getFirstName(), updated.getLastName(), updated.getEmail(), updated.getUsername(), updated.getPassword(), updated.getDateOfBirth());

		return account;
	}

}
