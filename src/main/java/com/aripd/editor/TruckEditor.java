package com.aripd.editor;

import java.beans.PropertyEditorSupport;

import com.aripd.domain.Truck;
import com.aripd.service.TruckService;

public class TruckEditor extends PropertyEditorSupport {

	private TruckService truckService;
	
	private Truck truck;

	public TruckEditor(TruckService truckService) {
		this.truckService = truckService;
	}

	public void setAsText(String text) throws IllegalArgumentException {
		Truck truck = truckService.getOne(Long.parseLong(text));
		this.setValue(truck);
	}
	
	public String getAsText() {
		Truck truck = (Truck) this.getValue();
		if (truck == null)
			return "";
		return Long.toString(truck.getId());
	}
	
	public void setValue(Object value) {
		setTruck((Truck) value);
	}

	public Truck getTruck() {
		return truck;
	}

	public void setTruck(Truck truck) {
		this.truck = truck;
	}
	
}
