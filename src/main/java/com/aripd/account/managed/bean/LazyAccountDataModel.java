package com.aripd.account.managed.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.aripd.account.domain.Account;

public class LazyAccountDataModel extends LazyDataModel<Account> {

	private static final long serialVersionUID = -4454382427046908677L;
	
	private List<Account> datasource;

	public LazyAccountDataModel(List<Account> datasource) {
		this.datasource = datasource;
	}
	
	public Account getRowData(String rowKey) {
        for(Account account : datasource) {
            if(account.getId().equals(rowKey))
                return account;
        }

        return null;
    }

    public Object getRowKey(Account account) {
        return account.getId();
    }
	
    public List<Account> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters) {
        List<Account> data = new ArrayList<Account>();

        //filter
        for(Account account : datasource) {
            boolean match = true;

            for(Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                try {
                    String filterProperty = it.next();
                    String filterValue = filters.get(filterProperty);
                    String fieldValue = String.valueOf(account.getClass().getField(filterProperty).get(account));

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
                data.add(account);
            }
        }

        //sort
        if(sortField != null) {
            Collections.sort(data, new LazyAccountSorter(sortField, sortOrder));
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
