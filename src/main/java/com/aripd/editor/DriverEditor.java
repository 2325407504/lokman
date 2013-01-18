package com.aripd.editor;

import java.beans.PropertyEditorSupport;

import com.aripd.domain.Driver;
import com.aripd.service.DriverService;

public class DriverEditor extends PropertyEditorSupport {

	private DriverService driverService;
	
	private Driver driver;

	public DriverEditor(DriverService driverService) {
		this.driverService = driverService;
	}

	public void setAsText(String text) throws IllegalArgumentException {
		Driver driver = driverService.getOne(Long.parseLong(text));
		this.setValue(driver);
	}
	
	public String getAsText() {
		Driver driver = (Driver) this.getValue();
		if (driver == null)
			return "";
		return Long.toString(driver.getId());
	}
	
	public void setValue(Object value) {
		setDriver((Driver) value);
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	
}
