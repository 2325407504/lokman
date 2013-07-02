package com.aripd.common.dto.autocomplete;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;

public class AutocompleteArgumentResolver implements WebArgumentResolver {

    private static final String S_SEARCHES = "term";

    public Object resolveArgument(MethodParameter param, NativeWebRequest request) throws Exception {
        AutocompleteParam annotation = param.getParameterAnnotation(AutocompleteParam.class);

        if (annotation != null) {
            HttpServletRequest httpRequest = (HttpServletRequest) request.getNativeRequest();

            String sSearches = httpRequest.getParameter(S_SEARCHES);

            AutocompleteCriteria pc = new AutocompleteCriteria(sSearches);

            return pc;
        }

        return WebArgumentResolver.UNRESOLVED;
    }
}