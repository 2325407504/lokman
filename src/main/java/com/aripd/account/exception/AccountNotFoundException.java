package com.aripd.account.exception;

/**
 * Exception to be thrown if an Account is Not Found, when its expected that
 * they should be found.
 */
public class AccountNotFoundException extends Exception {

	private static final long serialVersionUID = 5308371155850005809L;

	public AccountNotFoundException() {
	}

	public AccountNotFoundException(final String s) {
		super(s);
	}

	public AccountNotFoundException(final String s, final Throwable throwable) {
		super(s, throwable);
	}

	public AccountNotFoundException(final Throwable throwable) {
		super(throwable);
	}
}
