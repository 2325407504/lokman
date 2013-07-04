<%@ tag language="java" description="Language Selector" pageEncoding="UTF-8" %>
<%@ attribute name="title" required="true" type="java.lang.String" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="localeCode" value="${pageContext.response.locale}" />
<c:set var="availLanguages" value="tr_TR,en_US" />
<a href="#" class="dropdown-toggle" data-toggle="dropdown"><spring:message code="${title}" text="${title}" />: ${localeCode}<b class="caret"></b></a>
<ul class="dropdown-menu">
    <c:if test="${!fn:contains(availLanguages,localeCode)}">
        <c:set var="localeCode" value="tr_TR" />
    </c:if>
    <c:forEach items="${availLanguages}" var="lang">
        <li>
            <a href="?language=${lang}">
                ${lang}
                <c:if test="${fn:contains(localeCode, lang)}">
                    <i class="icon-ok"></i>
                </c:if>
            </a>
        </li>
    </c:forEach>
</ul>
