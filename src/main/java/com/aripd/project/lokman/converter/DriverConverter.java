package com.aripd.project.lokman.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.aripd.project.lokman.domain.Driver;
import com.aripd.project.lokman.service.DriverService;

@Component
public class DriverConverter implements Converter<String, Driver> {

	@Autowired
	DriverService driverService;

	@Override
	public Driver convert(String source) {
		return driverService.getOne(Long.valueOf(source));
	}

}
