package com.aripd.project.lgk.service;

import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import com.aripd.account.domain.Account;
import com.aripd.common.dto.PagingCriteria;
import com.aripd.common.dto.ResultSet;
import com.aripd.project.lgk.domain.Forwarding;

public interface ForwardingService {

	Forwarding findOne(Long id);
	
	Forwarding findOneByAccountAndId(Account account, Long id);
	
	Forwarding save(Forwarding formData);

	void delete(Long id);

	void delete(Forwarding forwarding);
	
	ResultSet<Forwarding> getRecords(PagingCriteria criteria);

	ResultSet<Forwarding> getRecords(Principal principal, PagingCriteria criteria);

	void exportXLS(HttpServletResponse response);

}
