package com.aripd.project.lgk.service;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.aripd.account.domain.Account;
import com.aripd.common.dto.datatables.DatatablesCriteria;
import com.aripd.common.dto.datatables.DatatablesResultSet;
import com.aripd.project.lgk.domain.Trip;
import com.aripd.project.lgk.domain.Truck;
import org.joda.time.DateTime;
import org.springframework.web.multipart.MultipartFile;

public interface TripService {

    public Trip findOne(Long id);

    public Trip findOneByAccountAndId(Account account, Long id);

    public List<Trip> findAll();

    public List<Trip> findByInterval(DateTime startingTime, DateTime endingTime);

    public List<Trip> findByInterval(DateTime startingTime, DateTime endingTime, Principal principal);

    public List<Trip> findByIntervalAndTruck(DateTime startingTime, DateTime endingTime, Truck truck);

    public List<Trip> findByIntervalAndTruck(DateTime startingTime, DateTime endingTime, Truck truck, Principal principal);

    public Trip save(Trip trip);

    public void delete(Long id);

    public void delete(Trip trip);

    public DatatablesResultSet<Trip> getRecords(DatatablesCriteria criteria);

    public DatatablesResultSet<Trip> getRecords(Principal principal, DatatablesCriteria criteria);

    public void importData(MultipartFile file);

    public void exportByInterval(HttpServletResponse response, DateTime startingTime, DateTime endingTime);

    public void exportByInterval(HttpServletResponse response, DateTime startingTime, DateTime endingTime, Principal principal);

    public void exportByIntervalAndTruck(HttpServletResponse response, DateTime startingTime, DateTime endingTime, Truck truck);

    public void exportByIntervalAndTruck(HttpServletResponse response, DateTime startingTime, DateTime endingTime, Truck truck, Principal principal);
}
