package com.aripd.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.aripd.domain.Truck;
import com.aripd.service.TruckService;

@Component
public class TruckConverter implements Converter<String, Truck> {

	@Autowired
	TruckService truckService;

	@Override
	public Truck convert(String source) {
		return truckService.getOne(Long.valueOf(source));
	}

}
