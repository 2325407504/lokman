package com.aripd.project.lgk.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.aripd.project.lgk.domain.Truck;
import com.aripd.project.lgk.service.TruckService;

@Component
public class TruckConverter implements Converter<String, Truck> {

    @Autowired
    TruckService truckService;

    @Override
    public Truck convert(String source) {
        return truckService.findOne(Long.valueOf(source));
    }
}
