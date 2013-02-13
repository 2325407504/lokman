package com.aripd.account.managed.bean;

import java.util.Comparator;

import org.primefaces.model.SortOrder;

import com.aripd.account.domain.Account;

public class LazyAccountSorter implements Comparator<Account> {

	private String sortField;
	private SortOrder sortOrder;

	public LazyAccountSorter(String sortField, SortOrder sortOrder) {
		this.sortField = sortField;
		this.sortOrder = sortOrder;
	}

	public int compare(Account o1, Account o2) {
		try {
			Object value1 = Account.class.getField(this.sortField).get(o1);
			Object value2 = Account.class.getField(this.sortField).get(o2);

			int value = ((Comparable) value1).compareTo(value2);

			return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

}
