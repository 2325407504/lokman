package com.aripd.common.dto;

import org.apache.log4j.Logger;

public enum SortDirection {
	ASC("asc"), DESC("desc");

	private String direction;

	protected static Logger logger = Logger.getLogger(SortDirection.class);

	private SortDirection(String direction) {
		this.direction = direction;
	}

	public String getDirection() {
		return this.direction;
	}

	public static SortDirection valueOfCaseInsensitive(String value) {
		String valueUpper = value.toUpperCase();
		SortDirection sd = SortDirection.valueOf(valueUpper);
		logger.debug("SortDirection converted " + value + " to " + sd);
		return sd;
	}
}