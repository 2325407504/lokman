package com.aripd.project.lgk.service;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.aripd.account.domain.Account;
import com.aripd.common.dto.datatables.DatatablesCriteria;
import com.aripd.common.dto.datatables.DatatablesResultSet;
import com.aripd.project.lgk.domain.Trip;
import com.aripd.project.lgk.domain.Truck;

public interface TripService {

    public Trip findOne(Long id);

    public Trip findOneByAccountAndId(Account account, Long id);

    public List<Trip> findAll();

    public Trip save(Trip trip);

    public void delete(Long id);

    public void delete(Trip trip);

    public DatatablesResultSet<Trip> getRecords(DatatablesCriteria criteria);

    public DatatablesResultSet<Trip> getRecords(Principal principal, DatatablesCriteria criteria);

    public void exportAll(HttpServletResponse response);

    public void exportByTruck(HttpServletResponse response, Truck truck);

    public void exportByTruck(HttpServletResponse response, Principal principal, Truck truck);

    public void importXLSX(String fileName);
}
