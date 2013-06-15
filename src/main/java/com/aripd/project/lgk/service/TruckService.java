package com.aripd.project.lgk.service;

import java.util.List;

import com.aripd.common.dto.PagingCriteria;
import com.aripd.common.dto.ResultSet;
import com.aripd.project.lgk.domain.Region;
import com.aripd.project.lgk.domain.Truck;

public interface TruckService {

    public Integer getKilometer(Long id);

    public Truck findOne(Long id);

    public Truck findOneByPlate(String plate);

    public List<Truck> findAll();

    public List<Truck> findByRegion(Region region);

    public Truck save(Truck truck);

    public void delete(Long id);

    public void delete(Truck truck);

    public ResultSet<Truck> getRecords(PagingCriteria criteria);
}
