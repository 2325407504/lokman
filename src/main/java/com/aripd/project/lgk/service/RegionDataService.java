package com.aripd.project.lgk.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aripd.project.lgk.domain.Region;
import com.aripd.project.lgk.repository.RegionRepository;

@Service
public class RegionDataService {

	@Autowired
	RegionRepository repository;
	
	private String aRegions[] = {"İstanbul/Trakya", "Marmara", "Kuzey/Orta Anadolu", "Ege", "Doğu Akdeniz", "Güney/Orta Anadolu"}; 

	public void init() {
		
		List<Region> regions = new ArrayList<Region>();
		Region region;
		for (String sRegion : aRegions) {
			region = new Region();
			region.setName(sRegion);
			regions.add(region);
		}
		repository.save(regions);
		
	}

}
