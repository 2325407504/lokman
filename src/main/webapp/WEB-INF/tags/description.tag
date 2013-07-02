<%@ tag language="java" %>
<%@ attribute name="id" required="true" %>
<%@ attribute name="caption" required="false" type="java.lang.String" %>
<%@ attribute name="cssClass" required="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:choose>
    <c:when test="${caption == null}">
        <dl class="${cssClass}" id="${id}">
            <jsp:doBody />
        </dl>
    </c:when>
    <c:otherwise>
        <fieldset>
            <legend><spring:message code="${caption}" text="${caption}" /></legend>
            <dl class="${cssClass}" id="${id}">
                <jsp:doBody />
            </dl>
        </fieldset>
    </c:otherwise>
</c:choose>