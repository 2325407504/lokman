<%@ include file="/WEB-INF/jsp/header.jsp" %>

<sec:authorize access="isAuthenticated()">
<div class="well">
	<div class="row-fluid">
		<div class="span6">
			<h4><spring:message code="Today's Trips" text="Today's Trips"></spring:message></h4>
			<p><a href="#" class="badge badge-inverse">12</a></p>
		</div>
		<div class="span6">
			<h4><spring:message code="Today's Forwardings" text="Today's Forwardings"></spring:message></h4>
			<p><a href="#" class="badge badge-inverse">8</a></p>
		</div>
	</div>
</div>

<div class="page-header">
	<h1><spring:message code="Trip Report" text="Trip Report"></spring:message> <small><spring:message code="Trip Tracking"></spring:message></small></h1>
</div>
<spring:url var="chart1Url" value="/trip/report3" />
<img src="${chart1Url}" alt="">
<table class="table table-bordered">
	<thead>
		<tr>
			<th><spring:message code="Date"></spring:message></th>
			<th><spring:message code="Weight"></spring:message></th>
		</tr>
	</thead>
	<tbody>
	</tbody>
	<tfoot>
	</tfoot>
</table>
</sec:authorize>

<sec:authorize access="isAnonymous()">
<spring:url value="/" var="homeUrl" />
<spring:url var="forwardingList" value="/forwarding/list" />
<spring:url var="tripList" value="/trip/list" />
<ul class="nav nav-tabs">
	<li class="active"><a href="${homeUrl}"><i class="icon-home"></i> <spring:message code="Home"></spring:message></a></li>
	<li class=""><a href="${forwardingList}"><spring:message code="Forwarding Tracking" text="Forwarding Tracking"></spring:message></a></li>
	<li class=""><a href="${tripList}"><spring:message code="Trip Tracking" text="Trip Tracking"></spring:message></a></li>
</ul>
</sec:authorize>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>