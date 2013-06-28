<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<spring:url value="/" var="homeUrl" />

<ul class="breadcrumb">
  <li><a href="${homeUrl}"><spring:message code="Home" /></a> <span class="divider">/</span></li>
  <li class="active"><spring:message code="Trips" /></li>
</ul>
<div>
	<spring:url var="chart1Url" value="/trip/report3" />
	<img src="${chart1Url}" alt="">
</div>

<jsp:include page="/WEB-INF/views/footer.jsp" />