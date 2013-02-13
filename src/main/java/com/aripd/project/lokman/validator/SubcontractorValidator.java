package com.aripd.project.lokman.validator;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;

import com.aripd.project.lokman.domain.Subcontractor;

@Service("subcontractorValidator")
@Transactional
public class SubcontractorValidator {

	public void validate(Subcontractor subcontractor, Errors errors) {
		// TODO Auto-generated method stub
		
	}

}
