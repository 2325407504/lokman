package com.aripd.project.lokman.managed.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.aripd.project.lokman.domain.Trip;

public class LazyTripDataModel extends LazyDataModel<Trip> {

	private static final long serialVersionUID = -4454382427046908677L;
	
	private List<Trip> datasource;

	public LazyTripDataModel(List<Trip> datasource) {
		this.datasource = datasource;
	}
	
	public Trip getRowData(String rowKey) {
        for(Trip trip : datasource) {
            if(trip.getId().equals(rowKey))
                return trip;
        }

        return null;
    }

    public Object getRowKey(Trip trip) {
        return trip.getId();
    }
	
    public List<Trip> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters) {
        List<Trip> data = new ArrayList<Trip>();

        //filter
        for(Trip trip : datasource) {
            boolean match = true;

            for(Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                try {
                    String filterProperty = it.next();
                    String filterValue = filters.get(filterProperty);
                    String fieldValue = String.valueOf(trip.getClass().getField(filterProperty).get(trip));

                    if(filterValue == null || fieldValue.startsWith(filterValue)) {
                        match = true;
                    }
                    else {
                        match = false;
                        break;
                    }
                } catch(Exception e) {
                    match = false;
                } 
            }

            if(match) {
                data.add(trip);
            }
        }

        //sort
        if(sortField != null) {
            Collections.sort(data, new LazyTripSorter(sortField, sortOrder));
        }

        //rowCount
        int dataSize = data.size();
        this.setRowCount(dataSize);

        //paginate
        if(dataSize > pageSize) {
            try {
                return data.subList(first, first + pageSize);
            }
            catch(IndexOutOfBoundsException e) {
                return data.subList(first, first + (dataSize % pageSize));
            }
        }
        else {
            return data;
        }
    }

}
