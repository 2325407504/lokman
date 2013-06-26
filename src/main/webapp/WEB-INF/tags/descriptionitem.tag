<%@ tag language="java" %>
<%@ attribute name="field" required="true" %>
<%@ attribute name="label" required="true" rtexprvalue="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<dt><spring:message code="${label}" text="${label}" /></dt>
<dd><spring:eval expression="${field}" /></dd>