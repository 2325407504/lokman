package com.aripd.project.lgk.validator;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.aripd.project.lgk.domain.Driver;

@Service("driverValidator")
@Transactional
public class DriverValidator {

    public void validate(Driver driver, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "required", "It is required!");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "required", "It is required!");

        if (driver.getName().length() < 3) {
            errors.rejectValue("name", "min 3 chars", "Min 3 chars");
        }
        String phonenumber = driver.getPhonenumber();
        if (!StringUtils.hasLength(phonenumber)) {
            errors.rejectValue("phonenumber", "required", "Required");
        } else {
            for (int i = 0; i < phonenumber.length(); i++) {
                if ((Character.isDigit(phonenumber.charAt(i))) == false) {
                    errors.rejectValue("phonenumber", "nonNumeric", "non-numeric");
                    break;
                }
            }
        }
    }
}
