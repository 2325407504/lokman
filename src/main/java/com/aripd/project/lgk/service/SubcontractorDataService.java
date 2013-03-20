package com.aripd.project.lgk.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aripd.project.lgk.domain.Subcontractor;
import com.aripd.project.lgk.repository.RegionRepository;
import com.aripd.project.lgk.repository.SubcontractorRepository;

@Service
public class SubcontractorDataService {

	@Autowired
	SubcontractorRepository repository;

	@Autowired
	RegionRepository regionRepository;
	
	public void init() {
		List<Subcontractor> subcontractors = new ArrayList<Subcontractor>();
		
		Subcontractor subcontractor1 = new Subcontractor();
		subcontractor1.setActive(true);
		subcontractor1.setCode("UID1");
		subcontractor1.setRegion(regionRepository.findOne(1L));
		subcontractor1.setName("Fethi Topaktaş");
		subcontractors.add(subcontractor1);

		Subcontractor subcontractor2 = new Subcontractor();
		subcontractor2.setActive(true);
		subcontractor2.setCode("UID2");
		subcontractor2.setRegion(regionRepository.findOne(2L));
		subcontractor2.setName("Niyazi Özdemir");
		subcontractors.add(subcontractor2);
		
		Subcontractor subcontractor3 = new Subcontractor();
		subcontractor3.setActive(true);
		subcontractor3.setCode("UID3");
		subcontractor3.setRegion(regionRepository.findOne(2L));
		subcontractor3.setName("Newnak");
		subcontractors.add(subcontractor3);

		Subcontractor subcontractor4 = new Subcontractor();
		subcontractor4.setActive(true);
		subcontractor4.setCode("UID4");
		subcontractor4.setRegion(regionRepository.findOne(2L));
		subcontractor4.setName("Ali Doğan");
		subcontractors.add(subcontractor4);

		Subcontractor subcontractor5 = new Subcontractor();
		subcontractor5.setActive(true);
		subcontractor5.setCode("UID5");
		subcontractor5.setRegion(regionRepository.findOne(2L));
		subcontractor5.setName("Yavuz Narman");
		subcontractors.add(subcontractor5);
		 
		Subcontractor subcontractor6 = new Subcontractor();
		subcontractor6.setActive(true);
		subcontractor6.setCode("UID6");
		subcontractor6.setRegion(regionRepository.findOne(2L));
		subcontractor6.setName("Diğer");
		subcontractors.add(subcontractor6);

		Subcontractor subcontractor7 = new Subcontractor();
		subcontractor7.setActive(true);
		subcontractor7.setCode("UID7");
		subcontractor7.setRegion(regionRepository.findOne(2L));
		subcontractor7.setName("Çubukçular");
		subcontractors.add(subcontractor7);

		Subcontractor subcontractor8 = new Subcontractor();
		subcontractor8.setActive(true);
		subcontractor8.setCode("UID8");
		subcontractor8.setRegion(regionRepository.findOne(2L));
		subcontractor8.setName("Veysel Kutucu");
		subcontractors.add(subcontractor8);
		 
		Subcontractor subcontractor9 = new Subcontractor();
		subcontractor9.setActive(true);
		subcontractor9.setCode("UID9");
		subcontractor9.setRegion(regionRepository.findOne(2L));
		subcontractor9.setName("Erdal Yalım Bursa");
		subcontractors.add(subcontractor9);
		 
		Subcontractor subcontractor10 = new Subcontractor();
		subcontractor10.setActive(true);
		subcontractor10.setCode("UID10");
		subcontractor10.setRegion(regionRepository.findOne(2L));
		subcontractor10.setName("Erdal Yalım Eskişehir");
		subcontractors.add(subcontractor10);
		 
		Subcontractor subcontractor11 = new Subcontractor();
		subcontractor11.setActive(true);
		subcontractor11.setCode("UID11");
		subcontractor11.setRegion(regionRepository.findOne(2L));
		subcontractor11.setName("Ali Caner");
		subcontractors.add(subcontractor11);
		 
		Subcontractor subcontractor12 = new Subcontractor();
		subcontractor12.setActive(true);
		subcontractor12.setCode("UID12");
		subcontractor12.setRegion(regionRepository.findOne(2L));
		subcontractor12.setName("İsmail Demirkol");
		subcontractors.add(subcontractor12);
		 
		Subcontractor subcontractor13 = new Subcontractor();
		subcontractor13.setActive(true);
		subcontractor13.setCode("UID13");
		subcontractor13.setRegion(regionRepository.findOne(3L));
		subcontractor13.setName("İbrahim Gedikli");
		subcontractors.add(subcontractor13);
		 
		Subcontractor subcontractor14 = new Subcontractor();
		subcontractor14.setActive(true);
		subcontractor14.setCode("UID14");
		subcontractor14.setRegion(regionRepository.findOne(3L));
		subcontractor14.setName("Diğer");
		subcontractors.add(subcontractor14);

		Subcontractor subcontractor15 = new Subcontractor();
		subcontractor15.setActive(true);
		subcontractor15.setCode("UID15");
		subcontractor15.setRegion(regionRepository.findOne(4L));
		subcontractor15.setName("Yaşar Bayrakal");
		subcontractors.add(subcontractor15);

		Subcontractor subcontractor16 = new Subcontractor();
		subcontractor16.setActive(true);
		subcontractor16.setCode("UID16");
		subcontractor16.setRegion(regionRepository.findOne(4L));
		subcontractor16.setName("Ömer Örücü");
		subcontractors.add(subcontractor16);

		Subcontractor subcontractor17 = new Subcontractor();
		subcontractor17.setActive(true);
		subcontractor17.setCode("UID17");
		subcontractor17.setRegion(regionRepository.findOne(4L));
		subcontractor17.setName("Ramazan Kafa");
		subcontractors.add(subcontractor17);

		Subcontractor subcontractor18 = new Subcontractor();
		subcontractor18.setActive(true);
		subcontractor18.setCode("UID18");
		subcontractor18.setRegion(regionRepository.findOne(4L));
		subcontractor18.setName("Diğer");
		subcontractors.add(subcontractor18);

		Subcontractor subcontractor19 = new Subcontractor();
		subcontractor19.setActive(true);
		subcontractor19.setCode("UID19");
		subcontractor19.setRegion(regionRepository.findOne(5L));
		subcontractor19.setName("Veysel Kızıldağ");
		subcontractors.add(subcontractor19);

		Subcontractor subcontractor20 = new Subcontractor();
		subcontractor20.setActive(true);
		subcontractor20.setCode("UID20");
		subcontractor20.setRegion(regionRepository.findOne(5L));
		subcontractor20.setName("Ayhan Aydın");
		subcontractors.add(subcontractor20);

		Subcontractor subcontractor21 = new Subcontractor();
		subcontractor21.setActive(true);
		subcontractor21.setCode("UID21");
		subcontractor21.setRegion(regionRepository.findOne(5L));
		subcontractor21.setName("Murat Teke");
		subcontractors.add(subcontractor21);

		Subcontractor subcontractor22 = new Subcontractor();
		subcontractor22.setActive(true);
		subcontractor22.setCode("UID22");
		subcontractor22.setRegion(regionRepository.findOne(5L));
		subcontractor22.setName("Ünsal Alemdar");
		subcontractors.add(subcontractor22);

		Subcontractor subcontractor23 = new Subcontractor();
		subcontractor23.setActive(true);
		subcontractor23.setCode("UID23");
		subcontractor23.setRegion(regionRepository.findOne(5L));
		subcontractor23.setName("Senayi Öztürk");
		subcontractors.add(subcontractor23);

		Subcontractor subcontractor24 = new Subcontractor();
		subcontractor24.setActive(true);
		subcontractor24.setCode("UID24");
		subcontractor24.setRegion(regionRepository.findOne(5L));
		subcontractor24.setName("Diğer");
		subcontractors.add(subcontractor24);

		Subcontractor subcontractor25 = new Subcontractor();
		subcontractor25.setActive(true);
		subcontractor25.setCode("UID25");
		subcontractor25.setRegion(regionRepository.findOne(6L));
		subcontractor25.setName("Diğer");
		subcontractors.add(subcontractor25);

		repository.save(subcontractors);
	}

}
