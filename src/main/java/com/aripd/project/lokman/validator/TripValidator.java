package com.aripd.project.lokman.validator;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.aripd.project.lokman.domain.Trip;
import com.aripd.project.lokman.service.TripService;

@Service("tripValidator")
public class TripValidator {

	@Resource(name = "tripService")
	private TripService tripService;

	public void setFmsService(TripService tripService) {
		this.tripService = tripService;
	}

	public void validate(Trip trip, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "startingKm", "required", "It is required!");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endingKm", "required", "It is required!");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "loadWeightInTonne", "required", "It is required!");
		
		List<Trip> a = tripService.findByPublishedAt(trip.getPublishedAt());
		if (a.isEmpty() == false) {
			errors.rejectValue("publishedAt", "Available Date", "Available Date");
		}
	}

}
