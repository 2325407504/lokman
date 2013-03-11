package com.aripd.project.lgk.managed.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.primefaces.model.LazyDataModel;

import com.aripd.project.lgk.domain.Trip;
import com.aripd.project.lgk.service.TripService;

@ManagedBean(name = "tripMB")
@RequestScoped
public class TripManagedBean implements Serializable {

	private static final long serialVersionUID = -9198450701472451939L;

	@ManagedProperty(value = "#{tripService}")
	private TripService tripService;

	private LazyDataModel<Trip> lazyModel;
	private Trip selectedTrip;
	private List<Trip> trips;

	@PostConstruct
    private void init() {
		lazyModel = new LazyTripDataModel(tripService.findAll());
    }
	
	public void setTripService(TripService tripService) {
		this.tripService = tripService;
	}

	public Trip getSelectedTrip() {
		return selectedTrip;
	}

	public void setSelectedTrip(Trip selectedTrip) {
		this.selectedTrip = selectedTrip;
	}

	public LazyDataModel<Trip> getLazyModel() {
		return lazyModel;
	}

	public List<Trip> getTrips() {
		return trips;
	}

	public void setTrips(List<Trip> trips) {
		this.trips = trips;
	}

	public void setLazyModel(LazyDataModel<Trip> lazyModel) {
		this.lazyModel = lazyModel;
	}

}