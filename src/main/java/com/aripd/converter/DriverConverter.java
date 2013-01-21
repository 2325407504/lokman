package com.aripd.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.aripd.domain.Driver;
import com.aripd.service.DriverService;

@Component
public class DriverConverter implements Converter<String, Driver> {

	@Autowired
	DriverService driverService;

	@Override
	public Driver convert(String source) {
		return driverService.getOne(Long.valueOf(source));
	}

}
