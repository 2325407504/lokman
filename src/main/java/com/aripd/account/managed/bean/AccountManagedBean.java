package com.aripd.account.managed.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.primefaces.model.LazyDataModel;

import com.aripd.account.domain.Account;
import com.aripd.account.service.IAccountService;

@ManagedBean(name = "accountMB")
@RequestScoped
public class AccountManagedBean implements Serializable {

	private static final long serialVersionUID = -9198450701472451939L;

	@ManagedProperty(value = "#{accountService}")
	private IAccountService accountService;

	private LazyDataModel<Account> lazyModel;
	private Account selectedAccount;
	private List<Account> accounts;

	@PostConstruct
    private void init() {
		lazyModel = new LazyAccountDataModel(accountService.getAll());
    }
	
	public void setAccountService(IAccountService accountService) {
		this.accountService = accountService;
	}

	public Account getSelectedAccount() {
		return selectedAccount;
	}

	public void setSelectedAccount(Account selectedAccount) {
		this.selectedAccount = selectedAccount;
	}

	public LazyDataModel<Account> getLazyModel() {
		return lazyModel;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public void setLazyModel(LazyDataModel<Account> lazyModel) {
		this.lazyModel = lazyModel;
	}

}