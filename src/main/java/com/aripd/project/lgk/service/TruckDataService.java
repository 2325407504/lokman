package com.aripd.project.lgk.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aripd.project.lgk.domain.Truck;
import com.aripd.project.lgk.repository.RegionRepository;
import com.aripd.project.lgk.repository.TruckRepository;

@Service
public class TruckDataService {

	@Autowired
	TruckRepository repository;

	@Autowired
	RegionRepository regionRepository;

	public void init() {
		List<Truck> trucks = new ArrayList<Truck>();
		
		Truck truck1 = new Truck();
		truck1.setRegion(regionRepository.findOne(1L));
		truck1.setPlate("34 YK 1063");
		trucks.add(truck1);

		Truck truck2 = new Truck();
		truck2.setRegion(regionRepository.findOne(1L));
		truck2.setPlate("35 SEC 10");
		trucks.add(truck2);
		
		Truck truck3 = new Truck();
		truck3.setRegion(regionRepository.findOne(1L));
		truck3.setPlate("35 ZH 318");
		trucks.add(truck3);

		Truck truck4 = new Truck();
		truck4.setRegion(regionRepository.findOne(1L));
		truck4.setPlate("35 U 1884");
		trucks.add(truck4);

		Truck truck5 = new Truck();
		truck5.setRegion(regionRepository.findOne(2L));
		truck5.setPlate("35 HA 6914");
		trucks.add(truck5);
		 
		Truck truck6 = new Truck();
		truck6.setRegion(regionRepository.findOne(2L));
		truck6.setPlate("34 AT 2912");
		trucks.add(truck6);

		Truck truck7 = new Truck();
		truck7.setRegion(regionRepository.findOne(2L));
		truck7.setPlate("34 TE 6123");
		trucks.add(truck7);

		Truck truck8 = new Truck();
		truck8.setRegion(regionRepository.findOne(3L));
		truck8.setPlate("35 CEV 47");
		trucks.add(truck8);
		 
		Truck truck9 = new Truck();
		truck9.setRegion(regionRepository.findOne(3L));
		truck9.setPlate("35 HMH 47");
		trucks.add(truck9);
		 
		Truck truck10 = new Truck();
		truck10.setRegion(regionRepository.findOne(3L));
		truck10.setPlate("35 SAU 44");
		trucks.add(truck10);
		 
		Truck truck11 = new Truck();
		truck11.setRegion(regionRepository.findOne(3L));
		truck11.setPlate("35 AC 7870");
		trucks.add(truck11);
		 
		Truck truck12 = new Truck();
		truck12.setRegion(regionRepository.findOne(4L));
		truck12.setPlate("35 VH 793");
		trucks.add(truck12);
		 
		Truck truck13 = new Truck();
		truck13.setRegion(regionRepository.findOne(4L));
		truck13.setPlate("35 HNU 60");
		trucks.add(truck13);
		 
		Truck truck14 = new Truck();
		truck14.setRegion(regionRepository.findOne(4L));
		truck14.setPlate("09 D 9585");
		trucks.add(truck14);

		Truck truck15 = new Truck();
		truck15.setRegion(regionRepository.findOne(4L));
		truck15.setPlate("35 HVU 92");
		trucks.add(truck15);

		repository.save(trucks);
	}
}
