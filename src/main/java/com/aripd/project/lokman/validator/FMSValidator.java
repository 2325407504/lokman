package com.aripd.project.lokman.validator;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.aripd.project.lokman.domain.FMS;
import com.aripd.project.lokman.service.FMSService;

@Service("fmsValidator")
public class FMSValidator {

	@Resource(name = "fmsService")
	private FMSService fmsService;

	public void setFmsService(FMSService fmsService) {
		this.fmsService = fmsService;
	}

	public void validate(FMS fms, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "startingKm", "required", "It is required!");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endingKm", "required", "It is required!");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "loadTon", "required", "It is required!");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fuelTL", "required", "It is required!");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fuelLiter", "required", "It is required!");
		
		List<FMS> a = fmsService.findByPublishedAt(fms.getPublishedAt());
		if (a.isEmpty() == false) {
			errors.rejectValue("publishedAt", "Available Date", "Available Date");
		}
	}

}
