package com.aripd.account.service;

import java.util.List;

import com.aripd.account.domain.Account;
import com.aripd.account.dto.AccountDto;
import com.aripd.account.exception.AccountNotFoundException;

/**
 * Declares methods used to obtain and modify account information.
 * 
 * @author aripd.com
 */
public interface AccountService {

	public Account getOne(Long id);

	public Account getOneByUsername(String username);

	public Account getActiveUser();

	public boolean hasUsername(String username);

	public List<Account> getAll();

	public Account save(AccountDto formData) throws AccountNotFoundException;

	/**
	 * Creates a new account.
	 * 
	 * @param created
	 *            The information of the created account.
	 * @return The created account.
	 */
	public Account create(AccountDto created);

	/**
	 * Deletes an account.
	 * 
	 * @param id
	 *            The id of the deleted account.
	 * @return The deleted account.
	 * @throws AccountNotFoundException
	 *             if no account is found with the given id.
	 */
	public Account delete(Long id) throws AccountNotFoundException;

	/**
	 * Finds all accounts.
	 * 
	 * @return A list of accounts.
	 */
	public List<Account> findAll();

	/**
	 * Finds account by id.
	 * 
	 * @param id
	 *            The id of the wanted account.
	 * @return The found account. If no account is found, this method returns
	 *         null.
	 */
	public Account findById(Long id);

	/**
	 * Updates the information of an account.
	 * 
	 * @param updated
	 *            The information of the updated account.
	 * @return The updated account.
	 * @throws AccountNotFoundException
	 *             if no person is found with given id.
	 */
	public Account update(AccountDto account) throws AccountNotFoundException;

}
