package com.aripd.validator;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.aripd.domain.Truck;

@Service("truckValidator")
@Transactional
public class TruckValidator {

	public boolean supports(Class clazz) {
		return Truck.class.isAssignableFrom(clazz);
	}
	
	public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "plate", "required", "It is required!");

        Truck truck = (Truck) target;		
	}

}
