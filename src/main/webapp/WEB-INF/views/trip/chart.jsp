<%@ include file="/WEB-INF/views/header.jsp" %>

<spring:url value="/" var="homeUrl" />

<ul class="breadcrumb">
  <li><a href="${homeUrl}"><spring:message code="Home"></spring:message></a> <span class="divider">/</span></li>
  <li class="active"><spring:message code="Trips"></spring:message></li>
</ul>
<div>
	<spring:url var="chart1Url" value="/trip/report3" />
	<img src="${chart1Url}" alt="">
</div>

<%@ include file="/WEB-INF/views/footer.jsp" %>