package com.aripd.project.lokman.domain;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.aripd.account.domain.Account;
import com.aripd.common.entity.BaseEntity;

@Entity(name = "shift")
public class Shift extends BaseEntity {

	private static final long serialVersionUID = 9033830210110272840L;

	@ManyToOne
	private Account account;
	
	@NotNull
	@DateTimeFormat(iso = ISO.NONE)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar startedAt;

	@NotNull
	private Integer breakCount;
	
	@NotNull
	private Integer breakTime;
	
	@NotNull
	private Integer electrictyConsumptionStart;
	
	@NotNull
	private Integer electrictyConsumptionEnd;
	
	@NotNull
	private Integer productionInTonne;

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Calendar getStartedAt() {
		return startedAt;
	}

	public void setStartedAt(Calendar startedAt) {
		this.startedAt = startedAt;
	}

	public Integer getBreakCount() {
		return breakCount;
	}

	public void setBreakCount(Integer breakCount) {
		this.breakCount = breakCount;
	}

	public Integer getBreakTime() {
		return breakTime;
	}

	public void setBreakTime(Integer breakTime) {
		this.breakTime = breakTime;
	}

	public Integer getElectrictyConsumptionStart() {
		return electrictyConsumptionStart;
	}

	public void setElectrictyConsumptionStart(Integer electrictyConsumptionStart) {
		this.electrictyConsumptionStart = electrictyConsumptionStart;
	}

	public Integer getElectrictyConsumptionEnd() {
		return electrictyConsumptionEnd;
	}

	public void setElectrictyConsumptionEnd(Integer electrictyConsumptionEnd) {
		this.electrictyConsumptionEnd = electrictyConsumptionEnd;
	}

	public Integer getProductionInTonne() {
		return productionInTonne;
	}

	public void setProductionInTonne(Integer productionInTonne) {
		this.productionInTonne = productionInTonne;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
