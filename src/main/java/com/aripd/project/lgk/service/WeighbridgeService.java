package com.aripd.project.lgk.service;

import javax.servlet.http.HttpServletResponse;

import com.aripd.common.dto.PagingCriteria;
import com.aripd.common.dto.ResultSet;
import com.aripd.project.lgk.domain.Weighbridge;

public interface WeighbridgeService {

    Weighbridge findOne(Long id);

    Weighbridge save(Weighbridge weighbridge);

    void delete(Long id);

    void delete(Weighbridge weighbridge);

    ResultSet<Weighbridge> getRecords(PagingCriteria criteria);

    void exportXLS(HttpServletResponse response);

    void importXLSX(String fileName);
}
