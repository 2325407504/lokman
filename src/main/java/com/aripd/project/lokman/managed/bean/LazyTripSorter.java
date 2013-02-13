package com.aripd.project.lokman.managed.bean;

import java.util.Comparator;

import org.primefaces.model.SortOrder;

import com.aripd.project.lokman.domain.Trip;

public class LazyTripSorter implements Comparator<Trip> {

	private String sortField;
	private SortOrder sortOrder;

	public LazyTripSorter(String sortField, SortOrder sortOrder) {
		this.sortField = sortField;
		this.sortOrder = sortOrder;
	}

	public int compare(Trip o1, Trip o2) {
		try {
			Object value1 = Trip.class.getField(this.sortField).get(o1);
			Object value2 = Trip.class.getField(this.sortField).get(o2);

			int value = ((Comparable) value1).compareTo(value2);

			return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

}
