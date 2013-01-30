package com.aripd.account.service;

import java.util.List;

import com.aripd.account.domain.Account;

public interface AccountService {

	public Account getOne(Long id);
	
	public Account getOneByUsername(String username);
	
	public Account getActiveUser();
	
	public boolean hasUsername(String username);
	
	public List<Account> getAll();
	
	public Account save(Account account);
	
	public Account create(Account account);
	
	public Account update(Account account);
	
	public Account delete(Long id);
	
}
