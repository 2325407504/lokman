package com.aripd.validator;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.aripd.domain.Truck;

@Service("truckValidator")
@Transactional
public class TruckValidator {

	public void validate(Truck truck, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "plate", "required", "It is required!");
	}

}
