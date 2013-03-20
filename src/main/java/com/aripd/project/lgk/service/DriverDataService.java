package com.aripd.project.lgk.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aripd.project.lgk.domain.Driver;
import com.aripd.project.lgk.repository.RegionRepository;
import com.aripd.project.lgk.repository.DriverRepository;

@Service
public class DriverDataService {

	@Autowired
	DriverRepository repository;

	@Autowired
	RegionRepository regionRepository;

	public void init() {
		List<Driver> drivers = new ArrayList<Driver>();
		
		Driver driver1 = new Driver();
		driver1.setActive(true);
		driver1.setRegion(regionRepository.findOne(1L));
		driver1.setCode("UID1");
		driver1.setName("Sürücü1");
		driver1.setPhonenumber("Telefon1");
		drivers.add(driver1);

		Driver driver2 = new Driver();
		driver2.setActive(true);
		driver2.setRegion(regionRepository.findOne(1L));
		driver2.setCode("UID2");
		driver2.setName("Sürücü2");
		driver2.setPhonenumber("Telefon2");
		drivers.add(driver2);
		
		Driver driver3 = new Driver();
		driver3.setActive(true);
		driver3.setRegion(regionRepository.findOne(1L));
		driver3.setCode("UID3");
		driver3.setName("Sürücü3");
		driver3.setPhonenumber("Telefon3");
		drivers.add(driver3);

		Driver driver4 = new Driver();
		driver4.setActive(true);
		driver4.setRegion(regionRepository.findOne(1L));
		driver4.setCode("UID4");
		driver4.setName("Sürücü4");
		driver4.setPhonenumber("Telefon4");
		drivers.add(driver4);

		Driver driver5 = new Driver();
		driver5.setActive(true);
		driver5.setRegion(regionRepository.findOne(2L));
		driver5.setCode("UID5");
		driver5.setName("Sürücü5");
		driver5.setPhonenumber("Telefon5");
		drivers.add(driver5);
		 
		Driver driver6 = new Driver();
		driver6.setActive(true);
		driver6.setRegion(regionRepository.findOne(2L));
		driver6.setCode("UID6");
		driver6.setName("Sürücü6");
		driver6.setPhonenumber("Telefon6");
		drivers.add(driver6);

		Driver driver7 = new Driver();
		driver7.setActive(true);
		driver7.setRegion(regionRepository.findOne(2L));
		driver7.setCode("UID7");
		driver7.setName("Sürücü7");
		driver7.setPhonenumber("Telefon7");
		drivers.add(driver7);

		Driver driver8 = new Driver();
		driver8.setActive(true);
		driver8.setRegion(regionRepository.findOne(3L));
		driver8.setCode("UID8");
		driver8.setName("Sürücü8");
		driver8.setPhonenumber("Telefon8");
		drivers.add(driver8);
		 
		Driver driver9 = new Driver();
		driver9.setActive(true);
		driver9.setRegion(regionRepository.findOne(3L));
		driver9.setCode("UID9");
		driver9.setName("Sürücü9");
		driver9.setPhonenumber("Telefon9");
		drivers.add(driver9);
		 
		Driver driver10 = new Driver();
		driver10.setActive(true);
		driver10.setRegion(regionRepository.findOne(3L));
		driver10.setCode("UID10");
		driver10.setName("Sürücü10");
		driver10.setPhonenumber("Telefon10");
		drivers.add(driver10);
		 
		Driver driver11 = new Driver();
		driver11.setActive(true);
		driver11.setRegion(regionRepository.findOne(3L));
		driver11.setCode("UID11");
		driver11.setName("Sürücü11");
		driver11.setPhonenumber("Telefon11");
		drivers.add(driver11);
		 
		Driver driver12 = new Driver();
		driver12.setActive(true);
		driver12.setRegion(regionRepository.findOne(4L));
		driver12.setCode("UID12");
		driver12.setName("Sürücü12");
		driver12.setPhonenumber("Telefon12");
		drivers.add(driver12);
		 
		Driver driver13 = new Driver();
		driver13.setActive(true);
		driver13.setRegion(regionRepository.findOne(4L));
		driver13.setCode("UID13");
		driver13.setName("Sürücü13");
		driver13.setPhonenumber("Telefon13");
		drivers.add(driver13);
		 
		Driver driver14 = new Driver();
		driver14.setActive(true);
		driver14.setRegion(regionRepository.findOne(4L));
		driver14.setCode("UID14");
		driver14.setName("Sürücü14");
		driver14.setPhonenumber("Telefon14");
		drivers.add(driver14);

		Driver driver15 = new Driver();
		driver15.setActive(true);
		driver15.setRegion(regionRepository.findOne(4L));
		driver15.setCode("UID15");
		driver15.setName("Sürücü15");
		driver15.setPhonenumber("Telefon15");
		drivers.add(driver15);

		repository.save(drivers);
	}
}
