package com.aripd.common.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The data tables response.
 */
public class DataTablesResponseDto<T> implements Serializable {

	private static final long serialVersionUID = -7453970926109773978L;

	@JsonProperty(value = "iTotalRecords")
	public int totalRecords;

	@JsonProperty(value = "iTotalDisplayRecords")
	public int totalDisplayRecords;

	@JsonProperty(value = "sEcho")
	public String echo;

	@JsonProperty(value = "sColumns")
	public String columns;

	@JsonProperty(value = "aaData")
	public List<T> data = new ArrayList<T>();

	public DataTablesResponseDto() {
	}

}