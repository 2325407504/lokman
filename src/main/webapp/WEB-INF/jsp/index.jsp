<%@ include file="/WEB-INF/jsp/header.jsp" %>

<spring:url value="/" var="homeUrl" />
<spring:url var="forwardingList" value="/forwarding/list" />
<spring:url var="tripList" value="/trip/list" />

<ul class="nav nav-tabs">
	<li class="active"><a href="${homeUrl}"><i class="icon-home"></i> <spring:message code="Home"></spring:message></a></li>
	<li class=""><a href="${forwardingList}"><spring:message code="Forwarding Tracking" text="Forwarding Tracking"></spring:message></a></li>
	<li class=""><a href="${tripList}"><spring:message code="Trip Tracking" text="Trip Tracking"></spring:message></a></li>
</ul>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>