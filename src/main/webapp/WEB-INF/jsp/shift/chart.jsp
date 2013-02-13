<%@ include file="/WEB-INF/jsp/header.jsp" %>

<spring:url value="/" var="homeUrl" />

<ul class="breadcrumb">
  <li><a href="${homeUrl}"><spring:message code="Home"></spring:message></a> <span class="divider">/</span></li>
  <li class="active"><spring:message code="Trip Tracking"></spring:message></li>
</ul>
<div>
	<spring:url var="chartUrl" value="/trip/report3" />
	<img src="${chartUrl}" alt="">
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>