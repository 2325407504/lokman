package com.aripd.common.dto;

import com.aripd.common.dto.autocomplete.AutocompleteXHRResultSet;
import com.aripd.common.dto.datatables.DatatablesXHRResultSet;
import com.aripd.common.dto.datatables.DatatablesCriteria;
import com.aripd.common.dto.datatables.DatatablesResultSet;
import com.aripd.common.dto.autocomplete.AutocompleteResultSet;
import com.aripd.common.entity.BaseEntity;

public class ControllerUtils {

    public static <T extends BaseEntity> WebResultSet<T> getDatatablesResultSet(DatatablesCriteria pc, DatatablesResultSet<T> rs) {
        return new DatatablesXHRResultSet<T>(pc, rs);
    }

    public static <T extends BaseEntity> WebResultSet<T> getAutocompleteResultSet(AutocompleteResultSet<T> rs) {
        return new AutocompleteXHRResultSet<T>(rs);
    }
}
