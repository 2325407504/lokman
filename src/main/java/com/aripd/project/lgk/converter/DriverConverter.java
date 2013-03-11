package com.aripd.project.lgk.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.aripd.project.lgk.domain.Driver;
import com.aripd.project.lgk.service.DriverService;

@Component
public class DriverConverter implements Converter<String, Driver> {

	@Autowired
	DriverService driverService;

	@Override
	public Driver convert(String source) {
		return driverService.findOne(Long.valueOf(source));
	}

}
