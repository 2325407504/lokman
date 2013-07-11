package com.aripd.project.lgk.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.aripd.project.lgk.domain.Driver;
import com.aripd.project.lgk.service.DriverService;
import org.springframework.stereotype.Component;

@Component
public class DriverConverter implements Converter<String, Driver> {

    @Autowired
    DriverService driverService;

    @Override
    public Driver convert(String id) {
        if (id == null) {
            //throw IllegalArgumentException();
        }
        return driverService.findOne(Long.valueOf(id));
    }
}
