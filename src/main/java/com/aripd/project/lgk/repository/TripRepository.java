package com.aripd.project.lgk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aripd.account.domain.Account;
import com.aripd.project.lgk.domain.Trip;
import com.aripd.project.lgk.domain.Truck;
import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Long> {

    Trip findOneByAccountAndId(Account account, Long id);

    List<Trip> findByTruck(Truck truck);

    List<Trip> findByAccountAndTruck(Account account, Truck truck);
}
