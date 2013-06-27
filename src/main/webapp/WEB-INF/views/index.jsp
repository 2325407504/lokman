<%@ include file="/WEB-INF/views/header.jsp" %>

<sec:authorize access="isAuthenticated()">
<div class="well">
	<div class="row-fluid">
		<div class="span6">
			<h4><spring:message code="Today's Trips" /></h4>
			<p><a href="#" class="badge badge-inverse">12</a></p>
		</div>
		<div class="span6">
			<h4><spring:message code="Today's Forwardings" /></h4>
			<p><a href="#" class="badge badge-inverse">8</a></p>
		</div>
	</div>
</div>

<div class="page-header">
	<h1><spring:message code="Trip Report" /> <small><spring:message code="Trips" /></small></h1>
</div>
<spring:url var="chart1Url" value="/trip/report3" />
<img src="${chart1Url}" alt="">
<table class="table table-bordered">
	<thead>
		<tr>
			<th><spring:message code="Date" /></th>
			<th><spring:message code="Weight" /></th>
		</tr>
	</thead>
	<tbody>
	</tbody>
	<tfoot>
	</tfoot>
</table>
</sec:authorize>

<sec:authorize access="isAnonymous()">
</sec:authorize>

<%@ include file="/WEB-INF/views/footer.jsp" %>