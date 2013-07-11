package com.aripd.project.lgk.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.aripd.project.lgk.domain.Truck;
import com.aripd.project.lgk.service.TruckService;
import org.springframework.stereotype.Component;

@Component
public class PlateToTruckConverter implements Converter<String, Truck> {

    @Autowired
    TruckService truckService;

    @Override
    public Truck convert(String plate) {
        if (plate == null) {
            //throw IllegalArgumentException();
        }
        return truckService.findOneByPlate(plate);
    }
}
