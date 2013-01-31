package com.aripd.account.exception;

/**
 * Exception to be thrown if a Role is Not Found, when its expected that
 * they should be found.
 */
public class RoleNotFoundException extends Exception {

	private static final long serialVersionUID = 5308371155850005809L;

	public RoleNotFoundException() {
	}

	public RoleNotFoundException(final String s) {
		super(s);
	}

	public RoleNotFoundException(final String s, final Throwable throwable) {
		super(s, throwable);
	}

	public RoleNotFoundException(final Throwable throwable) {
		super(throwable);
	}
}
