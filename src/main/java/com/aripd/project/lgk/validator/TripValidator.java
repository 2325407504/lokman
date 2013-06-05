package com.aripd.project.lgk.validator;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.aripd.project.lgk.domain.Trip;
import com.aripd.project.lgk.service.TripService;

@Service("tripValidator")
public class TripValidator {

    @Resource(name = "tripService")
    private TripService tripService;

    public void validate(Trip trip, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "truck", "required", "It is required!");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "driver", "required", "It is required!");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "startingPoint", "required", "It is required!");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "startingKm", "required", "It is required!");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "startingTime", "required", "It is required!");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endingPoint", "required", "It is required!");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endingKm", "required", "It is required!");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endingTime", "required", "It is required!");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "loadWeightInTonne", "required", "It is required!");
    }
}
