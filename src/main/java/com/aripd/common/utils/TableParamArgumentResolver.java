package com.aripd.common.utils;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;

import com.aripd.common.dto.PagingCriteria;
import com.aripd.common.dto.SortField;
import com.aripd.common.dto.TableParam;

public class TableParamArgumentResolver implements WebArgumentResolver {

	private static final String S_ECHO = "sEcho";
	private static final String I_DISPLAY_START = "iDisplayStart";
	private static final String I_DISPLAY_LENGTH = "iDisplayLength";
	private static final String I_SORTING_COLS = "iSortingCols";

	private static final String I_SORT_COLS = "iSortCol_";
	private static final String S_SORT_DIR = "sSortDir_";
	private static final String S_DATA_PROP = "mDataProp_";

	private static final String S_SEARCHES = "sSearch";
	
	public Object resolveArgument(MethodParameter param, NativeWebRequest request) throws Exception {
		TableParam tableParamAnnotation = param.getParameterAnnotation(TableParam.class);

		if (tableParamAnnotation != null) {
			HttpServletRequest httpRequest = (HttpServletRequest) request.getNativeRequest();

			String sEcho = httpRequest.getParameter(S_ECHO);
			String sSearches = httpRequest.getParameter(S_SEARCHES);
			String sDisplayStart = httpRequest.getParameter(I_DISPLAY_START);
			String sDisplayLength = httpRequest.getParameter(I_DISPLAY_LENGTH);
			String sSortingCols = httpRequest.getParameter(I_SORTING_COLS);

			Integer iEcho = Integer.parseInt(sEcho);
			Integer iDisplayStart = Integer.parseInt(sDisplayStart);
			Integer iDisplayLength = Integer.parseInt(sDisplayLength);
			Integer iSortingCols = Integer.parseInt(sSortingCols);
			
			List<SortField> sortFields = new ArrayList<SortField>();
			for (int colCount = 0; colCount < iSortingCols; colCount++) {
				String sSortCol = httpRequest.getParameter(I_SORT_COLS + colCount);
				String sSortDir = httpRequest.getParameter(S_SORT_DIR + colCount);
				String sColName = httpRequest.getParameter(S_DATA_PROP + sSortCol);
				sortFields.add(new SortField(sColName, sSortDir));
			}

			PagingCriteria pc = new PagingCriteria(sSearches, iDisplayStart, iDisplayLength, iEcho, sortFields);

			return pc;
		}

		return WebArgumentResolver.UNRESOLVED;
	}
}