package com.aripd.project.lgk.validator;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import com.aripd.project.lgk.domain.Production;
import com.aripd.project.lgk.service.ProductionService;

@Service("productionValidator")
public class ProductionValidator {

    @Resource(name = "productionService")
    private ProductionService productionService;

    public void validate(Production production, Errors errors) {
    }
}
