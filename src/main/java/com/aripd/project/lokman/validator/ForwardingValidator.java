package com.aripd.project.lokman.validator;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import com.aripd.project.lokman.domain.Forwarding;
import com.aripd.project.lokman.service.ForwardingService;

@Service("forwardingValidator")
public class ForwardingValidator {

	@Resource(name = "forwardingService")
	private ForwardingService forwardingService;

	public void validate(Forwarding forwarding, Errors errors) {
		
	}

}
